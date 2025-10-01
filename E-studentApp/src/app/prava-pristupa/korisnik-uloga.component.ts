import { Component, OnInit } from '@angular/core';
import { FormField } from '../generics/generic-form/form-model';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { UlogaService } from '../../Services/uloga.service';
import { CommonModule } from '@angular/common';
import { Uloga } from '../../models/uloga';
import { RegistrovaniKorisnik } from '../../models/registrovaniKorisnik';
import { GenericFormComponent } from '../generics/generic-form/generic-form.component';
import { RegistrovaniKorisnikService } from '../../Services/registrovaniKorisnici.service';
import { SluzbenikStudentskeService } from '../../Services/sluzbenikStudentske/sluzbenikStudentske.service';
import { StudentService } from '../../Services/student/student.service';
import { NastavnikService } from '../../Services/nastavnik.service';
import { AdministratorService } from '../../Services/administrator/administrator.service';
import { UserPermissionList } from '../../models/pravaPristupa/userPermissionModel';
import { UserPermission } from '../../models/pravaPristupa/userPermissionModel';
import { UserPermissionService } from '../../Services/userPermission/user-permission.service';

@Component({
  selector: 'app-korisnik-uloga',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './korisnik-uloga.component.html',
  styleUrl: './korisnik-uloga.component.css'
})
export class KorisnikUlogaComponent implements OnInit {

  uloge: Uloga[] = [];
  registrovaniKorisnici: RegistrovaniKorisnik[] = [];
  korisnikSaUlogomDataList: UserPermissionList[] | null = null;
  errorMessage = '';
  selektovaniKorisnik: UserPermissionList | null = null;
  userPermissionData: UserPermission[] | null = null;

  form!: FormGroup;
  kljuceviKorisnika: string[] = [];

  constructor(
    private registrovaniKorisnikService: RegistrovaniKorisnikService,
    private ulogaService: UlogaService,
    private UserPermissionService: UserPermissionService,
    private fb: FormBuilder,
    private nastavnikService: NastavnikService,
    private studentService: StudentService,
    private administratorService: AdministratorService,
    private sluzbenikStudentskeService: SluzbenikStudentskeService
  ) { }

  ngOnInit(): void {
    this.loadUserPermissionData();
    this.loadKorisniciSaUlogamaLista();
    this.loadKorisnici();
    this.loadUloge();
  }

  formFieldsKorisnikSaUlogom: FormField[] = [
    {
      type: 'select',
      label: 'Izaberi korisnika: ',
      name: 'korisnik',
      validations: [Validators.required]
    },
    {
      type: 'select',
      label: 'Izaberi ulogu: ',
      name: 'uloga',
      validations: [Validators.required]
    }
  ];

  isUlogaSelektovana(ulogaId: number | undefined): boolean {
    return this.selektovaniKorisnik?.listaUloga?.some(u => u.id === (ulogaId ?? 0)) ?? false;
  }

  openModal(korisnik: UserPermissionList) {
    if (!korisnik) {
      console.error("Greška: Pokušaj otvaranja modal-a sa null korisnikom.");
      return;
    }
    this.selektovaniKorisnik = {
      ...korisnik,
      listaUloga: [...(korisnik.listaUloga ?? [])]
    };
    console.log("Selektovani korisnik:", this.selektovaniKorisnik);
  }


  promenaUloge(uloga: Uloga, checked: boolean): void {
    if (!this.selektovaniKorisnik) return;

    if (checked) {
      // Dodavanje uloge
      if (!this.isUlogaSelektovana(uloga.id)) {
        const payload: any = {
          permission: { id: uloga.id },
          korisnik:  { id: this.selektovaniKorisnik.id }
        };

        this.UserPermissionService.create(payload).subscribe({
          next: () => {
            console.log('Korisniku je uspešno dodata uloga.', payload);

            // Lokalno osvezavanje UI odmah
            this.selektovaniKorisnik!.listaUloga = [
              ...(this.selektovaniKorisnik!.listaUloga ?? []),
              { id: uloga.id, ime: uloga.ime } as Uloga
            ];

            // Kreiraj odgovarajući entitet (Student/Admin/Sluzba/Nastavnik)
            this.kreirajEntitetPremaUlogama(this.selektovaniKorisnik?.korisnik, uloga.ime);

            this.errorMessage = "Korisniku je uspešno dodata uloga.";
            this.loadUserPermissionData(); // sinhronizacija sa backend-om
            setTimeout(() => { this.errorMessage = ''; }, 2000);
          },
          error: (err) => console.error('Greška prilikom dodavanja uloge', err)
        });
      }
    } else {
      // Uklanjanje uloge
      const pronadjeni = this.userPermissionData?.find(item =>
        item.korisnik.id === this.selektovaniKorisnik?.id && item.permission?.id === uloga.id
      );

      if (pronadjeni) {
        this.UserPermissionService.delete(pronadjeni.id).subscribe({
          next: () => {
            console.log("Korisniku je uspešno obrisana uloga.");

            // Lokalno uklanjanje
            this.selektovaniKorisnik!.listaUloga =
              (this.selektovaniKorisnik!.listaUloga ?? []).filter(u => u.id !== uloga.id);

            this.ukloniEntitetPremaUlogama(this.selektovaniKorisnik, uloga.ime);

            this.errorMessage = "Korisniku je uspešno obrisana uloga.";
            this.loadUserPermissionData(); // sinhronizacija sa backend-om
            setTimeout(() => { this.errorMessage = ''; }, 2000);
          },
          error: () => {
            console.log("Došlo je do greške u brisanju uloge.");
          }
        });
      } else {
        console.log("Nema objekta u UserPermissionData sa tim korisnikom i ulogom.");
        // Opcionalno: optimistično osvežavanje UI-a
        this.selektovaniKorisnik!.listaUloga =
          (this.selektovaniKorisnik!.listaUloga ?? []).filter(u => u.id !== uloga.id);
      }
    }
  }

