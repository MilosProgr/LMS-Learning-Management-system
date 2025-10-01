import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { map, Observable, startWith } from 'rxjs';
import { NastavnikNaRealizacijiService } from '../../Services/nastavnikNaRealizaciji.service';
import { LoginService } from '../../Services/login.service';
import { NastavnikService } from '../../Services/nastavnik.service';
import { Nastavnik } from '../../models/nastavnik.model';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-dodela-nastavnika-na-realizaciji',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,

    MatFormFieldModule,
    MatInputModule,
    MatAutocompleteModule,
    MatButtonModule,
    // MatIconModule,
    MatCardModule,
    RouterLink
  ],
  templateUrl: './dodela-nastavnika-na-realizaciji.component.html',
  styleUrl: './dodela-nastavnika-na-realizaciji.component.css'
})
export class DodelaNastavnikaNaRealizacijiComponent implements OnInit {

  errorMessage = '';
  nastavniciById = new Map<number, Nastavnik>();
  nastavnici: Nastavnik[] = [];
  filteredNastavnici$!: Observable<Nastavnik[]>;

  form = this.fb.group({
    nastavnik: new FormControl<Nastavnik | null>(null, { nonNullable: false, validators: [Validators.required] }),
    brojCasova: [null as number | null, [Validators.required, Validators.min(1), Validators.max(240)]]
  });

  constructor(
    private fb: FormBuilder,
    private nastavnikService: NastavnikService,
    private nastavnikNaRealizacijiService: NastavnikNaRealizacijiService,
    private loginService: LoginService
  ) { }

  ngOnInit(): void {
    this.ucitajNastavnike();
  }

  private ucitajNastavnike(): void {
    this.nastavnikService.getAll().subscribe({
      next: (data) => {
        this.nastavnici = data ?? [];

        // ako je prijavljeni korisnik zapravo nastavnik — auto-selektuj ga
        const prijavljeniId = this.loginService?.user?.id;
        if (prijavljeniId) {
          const ja = this.nastavnici.find(n =>
            n?.id === prijavljeniId || (n?.id === prijavljeniId)
          );
          if (ja) {
            this.form.controls.nastavnik.setValue(ja);
          }
        }

        this.filteredNastavnici$ = this.form.controls.nastavnik.valueChanges.pipe(
          startWith(this.form.controls.nastavnik.value),
          map(val => this.filterNastavnici(val))
        );
      },
      error: () => {
        this.errorMessage = 'Greška pri učitavanju nastavnika.';
      }
    });
  }

  /** Prikaz vrednosti za autocomplete polje */
  displayNastavnik = (n?: Nastavnik | null): string => n ? this.getFullName(n) : '';

  private getFullName(n: Nastavnik): string {
    const ime = n?.korisnik?.ime ?? '';
    const prezime = n?.korisnik?.prezime ?? '';
    const full = `${ime} ${prezime}`.trim();
    return full || n.jmbg || `ID: ${n.id}`;
  }

  private filterNastavnici(val: string | Nastavnik | null): Nastavnik[] {
    const term = (typeof val === 'string' ? val : (val ? this.getFullName(val) : ''))
      .toLowerCase()
      .trim();

    if (!term) return this.nastavnici.slice(0, 50);
    return this.nastavnici.filter(n => this.getFullName(n).toLowerCase().includes(term));
  }

  dodeli(): void {


    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const selected = this.form.value.nastavnik as Nastavnik | null;
    const brojCasova = this.form.value.brojCasova as number | null;

    if (!selected?.id || !brojCasova) return;

    const payload = {
      nastavnik: { id: selected.id },
      brojCasova: brojCasova
    };

    this.nastavnikNaRealizacijiService.create(payload).subscribe({
      next: () => {
        this.form.controls.brojCasova.reset();
        this.errorMessage = 'Dodela uspešno izvršena.';
        setTimeout(() => {
          this.errorMessage = ""
        }, 1000);
      },
      error: (err) => {
        if (err?.status === 409) {
          this.errorMessage = err.error?.message || 'Nastavnik je već dodeljen na ovoj realizaciji.';
          setTimeout(() => {
            this.errorMessage = ""
          }, 1000);
          // } else if (err?.status === 400) {
          //   this.errorMessage = err.error?.message || 'Neispravan zahtev za dodelu.';
        } else {
          this.errorMessage = 'Došlo je do greške pri dodeli.';
          setTimeout(() => {
            this.errorMessage = ""
          }, 1000);
        }
      }
    });
  }

  reset(): void {
    this.errorMessage = '';
    this.form.reset();
  }
}