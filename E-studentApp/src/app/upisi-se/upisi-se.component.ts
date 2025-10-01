import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { StudijskiProgramService } from '../../Services/studijski-program.service';
import { StudijskiProgram } from '../../models/studijskiprogramModel';
import { MestoService } from '../../Services/mesto.service';
import { DrzavaService } from '../../Services/drzava.service';
import { Drzava } from '../../models/drzava';
import { Mesto } from '../../models/mesto';
import { LoginService } from '../../Services/login.service';
import { RegistrovaniKorisnik } from '../../models/registrovaniKorisnik';
import { RegistrovaniKorisnikService } from '../../Services/registrovaniKorisnici.service';
import { StudentService } from '../../Services/student/student.service';
import { UserPermissionList } from '../../models/pravaPristupa/userPermissionModel';
import { UserPermissionService } from '../../Services/userPermission/user-permission.service';
import { UlogaService } from '../../Services/uloga.service';
import { Uloga } from '../../models/uloga';
import { MatAutocompleteModule, MatAutocomplete } from '@angular/material/autocomplete';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule, MatFormField, MatLabel } from '@angular/material/form-field';
import { map, Observable, startWith } from 'rxjs';
import { MatOption } from "@angular/material/core";
import { AdresaService } from '../../Services/adresa.service';
import { Adresa } from '../../models/adresaModel';
import { UpisStudentaRequest } from '../../models/UpisStudentaRequestModel';


@Component({
  selector: 'app-upisi-se',
  standalone: true,
  imports: [NgFor, NgIf, RouterLink, ReactiveFormsModule, MatFormField, MatLabel, MatOption, MatAutocomplete, AsyncPipe, MatInputModule, MatFormFieldModule, MatAutocompleteModule],
  templateUrl: './upisi-se.component.html',
  styleUrl: './upisi-se.component.css'
})
export class UpisiSeComponent implements OnInit {

  mesta: Mesto[] = [];
  drzave: Drzava[] = [];

  ulice: string[] = [];
  brojevi: string[] = [];

  token = sessionStorage.getItem('token');
  prijavljeniKorisnik: any;
  korisnikIzBaze: RegistrovaniKorisnik | undefined;
  errorMessage: string = '';
  formaZakljucana = false;

  filteredUlice!: Observable<string[]>;
  filteredBrojevi!: Observable<string[]>;
  filteredMesta!: Observable<Mesto[]>;
  filteredMestaPreb!: Observable<Mesto[]>;
  filteredDrzave!: Observable<Drzava[]>;

