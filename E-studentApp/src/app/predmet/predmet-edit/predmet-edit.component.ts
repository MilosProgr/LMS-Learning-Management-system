import { CommonModule } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { PredmetService } from '../../../Services/predmet.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Predmet } from '../../../models/predmetModel';
import { StudijskiProgram } from '../../../models/studijskiprogramModel';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatOptionModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { Sifra } from '../../../models/sifra';
import { GodinaStudija } from '../../../models/godinaStudija/godinaStudija';
import { map, Observable, startWith } from 'rxjs';

@Component({
  selector: 'app-predmet-edit',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatCheckboxModule,
    MatAutocompleteModule,
    MatOptionModule,
    MatButtonModule
  ],
  templateUrl: './predmet-edit.component.html',
  styleUrl: './predmet-edit.component.css'
})
export class PredmetEditComponent implements OnInit {
  form = this.fb.group({
    naziv: ['', Validators.required],
    esbn: [null as number | null],
    obavezan: [false, Validators.required],
    brojPredavanja: [null as number | null],
    brojVezbi: [null as number | null],
    drugiObliciNastave: [null as number | null],
    istrazivackiRad: [null as number | null],
    ostaliCasovi: [null as number | null],
    studijskiProgramIds: this.fb.control<number[]>([], { validators: [Validators.required] })
  });

  // autocomplete kontroleri za šifru i godinu (drže objekat ili string tokom kucanja)
  sifraCtrl = new FormControl<Sifra | string | null>(null);
  godinaCtrl = new FormControl<GodinaStudija | string | null>(null);
  
  filteredSifre$!: Observable<Sifra[]>;
  filteredGodine$!: Observable<GodinaStudija[]>;
  errorMessage = ""

  constructor(
    private fb: FormBuilder,
    private predmetService: PredmetService,
    public dialogRef: MatDialogRef<PredmetEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit(): void {

    this.filteredSifre$ = this.sifraCtrl.valueChanges.pipe(
      startWith(this.sifraCtrl.value),
      map(val => this.filterSifre(val))
    );

    this.filteredGodine$ = this.godinaCtrl.valueChanges.pipe(
      startWith(this.godinaCtrl.value),
      map(val => this.filterGodine(val))
    );

    // patch za edit
    if (this.data.mode === 'edit' && this.data.predmet) {
      const p = this.data.predmet;

      this.form.patchValue({
        naziv: p.naziv,
        esbn: p.esbn ?? null,
        obavezan: !!p.obavezan,
        brojPredavanja: p.brojPredavanja ?? null,
        brojVezbi: p.brojVezbi ?? null,
        drugiObliciNastave: p.drugiObliciNastave ?? null,
        istrazivackiRad: p.istrazivackiRad ?? null,
        ostaliCasovi: p.ostaliCasovi ?? null,
        studijskiProgramIds: (p.studijskiProgrami || []).map((sp: StudijskiProgram) => sp.id!)
      });

      if (p.godinaStudija) {
        const matchG = this.data.godine.find((g: GodinaStudija) => g.id === p.godinaStudija!.id);
        this.godinaCtrl.setValue(
          matchG ?? { id: p.godinaStudija.id ?? null, godina: p.godinaStudija.godina }
        );
      }

      // setuj autocomplete vrednosti objektima
      if (p.sifra) {
        const match = this.data.sifre.find((s: Sifra) => s.id === p.sifra?.id);
        console.log(match);
        this.sifraCtrl.setValue(match ?? { id: p.sifra.id!, tekst: p.sifra.tekst });

      }
      if (p.godinaStudija) {
        const matchGodina = this.data.godine.find((g: GodinaStudija) => g.id === p.godinaStudija?.id);
        console.log(matchGodina);

        this.godinaCtrl.setValue(matchGodina ?? { id: p.godinaStudija.id!, naziv: p.godinaStudija.godina });
      }
    }
  }

  // displayWith funkcije
  displaySifra = (val?: Sifra | string | null) =>
    typeof val === 'string'
      ? val
      : (val?.tekst ?? (val?.id != null ? `ID: ${val.id}` : ''));

  displayGodina = (val?: GodinaStudija | string | null): string => {
    if (typeof val === 'string') return val || '';
    if (!val) return '';
    return val.godina != null
      ? val.godina.toString()
      : (val.id != null ? `ID: ${val.id}` : '');
  };

  // filteri
  private filterSifre(val: Sifra | string | null): Sifra[] {
    const q = (typeof val === 'string' ? val : (val?.tekst ?? '')).toLowerCase();
    if (!q) return this.data.sifre;
    return this.data.sifre.filter((s: Sifra) =>
      (s.tekst ?? `ID: ${s.id}`).toLowerCase().includes(q)
    );
  }

  private filterGodine(val: GodinaStudija | string | null): GodinaStudija[] {
    if (!val || typeof val !== 'string') return this.data.godine;

    const q = val.toLowerCase();
    return this.data.godine.filter((g: GodinaStudija) =>
      (g.godina != null && g.godina.toString().includes(q)) ||
      (g.id != null && g.id.toString().includes(q))
    );
  }

  onSubmit(): void {
    if (this.form.invalid) return;

    const v = this.form.value;

    // izvuci iz autocomplete kontrole PRAVI objekat (ne string tokom kucanja)
    const sifraVal = this.sifraCtrl.value as Sifra | string | null;
    const godinaVal = this.godinaCtrl.value as GodinaStudija | string | null;

    const sifraObj: Sifra | undefined =
      sifraVal && typeof sifraVal !== 'string' ? { id: sifraVal.id! } : undefined;

    let godinaObj: { id: number } | undefined;
    if (godinaVal && typeof godinaVal !== 'string' && godinaVal.id != null) {
      godinaObj = { id: godinaVal.id };
    }
    // sastavi DTO po tvom modelu
    const dto: Predmet = {
      id: this.data.predmet?.id,
      naziv: v.naziv!,
      esbn: v.esbn ?? undefined,
      obavezan: !!v.obavezan,
      brojPredavanja: v.brojPredavanja ?? undefined,
      brojVezbi: v.brojVezbi ?? undefined,
      drugiObliciNastave: v.drugiObliciNastave ?? undefined,
      istrazivackiRad: v.istrazivackiRad ?? undefined,
      ostaliCasovi: v.ostaliCasovi ?? undefined,
      studijskiProgrami: (v.studijskiProgramIds || []).map(id => ({ id } as any)),
      sifra: sifraObj ? { id: sifraObj.id } : undefined,
      godinaStudija: godinaObj ? { id: godinaObj.id } as any : undefined
    };

    const req$ = this.data.mode === 'add'
      ? this.predmetService.create(dto as any)
      : this.predmetService.update(dto.id!, dto as any);

    req$.subscribe({
      next: () => this.dialogRef.close(true),
      error: (err) => {
        console.error(err)
        this.errorMessage = err.error?.message



      }
    });
  }
}
