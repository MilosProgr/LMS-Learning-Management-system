import { Component, Inject } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { IspitniRokService } from '../../../../Services/ispitniRok/ispitni-rok.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { IspitniRok } from '../../../../models/ispitniRok/ispitniRokModel';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

@Component({
  selector: 'app-ispitni-rok-edit',
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
  templateUrl: './ispitni-rok-edit.component.html',
  styleUrl: './ispitni-rok-edit.component.css'
})
export class IspitniRokEditComponent {
  form!: FormGroup;
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private service: IspitniRokService,
    private dialogRef: MatDialogRef<IspitniRokEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { mode: 'add' | 'edit', rok?: IspitniRok }
  ) { }

  ngOnInit(): void {
    const r = this.data?.rok;

    const pocetakDate = r?.pocetak ? new Date(r.pocetak) : null;
    const krajDate = r?.kraj ? new Date(r.kraj) : null;

    this.form = this.fb.group(
      {
        naziv: [r?.naziv ?? '', [Validators.required, firstUppercaseLetter]],
        redovan: [r?.redovan ?? false],
        datumPocetak: [pocetakDate, [Validators.required]],
        vremePocetak: [pocetakDate ? this.dateToTime(pocetakDate) : '', [Validators.required]],
        datumKraj: [krajDate, [Validators.required]],
        vremeKraj: [krajDate ? this.dateToTime(krajDate) : '', [Validators.required]],
      },
      { validators: [dateRangeValidator] }
    );
  }

  onSubmit() {
    if (this.form.invalid) return;

    const v = this.form.value;
    const start = this.combineDateTime(v.datumPocetak, v.vremePocetak);
    const end = this.combineDateTime(v.datumKraj, v.vremeKraj);


    const payload: IspitniRok = {
      id: this.data?.mode === 'edit' ? (this.data?.rok?.id as number) : 0,
      naziv: v.naziv,
      redovan: !!v.redovan,
      pocetak: start.toISOString(),
      kraj: end.toISOString(),
      realizacijaPredmeta: this.data?.rok?.realizacijaPredmeta ?? [],
      prijavljeniIspiti: this.data?.rok?.prijavljeniIspiti ?? []
    };

    if (this.data?.mode === 'edit') {
      this.service.update(payload.id!, payload).subscribe({
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

  private dateToTime(dt: Date): string {
    const hh = dt.getHours().toString().padStart(2, '0');
    const mm = dt.getMinutes().toString().padStart(2, '0');
    return `${hh}:${mm}`;
  }

  private combineDateTime(date: Date, time: string): Date {
    const [h, m] = (time ?? '00:00').split(':').map((x: string) => parseInt(x, 10) || 0);
    const d = new Date(date);
    d.setHours(h, m, 0, 0);
    return d;
  }
}

// ===== Validatori =====
export function firstUppercaseLetter(control: AbstractControl): ValidationErrors | null {
  const raw = (control.value ?? '').toString();
  if (!raw) return null;                 
  const v = raw.trimStart();
  if (!v) return null;
  const ch = v[0];
  const isLetter = ch.toLowerCase() !== ch.toUpperCase(); // radi i za ŠĐČĆŽ
  const isUpper = ch === ch.toUpperCase();
  return isLetter && isUpper ? null : { firstUppercase: true };
}

export function dateRangeValidator(group: AbstractControl): ValidationErrors | null {
  const dp = group.get('datumPocetak')?.value as Date | null;
  const tp = group.get('vremePocetak')?.value as string | null;
  const dk = group.get('datumKraj')?.value as Date | null;
  const tk = group.get('vremeKraj')?.value as string | null;

  if (!dp || !tp || !dk || !tk) return null;

  const start = new Date(dp);
  const [ph, pm] = tp.split(':').map(v => parseInt(v, 10) || 0);
  start.setHours(ph, pm, 0, 0);

  const end = new Date(dk);
  const [kh, km] = tk.split(':').map(v => parseInt(v, 10) || 0);
  end.setHours(kh, km, 0, 0);

  return end > start ? null : { rangeInvalid: true };
}

