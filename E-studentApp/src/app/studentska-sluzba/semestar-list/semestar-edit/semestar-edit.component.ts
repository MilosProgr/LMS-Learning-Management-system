import { CommonModule } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { SemestarService } from '../../../../Services/semestar.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Semestar } from '../../../../models/semestarModel';

@Component({
  selector: 'app-semestar-edit',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,

    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  templateUrl: './semestar-edit.component.html',
  styleUrl: './semestar-edit.component.css'
})
export class SemestarEditComponent {
  form!: FormGroup;
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private semestarService: SemestarService,
    private dialogRef: MatDialogRef<SemestarEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { mode: 'add' | 'edit', semestar?: Semestar }
  ) {}

  ngOnInit(): void {
    const s = this.data?.semestar;

    const poc = s?.datumPocetka ? new Date(s.datumPocetka as any) : null;
    const krj = s?.datumKraja   ? new Date(s.datumKraja as any)   : null;

    this.form = this.fb.group(
      {
        tip: [s?.tip ?? '', [Validators.required, Validators.maxLength(50), firstUppercaseLetter]],
        datumPocetak: [poc, [Validators.required]],
        vremePocetak: [poc ? this.dateToTime(poc) : '', [Validators.required]],
        datumKraj: [krj, [Validators.required]],
        vremeKraj: [krj ? this.dateToTime(krj) : '', [Validators.required]],
      },
      { validators: [dateRangeValidator] }
    );
  }

  onSubmit(): void {
    if (this.form.invalid) return;

    const v = this.form.value;
    const start = this.combineDateTime(v.datumPocetak, v.vremePocetak);
    const end = this.combineDateTime(v.datumKraj, v.vremeKraj);

    const payload: Semestar = {
      id: this.data?.mode === 'edit' ? (this.data?.semestar?.id ?? null) : null,
      tip: v.tip,
      datumPocetka: start.toISOString(),
      datumKraja: end.toISOString()
    } as any;

    if (this.data?.mode === 'edit' && payload.id != null) {
      this.semestarService.update(payload.id as number, payload).subscribe({
        next: (res) => this.dialogRef.close(res),
        error: (err) => {
          console.error(err);
          this.errorMessage = err.status === 409
            ? (err.error?.message || 'Već postoji zapis sa tim podacima.')
            : 'Došlo je do greške pri izmeni semestra.';
        }
      });
    } else {
      this.semestarService.create(payload).subscribe({
        next: (res) => this.dialogRef.close(res),
        error: (err) => {
          console.error(err);
          this.errorMessage = err.status === 409
            ? (err.error?.message || 'Već postoji zapis sa tim podacima.')
            : 'Došlo je do greške pri dodavanju semestra.';
        }
      });
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }

  // Helper funkcije
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

