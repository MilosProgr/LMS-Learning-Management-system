import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { NastavnikNaRealizacijiService } from '../../../Services/nastavnikNaRealizaciji.service';
import { LoginService } from '../../../Services/login.service';
import { Nastavnik } from '../../../models/nastavnik.model';
import { Predmet } from '../../../models/predmetModel';
import { forkJoin, map, Observable, startWith } from 'rxjs';
import { NastavnikNaRealizaciji } from '../../../models/nastavnikNaRealizacijiModel';
import { PredmetService } from '../../../Services/predmet.service';
import { RealizacijaPredmetaService } from '../../../Services/realizacija-predmeta.service';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatButtonModule } from '@angular/material/button';
import { NastavnikService } from '../../../Services/nastavnik.service';
import { Semestar } from '../../../models/semestarModel';
import { SemestarService } from '../../../Services/semestar.service';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-dodela-predmeta',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatAutocompleteModule,
    MatButtonModule,
    MatSelectModule
  ],
  templateUrl: './dodela-predmeta.component.html',
  styleUrl: './dodela-predmeta.component.css'
})
//Komponenta za realizaciju predmeta (dodela predmeta nastavniku)
export class DodelaPredmetaRealizacijaComponent implements OnInit {
  loading = false;
  errorMessage = '';

  nastavniciNaRealizaciji: NastavnikNaRealizaciji[] = [];
  predmeti: Predmet[] = [];
  semestri: Semestar[] = [];
  nastavniciById = new Map<number, Nastavnik>();

  filteredNastavnici$!: Observable<NastavnikNaRealizaciji[]>;
  filteredPredmeti$!: Observable<Predmet[]>;

  form = this.fb.group({
    nastavnikNaRealizaciji: new FormControl<NastavnikNaRealizaciji | null>(null, { validators: [Validators.required] }),
    predmet: new FormControl<Predmet | null>(null, { validators: [Validators.required] }),
    semestri: new FormControl<any[]>([], { validators: [Validators.required] })
  });

  constructor(
    private fb: FormBuilder,
    private nnrService: NastavnikNaRealizacijiService,
    private realizacijaPredmetaService: RealizacijaPredmetaService,
    private predmetService: PredmetService,
    private nastavnikService: NastavnikService,
    private semestarService: SemestarService
  ) { }

  ngOnInit(): void {
    const nnrCtrl = this.form.controls.nastavnikNaRealizaciji;
    const prCtrl = this.form.controls.predmet;

    this.filteredNastavnici$ = nnrCtrl.valueChanges.pipe(
      startWith(nnrCtrl.value),
      map(v => this.filterNastavnici(v))
    );
    this.filteredPredmeti$ = prCtrl.valueChanges.pipe(
      startWith(prCtrl.value),
      map(v => this.filterPredmeti(v))
    );

    // Ucitavanje svih podataka odjednom
    forkJoin({
      nnr: this.nnrService.getAll(),
      predmeti: this.predmetService.getAll(),
      nastavnici: this.nastavnikService.getAll(),
      semestri: this.semestarService.getAll()
    }).subscribe({
      next: ({ nnr, predmeti, nastavnici, semestri }) => {
        this.nastavniciNaRealizaciji = nnr ?? [];
        this.predmeti = predmeti ?? [];
        this.semestri = semestri ?? [];
        // console.log(predmeti);

        this.nastavniciById.clear();
        (nastavnici ?? []).forEach(n => {
          if (n?.id != null) this.nastavniciById.set(n.id, n); //kreiranje mape
        });

        // refres autocomplita
        nnrCtrl.setValue(nnrCtrl.value, { emitEvent: true });
        prCtrl.setValue(prCtrl.value, { emitEvent: true });
      },
      error: () => {
        this.errorMessage = 'Greška pri učitavanju podataka, nisu svi podaci ucitani.'
      }
    });
  }

