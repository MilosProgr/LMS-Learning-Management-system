import { CommonModule } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { TipEvaluacijeService } from '../../../Services/tip-evaluacije.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TipEvaluacije } from '../../../models/tipEvaluacije';

@Component({
  selector: 'app-tip-evaluacije-edit',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './tip-evaluacije-edit.component.html',
  styleUrls: ['./tip-evaluacije-edit.component.css']
})
export class TipEvaluacijeEditComponent implements OnInit {
  form!: FormGroup;
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private tipService: TipEvaluacijeService,
    private dialogRef: MatDialogRef<TipEvaluacijeEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit(): void {
    const tip = this.data?.tip;
    this.form = this.fb.group({
      naziv: [tip?.naziv || '', [Validators.required, Validators.maxLength(100)]]
    });
  }

  onSubmit() {
    if (this.form.invalid) return;

    const payload: TipEvaluacije = {
      id: this.data?.mode === 'edit' ? this.data?.tip?.id : null,
      naziv: this.form.value.naziv
    };

    if (this.data?.mode === 'edit' && payload.id != null) {
      this.tipService.update(payload.id, payload).subscribe({
        next: res => this.dialogRef.close(res),
        error: err => {
          if (err.status === 409) {
            this.errorMessage = err.error?.message || 'Već postoji zapis sa tim podacima.';
          }
        }
      });
    } else {
      this.tipService.create(payload).subscribe({
        next: res => this.dialogRef.close(res),
        error: err => {
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
