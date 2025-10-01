import { CommonModule } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { PohadjanjePredmetaService } from '../../../Services/pohadjanje_predmeta.service';

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
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  get mozeUpis(): boolean {
    return (this.data?.totalBodovi ?? 0) >= 51;
  }

  ngOnInit(): void {

    const total = Number(this.data?.totalBodovi ?? 0);
    const autoOcena = this.bodoviUOcenu(total); // izračunata ocena
    this.form = this.fb.group({
      konacnaOcena: [autoOcena, [Validators.required]]
    });
  }

  private bodoviUOcenu(b: number): number {
    if (b < 51) return 5;
    if (b <= 60) return 6;
    if (b <= 70) return 7;
    if (b <= 80) return 8;
    if (b <= 90) return 9;
    return 10;
  }

  submit() {
    if (!this.mozeUpis) {
      this.errorMessage = 'Upis ocene nije dozvoljen: nedovoljno bodova.';
      return;
    }
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const ocena = this.form.value.konacnaOcena as number;
    const sngId = Number(this.data.prijavaIspita?.studentNaGodini?.id);
    const predmetId = Number(this.data.prijavaIspita?.predmet?.id);

    const pohadjanja = (this.data.pohadjanjaPredmeta ?? []) as Array<{
      id: number;
      studentNaGodini?: { id?: number };
      predmet?: { id?: number };
    }>;

    // pronađi pohađanje (student + predmet)
    const pp = pohadjanja.find(p =>
      Number(p.studentNaGodini?.id) === sngId &&
      Number(p.predmet?.id) === predmetId
    );

    if (!pp?.id) {
      this.errorMessage = 'Nije pronađen zapis pohađanja za ovog studenta i predmet.';
      return;
    }

    this.pohadjanjePredmetaService
      .patchOcenaById(Number(pp.id), ocena)
      .subscribe({
        next: () => {
          this.errorMessage = "Ocena uspesno upisana."
          setTimeout(() => {
            this.dialogRef.close(true)
          }, 1000);
        },
        error: (err: any) => {
          const backendMsg =
            err?.error?.message ??
            (typeof err?.error === 'string' ? err.error : undefined) ??
            err?.message;
          switch (err?.status) {
            // case 404:
            //   this.errorMessage = backendMsg || 'Zapis nije pronađen (404).';
            //   break;
            case 409:
              this.errorMessage = backendMsg || 'Sukob podataka (409).';
              break;
            default:
              this.errorMessage = backendMsg || 'Došlo je do greške pri upisu ocene.';
          }
        }
      });
  }

  cancel() {
    this.dialogRef.close(false);
  }
}
