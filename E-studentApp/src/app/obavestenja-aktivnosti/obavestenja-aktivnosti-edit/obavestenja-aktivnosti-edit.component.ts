import { Component, Inject, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  Validators
} from '@angular/forms';

import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

import { ObavestenjaAktivnostiService } from '../../../Services/obavestenja/obavestenjaAktivnosti.service';
import { RegistrovaniKorisnikService } from '../../../Services/registrovaniKorisnici.service';

import { CommonModule } from '@angular/common';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatAutocompleteModule } from '@angular/material/autocomplete';

import { map, Observable, startWith } from 'rxjs';

import { RegistrovaniKorisnik } from '../../../models/registrovaniKorisnik';
import { ObavestenjeAktivnosti } from '../../../models/obavestenja/obavestenjaAktivnostiModel';

@Component({
  selector: 'app-obavestenja-aktivnosti-edit',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatCheckboxModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatAutocompleteModule
  ],
  templateUrl: './obavestenja-aktivnosti-edit.component.html',
  styleUrl: './obavestenja-aktivnosti-edit.component.css'
})
export class ObavestenjaAktivnostiEditComponent implements OnInit {

  form!: FormGroup;

  isSaving = false;

  errorMessage = '';

  registrovaniKorisnici: RegistrovaniKorisnik[] = [];

  filteredKorisnici!: Observable<RegistrovaniKorisnik[]>;

  constructor(
    private fb: FormBuilder,
    private obavestenjaAktivnostiService: ObavestenjaAktivnostiService,
    private registrovaniKorisnikService: RegistrovaniKorisnikService,
    private dialogRef: MatDialogRef<ObavestenjaAktivnostiEditComponent>,

    @Inject(MAT_DIALOG_DATA)
    public data: ObavestenjeAktivnosti
  ) { }

  ngOnInit(): void {

    console.log(this.data);

    const o = this.data?.obavestenje;

    this.form = this.fb.group({
      naziv: [
        o?.naslov ?? '',
        [
          Validators.required,
          firstUppercaseLetter
        ]
      ],

      sadrzaj: [
        o?.sadrzaj ?? '',
        [
          Validators.required,
          firstUppercaseLetter
        ]
      ],

      korisnik: [
        o?.registrovaniKorisnik?.korisnickoIme ?? '',
        [Validators.required]
      ]
    });

    if (this.data?.registrovaniKorisnici?.length > 0) {

      this.registrovaniKorisnici = this.data.registrovaniKorisnici;

      this.initFilterStream();
    }
  }

  private initFilterStream(): void {

    this.filteredKorisnici =
      this.form.get('korisnik')!.valueChanges.pipe(

        startWith(
          this.form.get('korisnik')!.value || ''
        ),

        map((value: string) =>
          this.filterKorisnici(
            (value || '').toLowerCase()
          )
        )
      );
  }

  private filterKorisnici(
    filterValue: string
  ): RegistrovaniKorisnik[] {

    return this.registrovaniKorisnici.filter(k =>
      (k.korisnickoIme || '')
        .toLowerCase()
        .includes(filterValue)
    );
  }

  onSubmit(): void {

    if (this.form.invalid) {
      return;
    }

    this.isSaving = true;

    const v = this.form.value;

    const korisnickoImeInput: string =
      (v.korisnik || '')
        .toString()
        .trim();

    const izabrani =
      this.registrovaniKorisnici.find(k =>
        (k.korisnickoIme || '')
          .toLowerCase()
        === korisnickoImeInput.toLowerCase()
      );

    if (!izabrani) {

      this.form
        .get('korisnik')
        ?.setErrors({
          invalidSelection: true
        });

      this.isSaving = false;

      return;
    }

    const payload: Partial<ObavestenjeAktivnosti> = {

      id:
        this.data?.mode === 'edit'
          ? this.data?.obavestenje?.id
          : undefined,

      naslov: String(v.naziv).trim(),

      sadrzaj: String(v.sadrzaj).trim(),

      registrovaniKorisnik: {
        id: izabrani.id
      }
    };

    const req$ =
      this.data?.mode === 'edit'

        ? this.obavestenjaAktivnostiService.update(
          payload.id!,
          payload as ObavestenjeAktivnosti
        )

        : this.obavestenjaAktivnostiService.create(
          payload as ObavestenjeAktivnosti
        );

    req$.subscribe({

      next: (res) => {

        this.dialogRef.close(res);
      },

      error: (err) => {

        if (err.status === 409) {

          this.errorMessage =
            err.error?.message
            || 'Već postoji zapis sa tim podacima.';

          this.form
            .get('naziv')
            ?.setErrors({
              duplicate: true
            });

          this.form
            .get('sadrzaj')
            ?.setErrors({
              duplicate: true
            });
        }

        this.isSaving = false;
      },

      complete: () => {

        this.isSaving = false;
      }
    });
  }

  onCancel(): void {

    this.dialogRef.close();
  }
}

export function firstUppercaseLetter(
  control: AbstractControl
): ValidationErrors | null {

  const raw = (control.value ?? '').toString();

  if (!raw) {
    return null;
  }

  const v = raw.trimStart();

  if (!v) {
    return null;
  }

  const ch = v[0];

  const isLetter =
    ch.toLowerCase() !== ch.toUpperCase();

  const isUpper =
    ch === ch.toUpperCase();

  return isLetter && isUpper
    ? null
    : { firstUppercase: true };
}