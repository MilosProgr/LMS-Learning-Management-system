import { Component, Inject } from '@angular/core';
import { IspitniRok } from '../../../models/ispitniRok/ispitniRokModel';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { IspitniRokService } from '../../../Services/ispitniRok/ispitni-rok.service';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { PrijavljeniIspitService } from '../../../Services/prijavljeniIspit.service';

@Component({
  selector: 'app-prijava-ispita-dialog',
  standalone: true,
  imports: [
        CommonModule,
        MatDialogModule,
        MatFormFieldModule,
        MatSelectModule,
        MatButtonModule,
        ReactiveFormsModule
  ],
  templateUrl: './prijava-ispita-dialog.component.html',
  styleUrl: './prijava-ispita-dialog.component.css'
})
export class PrijavaIspitaDialogComponent {
  ispitniRokovi: IspitniRok[] = [];
  loading = false;
  errorMessage = '';

  form = this.fb.group({
    rokId: [null as number | null, Validators.required]
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: {
      predmet: any;
      studentId: number;
      studentNaGodiniId: number;
      cenaPrijave?: number | null;
    },
    private dialogRef: MatDialogRef<PrijavaIspitaDialogComponent>,
    private fb: FormBuilder,
    private ispitniRokService: IspitniRokService,
    private prijavljeniIspitService: PrijavljeniIspitService,
  ) {}

  ngOnInit(): void {
    this.ispitniRokService.getRokoviKojiNisuProsli().subscribe({
      next: (rokovi) => {
        this.ispitniRokovi = rokovi ?? [];
        if (this.ispitniRokovi.length) {
          this.form.patchValue({ rokId: this.ispitniRokovi[0].id! });
        }
      },
      error: () => { console.error("Greska u ucitavanju ispitnih rokova."
      );}
    });
  }

  cancel(): void {
    this.dialogRef.close();
  }

  submit(): void {
    if (this.form.invalid || !this.data?.predmet?.id) return;

    this.loading = true;
    const rokId = this.form.value.rokId!;

    this.prijavljeniIspitService.prijavi({
      studentId: this.data.studentId,
      studentNaGodiniId: this.data.studentNaGodiniId,
      predmetId: this.data.predmet.id,
      ispitniRokId: rokId,
      cenaPrijave: this.data.cenaPrijave ?? null
    }).subscribe({
      next: () => {
        this.dialogRef.close({ success: true });
      },
      error : (err) => {
        if (err.status === 409) {
          this.errorMessage = err.error?.message || 'Greska prijave ispita.';
        }
      }
    });
  }
}
