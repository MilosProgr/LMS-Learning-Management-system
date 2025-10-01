import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { InstrumentEvaluacijeService } from '../../../Services/fileInstrumentEvaluacije.service';
import { FileInstrumentEvaluacije } from '../../../models/FileInstrumentEvaluacijeModel';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-instrument-evaluacije-edit',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './instrument-evaluacije-edit.component.html',
  styleUrl: './instrument-evaluacije-edit.component.css'
})
export class InstrumentEvaluacijeEditComponent implements OnInit {
  form!: FormGroup;
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private instrumentEvaluacijeService: InstrumentEvaluacijeService,
    private dialogRef: MatDialogRef<InstrumentEvaluacijeEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { mode: 'add' | 'edit'; instrument?: FileInstrumentEvaluacije }
  ) { }

  ngOnInit(): void {
    const file = this.data?.instrument;
    console.log(file);
    

    this.form = this.fb.group({
      opis: [file?.opis ?? '', [Validators.maxLength(255)]],
      url: [
        file?.url ?? '',
        [
          Validators.required,
          Validators.maxLength(2048),
          // Jednostavna validacija URL-a
          Validators.pattern(/^https?:\/\/.+/i)
        ]
      ]
    });
  }

  onSubmit() {
    if (this.form.invalid) return;

    const payload: FileInstrumentEvaluacije = {
      id: this.data?.mode === 'edit' ? (this.data.instrument?.id ?? null) : null,
      opis: this.form.value.opis,
      url: this.form.value.url
    };

    if (this.data?.mode === 'edit' && payload.id != null) {
      this.instrumentEvaluacijeService.update(payload.id as number, payload).subscribe({
        next: (res: any) => this.dialogRef.close(res),
        error: (err: { status: number; error: { message: string; }; }) => {
          if (err.status === 409) {
            this.errorMessage = err.error?.message || 'Već postoji zapis sa tim podacima.';
          }
        }
      });
    } else {
      this.instrumentEvaluacijeService.create(payload).subscribe({
        next: (res: any) => this.dialogRef.close(res),
        error: (err: { status: number; error: { message: string; }; }) => {
          if (err.status === 409) {
            this.errorMessage = err.error?.message || 'Već postoji zapis sa tim podacima.';
          }
        }
      });
    }
  }

  onCancel() {
    this.dialogRef.close();
  }
}
