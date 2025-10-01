import { CommonModule } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { SifraService } from '../../../Services/sifra.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Sifra } from '../../../models/sifra';

@Component({
  selector: 'app-sifarnik-edit',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './sifarnik-edit.component.html',
  styleUrls: ['./sifarnik-edit.component.css']
})
export class SifarnikEditComponent implements OnInit {
  form!: FormGroup;
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private sifraService: SifraService,
    private dialogRef: MatDialogRef<SifarnikEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit(): void {
    const sifra = this.data?.sifra;
    this.form = this.fb.group({
      tekst: [sifra?.tekst || '', [Validators.required, Validators.maxLength(255)]]
    });
  }

  onSubmit() {
    if (this.form.invalid) return;

    const payload: Sifra = {
      id: this.data?.mode === 'edit' ? this.data?.sifra?.id : null,
      tekst: this.form.value.tekst
    };

    if (this.data?.mode === 'edit' && payload.id != null) {
      this.sifraService.update(payload.id, payload).subscribe({
        next: res => this.dialogRef.close(res),
        error: err => {
          if (err.status === 409) {
            this.errorMessage = err.error?.message || 'Već postoji zapis sa tim podacima.';
            this.refreshEror()
          }
        }
      });
    } else {
      this.sifraService.create(payload).subscribe({
        next: res => this.dialogRef.close(res),
        error: err => {
          if (err.status === 409) {
            this.errorMessage = err.error?.message || 'Već postoji zapis sa tim podacima.';
            this.refreshEror()
          }
        }
      });
    }
  }

  onCancel() {
    this.dialogRef.close();
  }

  refreshEror() {
    setTimeout(() => {
      this.errorMessage = "";
    }, 2000);
  }
}
