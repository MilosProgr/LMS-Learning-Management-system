import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { MatButtonModule } from '@angular/material/button';
import {
  MAT_DIALOG_DATA,
  MatDialogModule,
  MatDialogRef
} from '@angular/material/dialog';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';

import { PohadjanjePredmetaService } from '../../../Services/pohadjanje_predmeta.service';
import { UpisOceneDialogData } from '../../../models/upisOcenaDialog/upisOcenaDialog';


@Component({
  selector: 'app-upis-ocene-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule
  ],
  templateUrl: './upis-ocene-dialog.component.html',
  styleUrl: './upis-ocene-dialog.component.css'
})
export class UpisOceneDialogComponent implements OnInit {

  form!: FormGroup;

  errorMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<UpisOceneDialogComponent>,
    private pohadjanjePredmetaService: PohadjanjePredmetaService,

    @Inject(MAT_DIALOG_DATA)
    public data: UpisOceneDialogData
  ) { }

  get mozeUpis(): boolean {
    return (this.data?.totalBodovi ?? 0) >= 51;
  }

  ngOnInit(): void {

    const total = Number(this.data?.totalBodovi ?? 0);

    const autoOcena = this.bodoviUOcenu(total);

    this.form = this.fb.group({
      konacnaOcena: [
        autoOcena,
        [Validators.required]
      ]
    });
  }

  private bodoviUOcenu(bodovi: number): number {

    if (bodovi < 51) {
      return 5;
    }

    if (bodovi <= 60) {
      return 6;
    }

    if (bodovi <= 70) {
      return 7;
    }

    if (bodovi <= 80) {
      return 8;
    }

    if (bodovi <= 90) {
      return 9;
    }

    return 10;
  }

  submit(): void {

    this.errorMessage = null;

    if (!this.mozeUpis) {
      this.errorMessage =
        'Upis ocene nije dozvoljen: nedovoljno bodova.';
      return;
    }

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const ocena = Number(this.form.value.konacnaOcena);

    const sngId =
      this.data.prijavaIspita?.studentNaGodini?.id;

    const predmetId =
      this.data.prijavaIspita?.predmet?.id;

    if (!sngId || !predmetId) {
      this.errorMessage =
        'Nedostaju podaci o studentu ili predmetu.';
      return;
    }

    const pohadjanja =
      this.data.pohadjanjaPredmeta ?? [];

    const pp = pohadjanja.find(p =>
      p.studentNaGodini?.id === sngId &&
      p.predmet?.id === predmetId
    );

    console.log('Pronadjeno pohadjanje:', pp);

    if (!pp?.id) {
      this.errorMessage =
        'Nije pronađen zapis pohađanja za ovog studenta i predmet.';
      return;
    }

    this.pohadjanjePredmetaService
      .patchOcenaById(pp.id, ocena)
      .subscribe({

        next: () => {

          this.errorMessage =
            'Ocena uspešno upisana.';

          setTimeout(() => {
            this.dialogRef.close(true);
          }, 1000);
        },

        error: (err: HttpErrorResponse) => {

          const backendMsg =
            err?.error?.message ??
            (typeof err?.error === 'string'
              ? err.error
              : undefined) ??
            err?.message;

          switch (err.status) {

            case 409:
              this.errorMessage =
                backendMsg ??
                'Sukob podataka (409).';
              break;

            default:
              this.errorMessage =
                backendMsg ??
                'Došlo je do greške pri upisu ocene.';
          }
        }
      });
  }

  cancel(): void {
    this.dialogRef.close(false);
  }
}