import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, ValidatorFn, ValidationErrors, AbstractControl } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FakultetService } from '../../../Services/fakultet.service';
import { AdresaService } from '../../../Services/adresa.service';
import { MestoService } from '../../../Services/mesto.service';
import { NastavnikService } from '../../../Services/nastavnik.service';
import { DrzavaService } from '../../../Services/drzava.service';
import { UniverzitetService } from '../../../Services/univerzitet.service';
import { Mesto } from '../../../models/mesto';
import { Drzava } from '../../../models/drzava';
import { Adresa } from '../../../models/adresaModel';
import { Univerzitet } from '../../../models/univerzitetModel';
import { Nastavnik } from '../../../models/nastavnik.model';
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatAutocompleteModule } from "@angular/material/autocomplete";
import { map, Observable, startWith } from 'rxjs';

@Component({
  selector: 'app-fakultet-edit',
  standalone: true,
  templateUrl: './fakultet-edit.component.html',
  styleUrls: ['./fakultet-edit.component.css'],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatAutocompleteModule
  ]
})
export class FakultetEditComponent implements OnInit {
  mesta: Mesto[] = [];
  drzave: Drzava[] = [];
  adrese: Adresa[] = [];
  univerziteti: Univerzitet[] = [];
  nastavniciBezFakulteta: any[] = [];
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
    private fakultetService: FakultetService,
    private mestoService: MestoService,
    private nastavnikService: NastavnikService,
    private adresaService: AdresaService,
    private drzavaService: DrzavaService,
    private univerzitetService: UniverzitetService,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialogRef: MatDialogRef<FakultetEditComponent>
  ) { }

  ngOnInit(): void {
    // Preuzmi podatke iz data (prosleđeno iz liste)
    console.log(this.data);

    this.mesta = this.data?.mesta || [];
    this.adrese = this.data?.adrese || [];
    this.drzave = this.data?.drzave || [];
    this.univerziteti = this.data?.univerziteti || [];
    this.nastavniciBezFakulteta = this.data?.nastavniciBezFakulteta || [];
    const fakultet = this.data?.fakultet;
    console.log(this.nastavniciBezFakulteta);

    this.ulice = [...new Set(this.adrese.map(a => a.ulica).filter(Boolean))];
    this.brojevi = [...new Set(this.adrese.map(a => a.broj).filter(Boolean))];

    this.form = this.fb.group({
      naziv: [fakultet?.naziv || '', [Validators.required, this.NazivValidator()]],
      kontakt: [fakultet?.kontakt || '', [Validators.required, Validators.pattern(/^\d{3}\/\d+$/)]],
      ulica: [fakultet?.adresa?.ulica || '', [Validators.required, this.NazivValidator()]],
      broj: [fakultet?.adresa?.broj || '', [Validators.required]],
      opis: [fakultet?.opis || '', [Validators.required, Validators.minLength(15), this.opisValidator()]],
      mesto: [fakultet?.adresa?.mesto?.id || '', [Validators.required, this.NazivValidator()]],
      univerzitet: [fakultet?.univerzitet?.id || '', [Validators.required]],
      dekan: [fakultet?.dekan?.id || '', [Validators.required]],
      drzava: [fakultet?.adresa?.mesto?.drzava?.id || '', [Validators.required, this.NazivValidator()]],
      adresaId: [fakultet?.adresa?.id || null]
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

  dodajFakultet() {
    if (this.form.invalid) return;
    const f = this.form.value;

    const payload = {
      naziv: f.naziv,
      opis: f.opis,
      telefon: f.kontakt,
      dekanId: f.dekan,
      univerzitetId: f.univerzitet,
      ulica: f.ulica,
      broj: f.broj,
      drzavaNaziv: f.drzava,        // ili drzavaId
      mestoNaziv: f.mesto           // ili mestoId
    };

    this.fakultetService.createFakultet(payload).subscribe({
      next: res => this.dialogRef.close(res),
      error: (err) => {
        console.log("status: ", err.status);

        console.log("MojError", err);
        if (err.status === 409) {
          this.errorMessage = err.error?.message || 'Desio se konflikt u podacima.';
          this.refreshError();
        }
        else if (err.status === 400) {
          this.errorMessage = 'Nedostaje neki od podataka.';
          this.refreshError();
        }
        else {
          this.errorMessage = 'Greška prilikom dodavanja fakulteta.'
          this.refreshError();
        }
      }
    });
  }

  izmeniFakultet() {
    if (this.form.invalid) return;
    const f = this.form.value;
    const id = this.data?.fakultet?.id;

    const payload = {
      naziv: f.naziv,
      opis: f.opis,
      telefon: f.kontakt,
      dekanId: f.dekan,
      univerzitetId: f.univerzitet,
      ulica: f.ulica,
      broj: f.broj,
      drzavaNaziv: f.drzava,
      mestoNaziv: f.mesto
    };

    this.fakultetService.updateFakultet(id, payload).subscribe({
      next: res => this.dialogRef.close(res),
      error: (err) => {
        console.log("MojError", err);
        if (err.status === 409) {
          this.errorMessage = err.error?.message || 'Desio se konflikt u podacima.';
          this.refreshError();
        }
        else if (err.status === 400) {
          this.errorMessage = 'Nedostaje neki od podataka.';
          this.refreshError();
        }
        else {
          this.errorMessage = 'Greška prilikom dodavanja fakulteta.'
          this.refreshError();
        }
      }
    });
  }

  onSubmit() {
    if (this.form.valid) {
      if (this.data?.mode === 'edit') {
        this.izmeniFakultet();
      } else {
        this.dodajFakultet();
      }
    }
  }

  onCancel() {
    this.dialogRef.close();
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

  private refreshError() {
    setTimeout(() => {
      this.errorMessage = ""
    }, 2000);
  }
}