  loadKorisniciSaUlogamaLista(): void {
    this.UserPermissionService.getKorisniciSaUlogamaList().subscribe((data) => {
      this.korisnikSaUlogomDataList = data;
      console.log("Registrovani korisnici sa ulogama:", this.korisnikSaUlogomDataList);
    });
  }

  loadUserPermissionData(): void {
    this.UserPermissionService.getAll().subscribe((data) => {
      this.userPermissionData = data;
      console.log("UserPermission data:", this.userPermissionData);
    });
  }

  groupUsersByUsername(data: any[]): any[] {
    const userMap = new Map<string, any>();

    data.forEach(item => {
      const korisnikKey = item.korisnik?.korisnickoIme;
      if (!userMap.has(korisnikKey)) {
        userMap.set(korisnikKey, {
          korisnik: item.korisnik,
          uloge: []
        });
      }
      const existingUser = userMap.get(korisnikKey);
      existingUser.uloge.push(item.permission);
    });

    return Array.from(userMap.values());
  }

  loadKorisnici(): void {
    this.registrovaniKorisnikService.getAll().subscribe((data) => {
      this.registrovaniKorisnici = data;
    });
  }

  loadUloge(): void {
    this.ulogaService.getAll().subscribe((data) => {
      this.uloge = data;
    });
  }

  private kreirajEntitetPremaUlogama(korisnik: any, uloga: string): void {
    let entitet: any;

    console.log("Izabrani korisnik iz funkcije:", korisnik);
    console.log("izabrana Uloga iz funkcije:", uloga);

    switch (uloga) {
      case 'ROLE_STUDENT':
        entitet = {
          id: korisnik.id,
          korisnik: { id: korisnik.id }
        };
        console.log("entitet koji se dodaje u tabelu:", entitet);

        this.studentService.create(entitet).subscribe({
          next: () => console.log('Student je uspešno kreiran.'),
          error: (err) => console.error('Greška prilikom kreiranja studenta', err)
        });
        this.loadKorisniciSaUlogamaLista();
        break;

      case 'ROLE_ADMIN':
        entitet = {
          id: korisnik.id,
          korisnik: { id: korisnik.id }
        };
        console.log("entitet koji se dodaje u tabelu:", entitet);

        this.administratorService.create(entitet).subscribe({
          next: () => console.log('Administrator je uspešno kreiran.'),
          error: (err) => console.error('Greška prilikom kreiranja administratora', err)
        });
        this.loadKorisniciSaUlogamaLista();
        break;

      case 'ROLE_SLUZBA':
        entitet = {
          id: korisnik.id,
          korisnik: { id: korisnik.id }
        };
        console.log("entitet koji se dodaje u tabelu:", entitet);

        this.sluzbenikStudentskeService.create(entitet).subscribe({
          next: () => console.log('Službenik studentske je uspešno kreiran.'),
          error: (err) => console.error('Greška prilikom kreiranja službenika studentske', err)
        });
        this.loadKorisniciSaUlogamaLista();
        break;

      case 'ROLE_NASTAVNIK':
        entitet = {
          id: korisnik.id,
          korisnik: { id: korisnik.id }
        };
        console.log("entitet koji se dodaje u tabelu:", entitet);

        this.nastavnikService.create(entitet).subscribe({
          next: () => console.log('Nastavnik je uspešno kreiran.'),
          error: (err) => console.error('Greška prilikom kreiranja nastavnika', err)
        });
        this.loadKorisniciSaUlogamaLista();
        break;

      default:
        console.log('Nepoznata uloga.');
        break;
    }
  }

  private ukloniEntitetPremaUlogama(korisnik: any, uloga: string): void {
    let entitetId: any;

    switch (uloga) {
      case 'ROLE_STUDENT':
        entitetId = korisnik.id;
        console.log("entitet koji se brise iz tabele:", entitetId);
        this.studentService.delete(entitetId).subscribe({
          next: () => console.log('Student je uspešno obrisan.'),
          error: (err) => console.error('Greška prilikom brisanja studenta', err)
        });
        this.loadKorisniciSaUlogamaLista();
        break;

      case 'ROLE_ADMIN':
        entitetId = korisnik.id;
        console.log("entitet koji se brise iz tabele:", entitetId);
        this.administratorService.delete(entitetId).subscribe({
          next: () => console.log('Administrator je uspešno obrisan.'),
          error: (err) => console.error('Greška prilikom brisanja administratora', err)
        });
        this.loadKorisniciSaUlogamaLista();
        break;

      case 'ROLE_SLUZBA':
        entitetId = korisnik.id;
        console.log("entitet koji se brise iz tabele:", entitetId);
        this.sluzbenikStudentskeService.delete(entitetId).subscribe({
          next: () => console.log('Službenik studentske je uspešno obrisan.'),
          error: (err) => console.error('Greška prilikom brisanja službenika studentske', err)
        });
        this.loadKorisniciSaUlogamaLista();
        break;

      case 'ROLE_NASTAVNIK':
        entitetId = korisnik.id;
        console.log("entitet koji se brise iz tabele:", entitetId);
        this.nastavnikService.delete(entitetId).subscribe({
          next: () => console.log('Nastavnik je uspešno obrisan.'),
          error: (err) => console.error('Greška prilikom brisanja nastavnika', err)
        });
        this.loadKorisniciSaUlogamaLista();
        break;

      default:
        console.log('Nepoznata uloga.');
        break;
    }
  }
}