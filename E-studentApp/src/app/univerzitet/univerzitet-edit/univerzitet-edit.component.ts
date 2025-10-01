import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router, RouterLink, RouterModule } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { UniverzitetService } from '../../../Services/univerzitet.service';
import { CommonModule } from '@angular/common';
import { AdresaService } from '../../../Services/adresa.service';
import { Adresa } from '../../../models/adresaModel';
import { MestoService } from '../../../Services/mesto.service';
import { NastavnikService } from '../../../Services/nastavnik.service';
import { DrzavaService } from '../../../Services/drzava.service';
import { Mesto } from '../../../models/mesto';
import { Drzava } from '../../../models/drzava';
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatAutocompleteModule } from "@angular/material/autocomplete";
import { map, Observable, startWith } from 'rxjs';

@Component({
  selector: 'app-univerzitet-edit',
  standalone: true,
  templateUrl: './univerzitet-edit.component.html',
  styleUrls: ['./univerzitet-edit.component.css'],
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatButtonModule,
    MatAutocompleteModule
  ]
})
export class UniverzitetEditComponent implements OnInit {

  adrese: Adresa[] = [];
  nastavniciBezUniverziteta: any[] = [];
  mesta: Mesto[] = [];
  drzave: Drzava[] = [];
  // univerzitetData: Univerzitet | null = null;
  form!: FormGroup;
  errorMessage: string = '';


  filteredMesta!: Observable<Mesto[]>;
  filteredUlice!: Observable<string[]>;
  filteredBrojevi!: Observable<string[]>;
  filteredDrzave!: Observable<Drzava[]>;

  ulice: any[] = [];
  brojevi: any[] = [];


  constructor(
    private fb: FormBuilder,
    private univerzitetService: UniverzitetService,
    private mestoService: MestoService, private nastavnikService: NastavnikService,
    private adresaService: AdresaService, private drzavaService: DrzavaService,
    private route: ActivatedRoute,
    private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialogRef: MatDialogRef<UniverzitetEditComponent>
  ) { }

  ngOnInit(): void {
    console.log(this.data);
    this.mesta = this.data?.mesta || [];
    this.adrese = this.data?.adrese || [];
    this.drzave = this.data?.drzave || [];
    this.nastavniciBezUniverziteta = this.data?.nastavniciBezUniverziteta || [];
    const univerzitet = this.data?.univerzitet;
    console.log(univerzitet);

    this.ulice = [...new Set(this.adrese.map(a => a.ulica).filter(Boolean))];
    this.brojevi = [...new Set(this.adrese.map(a => a.broj).filter(Boolean))];
    console.log(this.ulice);
    console.log(this.brojevi);

    this.form = this.fb.group({
      naziv: [univerzitet?.naziv || '', [Validators.required, this.NazivValidator()]],
      datumOsnivanja: [univerzitet?.datumOsnivanja || '', [Validators.required, this.noFutureDateValidator()]],
      ulica: [univerzitet?.adresa?.ulica || '', [Validators.required, this.NazivValidator()]],
      broj: [univerzitet?.adresa?.broj || '', [Validators.required]],
      opis: [univerzitet?.opis || '', [Validators.required, Validators.minLength(15), this.opisValidator()]],
      mesto: [univerzitet?.adresa?.mesto?.id || '', [Validators.required, this.NazivValidator()]],
      rektor: [univerzitet?.rektor?.id || '', [Validators.required]],
      drzava: [univerzitet?.adresa?.mesto?.drzava?.id || '', [Validators.required, this.NazivValidator()]],

      adresaId: [univerzitet?.adresa?.id || null],
    });


    this.filteredUlice = this.form.get('ulica')!.valueChanges.pipe(
      startWith(''),
      map(value => this._filterUlice(value || ''))
    );

    this.filteredBrojevi = this.form.get('broj')!.valueChanges.pipe(
      startWith(''),
      map(value => this._filterBrojevi(value || ''))
    );

    this.filteredMesta = this.form.get('mesto')!.valueChanges.pipe(
      startWith(''),
      map(value => this._filterMesta(value || ''))
    );

    this.filteredDrzave = this.form.get('drzava')!.valueChanges.pipe(
      startWith(''),
      map(value => this._filterDrzave(value || ''))
    );

  }

