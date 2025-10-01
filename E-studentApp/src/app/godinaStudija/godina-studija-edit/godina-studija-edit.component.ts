import { CommonModule } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { GodinaStudijaService } from '../../../Services/godinaStudija/godinaStudijaModel.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { GodinaStudija } from '../../../models/godinaStudija/godinaStudija';

@Component({
  selector: 'app-godina-studija-edit',
  standalone: true,
  imports: [
        CommonModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatInputModule,
        MatButtonModule
  ],
  templateUrl: './godina-studija-edit.component.html',
  styleUrl: './godina-studija-edit.component.css'
})
export class GodinaStudijaEditComponent implements OnInit {
  form!: FormGroup;
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private godinaService: GodinaStudijaService,
    private dialogRef: MatDialogRef<GodinaStudijaEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit(): void {
    const godina = this.data?.godina;
    this.form = this.fb.group({
      godina: [godina?.godina || '', [
        Validators.required,
        Validators.pattern(/^[0-9]+$/)   // samo brojevi
      ]]
    });
  }

  onSubmit() {
  if (this.form.invalid) return;

  const payload: GodinaStudija = {
    id: this.data?.mode === 'edit' ? this.data?.godina?.id : null,
    godina: this.form.value.godina
  };

  if (this.data?.mode === 'edit' && payload.id != null) {
    this.godinaService.update(payload.id, payload).subscribe({
      next: (res) => this.dialogRef.close(res),
      error: (err) => {
        if (err.status === 409) {
          this.errorMessage = err.error?.message || 'Već postoji zapis sa tim podacima.';
        }
      }
    });
  } else {
    this.godinaService.create(payload).subscribe({
      next: (res) => this.dialogRef.close(res),
      error: (err) => {
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