  form: FormGroup = this.fb.group({
    jmbg: ['', [Validators.required, this.jmbgValidator()]],
    telefon: ['', [Validators.required, Validators.pattern(/^\d{3}\/\d+$/)]],
    ulica: ['', [Validators.required, this.NazivValidator()]],
    broj: ['', [Validators.required]],
    mesto: ['', [Validators.required, this.NazivValidator()]],
    mestoPrebivalista: ['', [Validators.required, this.NazivValidator()]],
    drzava: ['', [Validators.required, this.NazivValidator()]],
  });

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private studijskiProgramService: StudijskiProgramService,
    private adresaService: AdresaService,
    private mestoService: MestoService,
    private drzavaService: DrzavaService,
    private loginService: LoginService,
    private korisnikService: RegistrovaniKorisnikService,
    private studentService: StudentService,
    private userPermissionService: UserPermissionService,
    private ulogaService: UlogaService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.loadDrzave();
    this.loadMesta();
    this.loadAdrese();
    // this.loadKorisnik();
    this.prijavljeniKorisnik = this.loginService.decodeToken(this.token!);
    // console.log("ID prijavljenog korisnika je: ", this.prijavljeniKorisnik.id);
    this.loadKorisnik(this.prijavljeniKorisnik.id)

  }

  addStudent(): void {
  if (this.form.invalid || !this.korisnikIzBaze) return;

  const f = this.form.value;

  const korisnikId = this.korisnikIzBaze.id;
  if (korisnikId == null) {
    this.errorMessage = 'Nedostaje ID korisnika.';
    setTimeout(() => (this.errorMessage = ''), 3000);
    return;
  }

  
  const drzavaObj = this.drzave.find(d => d.naziv === f.drzava) || null;
  const mestoZaAdresu = this.mesta.find(m => m.naziv === f.mesto) || null;
  const mestoPreb = this.mesta.find(m => m.naziv === f.mestoPrebivalista) || null;

  
  const payload: UpisStudentaRequest = {
    korisnikId,
    jmbg: f.jmbg,
    telefon: f.telefon,
    ulica: f.ulica,
    broj: f.broj,

    // Država: ako postoji u listi — pošalji ID; inače bekend će kreirati
    drzavaId: drzavaObj?.id ?? null,
    drzavaNaziv: drzavaObj?.id ? null : (f.drzava ?? null),

    // Mesto (adresa): ID ili naziv
    mestoId: mestoZaAdresu?.id ?? null,
    mestoNaziv: mestoZaAdresu?.id ? null : (f.mesto ?? null),

    // Mesto prebivališta: ID ili naziv (obavezno jedno od ova dva)
    mestoPrebivalistaId: mestoPreb?.id ?? null,
    mestoPrebivalistaNaziv: mestoPreb?.id ? null : (f.mestoPrebivalista ?? null)
  };

  if ((payload.drzavaId == null || isNaN(Number(payload.drzavaId))) && !payload.drzavaNaziv) {
    this.errorMessage = 'Morate uneti ili izabrati državu.';
    setTimeout(() => (this.errorMessage = ''), 3000);
    return;
  }
  // Mesto prebivališta je obavezno: ID ili naziv
  if ((payload.mestoPrebivalistaId == null || isNaN(Number(payload.mestoPrebivalistaId))) && !payload.mestoPrebivalistaNaziv) {
    this.errorMessage = 'Morate uneti ili izabrati mesto prebivališta.';
    setTimeout(() => (this.errorMessage = ''), 3000);
    return;
  }
  
  this.studentService.upisiStudneta(payload).subscribe({
    next: () => {
      this.errorMessage = 'Prijava uspešno poslata!';
      this.form.reset();
      this.formaZakljucana = true;
      setTimeout(() => {
        this.errorMessage = '';
        this.router.navigate(['/obavestenja']);
      }, 2000);
    },
    error: (err) => {
      console.error(err);
      if (err.status === 409) {
        this.errorMessage = err.error?.message || 'Već postoji student sa tim JMBG.';
      } else if (err.status === 404) {
        this.errorMessage = err.error?.message || 'Nedostaju referentni podaci (npr. država/mesto).';
      } else if (err.status === 400) {
        const msg = Array.isArray(err.error) ? err.error.join(', ') : (err.error?.message || 'Neispravni podaci zahteva.');
        this.errorMessage = msg;
      } else {
        this.errorMessage = 'Greška prilikom upisa studenta.';
      }
      setTimeout(() => (this.errorMessage = ''), 3000);
    }
  });
}


  loadMesta(): void {
    this.mestoService.getAll().subscribe(data => {
      this.mesta = data;
      console.log("Mesta:", this.mesta);

      // Tek sada kada su mesta dostupna, setovanje filtera
      this.filteredMesta = this.form.get('mesto')!.valueChanges.pipe(
        startWith(''),
        map(value => this._filterMesta(value || ''))
      );

      this.filteredMestaPreb = this.form.get('mestoPrebivalista')!.valueChanges.pipe(
        startWith(''),
        map(value => this._filterMesta(value || ''))
      );
    });
  }

  loadDrzave(): void {
    this.drzavaService.getAll().subscribe(data => {
      this.drzave = data;
      console.log("Drzave:", this.drzave);

      this.filteredDrzave = this.form.get('drzava')!.valueChanges.pipe(
        startWith(''),
        map(value => this._filterDrzave(value || ''))
      );
    });
  }

  loadAdrese(): void {
    this.adresaService.getAll().subscribe(adrese => {
      this.ulice = [...new Set(adrese.map(a => a.ulica).filter(Boolean))];
      this.brojevi = [...new Set(adrese.map(a => a.broj).filter(Boolean))];


      this.filteredUlice = this.form.get('ulica')!.valueChanges.pipe(
        startWith(''),
        map(value => this._filterUlice(value || ''))
      );

      this.filteredBrojevi = this.form.get('broj')!.valueChanges.pipe(
        startWith(''),
        map(value => this._filterBrojevi(value || ''))
      );
    });
  }

  loadKorisnik(id: number): void {
    this.korisnikService.getOne(id).subscribe(data => {
      this.korisnikIzBaze = data;
      console.log("Registrovani korisnik:", this.korisnikIzBaze);
    });
  }
  private _filterMesta(value: string): Mesto[] {
    const filterValue = value.toLowerCase();
    return this.mesta.filter(option => option.naziv.toLowerCase().includes(filterValue));
  }

  private _filterDrzave(value: string): Drzava[] {
    const filterValue = value.toLowerCase();
    return this.drzave.filter(option => option.naziv.toLowerCase().includes(filterValue));
  }

  private _filterUlice(value: string | null | undefined): string[] {
    const filterValue = (typeof value === 'string' ? value : '').toLowerCase();
    return this.ulice.filter(ulica => ulica.toLowerCase().includes(filterValue));
  }

  private _filterBrojevi(value: string | null | undefined): string[] {
    const filterValue = (typeof value === 'string' ? value : '').toLowerCase();
    return this.brojevi.filter(broj => broj.toLowerCase().includes(filterValue));
  }


  // Prilagođeni validator za JMBG
  private jmbgValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;

      // Proverava da li je unos u formatu JMBG (13 cifara)
      const valid = /^\d{13}$/.test(value);
      return valid ? null : { jmbgInvalid: true };
    };
  }
  //validator za unos drzave mesta i tekstualinih unosa
  private NazivValidator(): ValidatorFn {
  // Pocinje velikim slovom, zatim samo slova ili razmaci (bez cifara i znakova)
  const re = /^\p{Lu}[\p{L} ]*$/u;
  return (control: AbstractControl): ValidationErrors | null => {
    const v: string = (control.value ?? '').trim();
    if (!v) return null; 
    return re.test(v) ? null : { naziv: true };
  };
}
}