  dodajUniverzitet() {
    if (this.form.invalid) return;
    const f = this.form.value;

    const payload = {
      // univerzitet
      naziv: f.naziv,
      opis: f.opis,
      datumOsnivanja: f.datumOsnivanja,
      rektorId: f.rektor,

      // adresa
      ulica: f.ulica,
      broj: f.broj,

      // Država/Mesto — saljes naziv, moze i id
      drzavaNaziv: f.drzava,
      mestoNaziv: f.mesto
    };

    this.univerzitetService.createUniverzitet(payload).subscribe({
      next: (res) => this.dialogRef.close(res),
      error: (err) => {
        if (err.status === 409) {
          this.errorMessage = err.error?.message || 'Desio se konflikt u podacima.';
          this.refreshError();
        }
        else if (err.status === 400) {
          this.errorMessage =  'Nedostaje neki od podataka.';
          this.refreshError();
        }
        else {
          this.errorMessage = 'Greška prilikom dodavanja univerziteta.'
          this.refreshError();
        }
      }
    });
  }
  izmeniUniverzitet() {
    if (this.form.invalid) return;
    const f = this.form.value;
    const id = this.data?.univerzitet?.id;

    const payload = {
      naziv: f.naziv,
      opis: f.opis,
      datumOsnivanja: f.datumOsnivanja,
      rektorId: f.rektor,
      ulica: f.ulica,
      broj: f.broj,
      drzavaNaziv: f.drzava,
      mestoNaziv: f.mesto
    };

    this.univerzitetService.updateUniverzitet(id, payload).subscribe({
      next: (res) => this.dialogRef.close(res),
      error: (err) => {
        if (err.status === 409) {
          this.errorMessage = err.error?.message || 'Desio se konflikt u podacima.';
          this.refreshError();
        }
        else if (err.status === 400) {
          this.errorMessage =  'Nedostaje neki od podataka.';
          this.refreshError();
        }
        else {
          this.errorMessage = 'Greška prilikom dodavanja univerziteta.'
          this.refreshError();
        }
      }
    });
  }

  private _filterUlice(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.ulice.filter(ulica => ulica.toLowerCase().includes(filterValue));
  }

  private _filterBrojevi(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.brojevi.filter(broj => broj.toLowerCase().includes(filterValue));
  }

  private _filterMesta(value: string): any[] {
    const filterValue = value.toLowerCase();
    return this.mesta.filter(mesto => mesto.naziv.toLowerCase().includes(filterValue));
  }

  private _filterDrzave(value: string): any[] {
    const filterValue = value.toLowerCase();
    return this.drzave.filter(drzava => drzava.naziv.toLowerCase().includes(filterValue));
  }

  onSubmit() {
    if (this.form.valid) {
      if (this.data?.mode === 'edit') {
        this.izmeniUniverzitet();
      } else {
        this.dodajUniverzitet();
      }
    }
  }

  onCancel() {
    this.dialogRef.close();
  }

  displayMesto = (id: number | string): string => {
    if (id == null || id === '') return '';
    const mesto = this.mesta.find(m => m.id === Number(id));
    return mesto ? mesto.naziv : '';
  };

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

  // validator za OPIS: Pocinje velikim slovom, zatim slova/cifre/razmaci/tacka
  private opisValidator(): ValidatorFn {
    const re = /^\p{Lu}[\p{L}\p{N}\s.]*$/u;
    return (control: AbstractControl): ValidationErrors | null => {
      const v: string = (control.value ?? '').trim();
      if (!v) return null; // 'required' hvata prazno
      return re.test(v) ? null : { opis: true };
    };
  }

  //Kod za sprecavanje unosa datuma u losem formatu
  today: Date = new Date();
  // filter koji sprecava klik na buduce dane u kalendaru
  pastDatesOnly = (d: Date | null): boolean => {
    const date = d ? new Date(d) : new Date();
    date.setHours(0, 0, 0, 0);
    const today = new Date();
    today.setHours(0, 0, 0, 0);
    return date <= today;
  };

  // validator: datum ne sme biti u budućnosti
  private noFutureDateValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const v = control.value;
      if (!v) return null; // 'required' rešava prazno

      // Ako koristiš MatNativeDateModule, v je Date; ako koristiš moment adapter, uradi v.toDate()
      const date = v instanceof Date ? new Date(v) : new Date(v);
      if (isNaN(date.getTime())) return null;

      const today = new Date();
      today.setHours(0, 0, 0, 0);
      date.setHours(0, 0, 0, 0);

      return date > today ? { futureDate: true } : null;
    };
  }

    private refreshError() {
    setTimeout(() => {
      this.errorMessage = ""
    }, 2000);
  }
}
