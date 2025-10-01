import { CommonModule } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ObavestenjeService } from '../../../Services/obavestenja/obavestenje.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Obavestenje } from '../../../models/obavestenja/obavestenjeModel';

@Component({
  selector: 'app-obavestenja-edit',
  standalone: true,
  imports: [    
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatNativeDateModule],
  templateUrl: './obavestenja-edit.component.html',
  styleUrl: './obavestenja-edit.component.css'
})
export class ObavestenjeEditComponent implements OnInit {
  form!: FormGroup;
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private service: ObavestenjeService,
    private dialogRef: MatDialogRef<ObavestenjeEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { mode: 'add' | 'edit', obavestenje?: Obavestenje }
  ) {}

  ngOnInit(): void {
    const o = this.data?.obavestenje;
    this.form = this.fb.group({
      naslov: [o?.naslov ?? '', [Validators.required, firstUppercaseLetter]],
      tekst:  [o?.tekst ?? '',  [Validators.required, firstUppercaseLetter]],
    });
  }

  onSubmit() {
    if (this.form.invalid) return;

    const value = this.form.value;
    const payload: Obavestenje = {
      id: this.data?.mode === 'edit' ? (this.data?.obavestenje?.id as number) : 0,
      naslov: value.naslov,
      tekst: value.tekst
    };

    if (this.data?.mode === 'edit') {
      this.service.update(payload.id, payload).subscribe({
        next: (res) => this.dialogRef.close(res),
        error: (err) => {
          if (err.status === 409) {
            this.errorMessage = err.error?.message || 'Već postoji zapis sa tim podacima.';
          }
        }
      });
    } else {
      this.service.create(payload).subscribe({
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
//funkcija za validaciju naziva i teksta
export function firstUppercaseLetter(control: AbstractControl): ValidationErrors | null {
  const raw = (control.value ?? '').toString();
  if (!raw) return null;                 // prazno će hvatati Validators.required
  const v = raw.trimStart();             // ignoriši vodeće whitespace karaktere
  if (!v) return null;

  const ch = v[0];
  const isLetter = ch.toLowerCase() !== ch.toUpperCase(); // radi i za ŠĐČĆŽ itd.
  const isUpper  = ch === ch.toUpperCase();

  return isLetter && isUpper ? null : { firstUppercase: true };
}