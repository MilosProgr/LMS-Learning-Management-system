import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { EvaluacijaZnanja } from '../../../models/evaluacijaZnanja';
import { EvaluacijaZnanjaService } from '../../../Services/evaluacija-znanja.service';
import { TipEvaluacijeService } from '../../../Services/tip-evaluacije.service';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { PohadjanjePredmeta } from '../../../models/pohadjanjePredmeta';
import { TipEvaluacije } from '../../../models/tipEvaluacije';
import { Ishod } from '../../../models/ishodModel';
import { FileInstrumentEvaluacije } from '../../../models/FileInstrumentEvaluacijeModel';
import { RealizacijaPredmeta } from '../../../models/realizacijaPredmetaModel';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { map, Observable, startWith } from 'rxjs';
import { Predmet } from '../../../models/predmetModel';

@Component({
  selector: 'app-upis-bodova-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatCheckboxModule,
    MatAutocompleteModule
  ],
  templateUrl: './upis-bodova-dialog.component.html',
  styleUrl: './upis-bodova-dialog.component.css'
})
export class UpisBodovaDialogComponent implements OnInit {
  form!: FormGroup;
  errorMessage = ""

  postojećiFajlovi: { id: number; opis: string }[] = [];
  postojećiIshodi: { id: number; opis: string }[] = [];

  pohadjanjePredmeta: PohadjanjePredmeta[] = [];
  tipoviEvaluacije: TipEvaluacije[] = [];
  ishodi: Ishod[] = [];
  instrumentiEvaluacije: FileInstrumentEvaluacije[] = [];
  evaluzacijaZnanja: EvaluacijaZnanja[] = [];
  realizacijePredmeta: RealizacijaPredmeta[] = [];
  predmeti: Predmet[] = [];

  createNewInstrument = false;
  createNewIshod = false;

  filteredPredmeti$!: Observable<Predmet[]>;

  constructor(
    private fb: FormBuilder,
    private evaluacijaZnanjaService: EvaluacijaZnanjaService,
    private dialogRef: MatDialogRef<UpisBodovaDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit(): void {
    console.log(this.data);
    this.pohadjanjePredmeta = this.data.pohadjanjePredmeta
    this.tipoviEvaluacije = this.data.tipoviEvaluacije
    this.ishodi = this.data.ishodi
    this.instrumentiEvaluacije = this.data.instrumentiEvaluacije
    this.realizacijePredmeta = this.data.realizacijePredmeta
    this.predmeti = this.data.predmeti
    console.log("Predmeti", this.predmeti);
    

    this.form = this.fb.group({
      tipEvaluacijeId: [null, [Validators.required]],
      realizacijaPredmetaId: [this.data.prijava?.realizacijaPredmetaId ?? null, [Validators.required]],
      ostvareniBodovi: [0, [Validators.required, Validators.min(0)]],

      // instrument (postojeći ili novi)
      instrumentFileId: [null],
      instrumentOpis: [''],
      instrumentUrl: [''],

      // ishod (postojeći ili novi)
      ishodId: [null],
      ishodOpis: [''],
      ishodPredmet: [null],
      ishodPolozeno: [false],
    });
    this.predmeti = Array.from(this.predmeti);

    this.setupPredmetFilter();

  }

  displayPredmet = (value: any): string => {
    if (value && typeof value === 'object') {
      return value.naziv ?? '';
    }
    if (typeof value === 'string') {
      return value; // dok korisnik kuca
    }
    return '';
  };

  private setupPredmetFilter() {
    this.filteredPredmeti$ = this.form.get('ishodPredmet')!.valueChanges.pipe(
      startWith(this.form.get('ishodPredmet')!.value ?? ''),
      map((value: string | Predmet | null) =>
        typeof value === 'string' ? value : value?.naziv ?? ''
      ),
      map(name => {
        const q = name.toLowerCase();
        return this.predmeti.filter(p => (p.naziv ?? '').toLowerCase().includes(q));
      })
    );
  }

  toggleCreateInstrument(v: boolean) {
    this.createNewInstrument = v;
    if (v) {
      // resetuj ID kad kreiraš novi
      this.form.patchValue({ instrumentFileId: null });
      // i validiraj opis/url kao obavezno
      this.form.get('instrumentOpis')?.setValidators([Validators.required, Validators.maxLength(255)]);
      this.form.get('instrumentUrl')?.setValidators([Validators.required, Validators.maxLength(2048)]);
    } else {
      // vraćamo se na izbor ID-ja
      this.form.patchValue({ instrumentOpis: '', instrumentUrl: '' });
      this.form.get('instrumentOpis')?.clearValidators();
      this.form.get('instrumentUrl')?.clearValidators();
    }
    this.form.get('instrumentOpis')?.updateValueAndValidity();
    this.form.get('instrumentUrl')?.updateValueAndValidity();
  }

  toggleCreateIshod(v: boolean) {
    this.createNewIshod = v;
    if (v) {
      this.form.patchValue({ ishodId: null });
      this.form.get('ishodOpis')?.setValidators([Validators.required]);
    } else {
      this.form.patchValue({ ishodOpis: '' });
      this.form.get('ishodOpis')?.clearValidators();
    }
    this.form.get('ishodOpis')?.updateValueAndValidity();
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    // složi telo po dogovorenom “request” formatu
    const f = this.form.value;

    const body: any = {
      prijavljeniIspitId: this.data.prijavaIspita.id,
      tipEvaluacijeId: f.tipEvaluacijeId,
      realizacijaPredmetaId: f.realizacijaPredmetaId,
      ostvareniBodovi: f.ostvareniBodovi,
    };

    // instrument: ili postojeći ID ili kreiraj novi iz (opis,url)
    if (this.createNewInstrument) {
      if (f.instrumentOpis) body.instrumentOpis = f.instrumentOpis;
      if (f.instrumentUrl) body.instrumentUrl = f.instrumentUrl;
    } else if (f.instrumentFileId) {
      body.instrumentFileId = f.instrumentFileId;
    }

    // ishod: ili postojeći ID ili kreiraj novi iz (opis[, predmetId])
    if (this.createNewIshod) {
      if (f.ishodOpis) body.ishodOpis = f.ishodOpis;
      const predmet: Predmet | null = f.ishodPredmet ?? null;
      if (predmet?.id) body.ishodPredmetId = predmet.id; // backend će ga iskoristiti
      body.ishodPolozeno = !!f.ishodPolozeno;
    } else if (f.ishodId) {
      body.ishodId = f.ishodId;
    }

    this.evaluacijaZnanjaService.kreirajEvaluaciju(body)
      .subscribe({
        next: (res) => this.dialogRef.close(true),
        error: (err) => {
          console.error(err);
          if (err.status === 409) {
            this.errorMessage = err.error?.message || 'Već postoji zapis sa tim podacima.';
          }
          if (err.status === 404) {
            this.errorMessage = err.error?.message || 'Doslo je do greske jer fali neki podatat.';
          }

        }
      });
  }

  cancel(): void {
    this.dialogRef.close(false);
  }


  ocistiErrorRegister() {
    setTimeout(() => {
      this.errorMessage = ""
    }, 2000);
  }

}