  private fullNameFromNastavnik(n?: Nastavnik | null): string {
    if (!n) return '';
    const ime = n.korisnik?.ime?.trim() ?? '';
    const prezime = n.korisnik?.prezime?.trim() ?? '';
    const ip = `${ime} ${prezime}`.trim();
    return ip || n.poslovniMail || n.jmbg || (n.id != null ? `ID: ${n.id}` : '');
  }

  /** koristi inline nastavnika ako ima korisnika; u suprotnom dopuni iz mape id→nastavnik */
  displayNnr = (nnr?: NastavnikNaRealizaciji | null): string => {
    const inline = (nnr?.nastavnik as Nastavnik) ?? null;
    const enriched = inline?.id != null ? this.nastavniciById.get(inline.id) : undefined;
    return this.fullNameFromNastavnik(enriched ?? inline);
  };

  displayPredmet = (p?: Predmet | null): string => {
    if (!p) return '';
    const naziv = p.naziv?.trim() ?? '';
    const sifra = (p as any)?.sifra?.tekst ? ` • Šifra: ${(p as any).sifra.tekst}` : '';
    const godinaStudija = (p as any)?.godinaStudija?.godina
      ? ` • Godina studija: ${(p as any).godinaStudija.godina}`
      : '';
    return `${naziv}${sifra}${godinaStudija}`;
  };

  displaySemestar(s?: Semestar | null): string {
    if (!s) return '';
    // Prioritetno: naziv; ili oznaka/godina
    return s.tip ?? s.id?.toString() ?? (s.tip != null ? `Semestar (${s.tip})` : `Semestar #${s.id}`);
  }

  // ——— Filteri ———
  private norm(s: string) {
    return (s || '').toLowerCase().normalize('NFKD').replace(/\p{Diacritic}/gu, '');
  }

  private filterNastavnici(val: string | NastavnikNaRealizaciji | null): NastavnikNaRealizaciji[] {
    const term = typeof val === 'string' ? val : this.displayNnr(val);
    const k = this.norm(term);
    if (!k) return this.nastavniciNaRealizaciji.slice(0, 50);
    return this.nastavniciNaRealizaciji.filter(nnr => this.norm(this.displayNnr(nnr)).includes(k));
  }

  private filterPredmeti(val: string | Predmet | null): Predmet[] {
    const term = typeof val === 'string' ? val : this.displayPredmet(val);
    const k = this.norm(term);
    if (!k) return this.predmeti.slice(0, 50);
    return this.predmeti.filter(p => this.norm(this.displayPredmet(p)).includes(k));
  }

  reset(): void {
    this.errorMessage = '';
    this.form.reset();
  }

  dodeli(): void {
    this.errorMessage = '';
    if (this.form.invalid) { this.form.markAllAsTouched(); return; }

    const nnr = this.form.value.nastavnikNaRealizaciji;
    const p = this.form.value.predmet;
    const semestriId = this.form.value.semestri ?? [];
    if (!nnr?.id || !p?.id) {
      this.errorMessage = 'Morate izabrati nastavnika (NNR) i predmet.';
      setTimeout(() => {
        this.errorMessage = ""
      }, 2000);
      return;
    }

    const payload = {
      predmet: { id: p.id },
      nastavnikNaRealizaciji: { id: nnr.id },
      semestri: semestriId.map(s => ({ id: s.id }))
    };

    this.loading = true;
    this.realizacijaPredmetaService.create(payload).subscribe({
      next: () => {
        this.loading = false;
        this.form.reset();
        this.errorMessage = 'Predmet uspešno dodeljen nastavniku.';
        setTimeout(() => {
          this.errorMessage = ""
        }, 2000);
      },
      error: (err) => {
        this.loading = false;
        if (err?.status === 409) {
          this.errorMessage = err.error?.message || 'Ova realizacija već postoji.';
          setTimeout(() => {
            this.errorMessage = ""
          }, 2000);
        } else {
          this.errorMessage = 'Greška pri dodeli predmeta.';
          setTimeout(() => {
            this.errorMessage = ""
          }, 2000);
        }
      }
    });
  }
}