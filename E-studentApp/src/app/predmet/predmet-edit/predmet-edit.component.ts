// import { CommonModule } from '@angular/common';
// import { Component, Inject, OnInit } from '@angular/core';
// import { FormBuilder, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';

// import { MatFormFieldModule } from '@angular/material/form-field';
// import { MatInputModule } from '@angular/material/input';
// import { MatSelectModule } from '@angular/material/select';
// import { MatCheckboxModule } from '@angular/material/checkbox';
// import { MatAutocompleteModule } from '@angular/material/autocomplete';
// import { MatOptionModule } from '@angular/material/core';
// import { MatButtonModule } from '@angular/material/button';

// import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

// import { map, Observable, startWith } from 'rxjs';

// import { PredmetService } from '../../../Services/predmet.service';
// import { Predmet } from '../../../models/predmetModel';
// import { StudijskiProgram } from '../../../models/studijskiprogramModel';
// import { Sifra } from '../../../models/sifra';
// import { GodinaStudija } from '../../../models/godinaStudija/godinaStudija';
// import { PredmetDialogData } from '../../../models/dijalogModel/dijalogModel';

// @Component({
//   selector: 'app-predmet-edit',
//   standalone: true,
//   imports: [
//     CommonModule,
//     ReactiveFormsModule,
//     MatFormFieldModule,
//     MatInputModule,
//     MatSelectModule,
//     MatCheckboxModule,
//     MatAutocompleteModule,
//     MatOptionModule,
//     MatButtonModule
//   ],
//   templateUrl: './predmet-edit.component.html',
//   styleUrl: './predmet-edit.component.css'
// })
// export class PredmetEditComponent implements OnInit {

//   form = this.fb.group({
//     naziv: ['', Validators.required],
//     esbn: [null as number | null],
//     obavezan: [false, Validators.required],
//     brojPredavanja: [null as number | null],
//     brojVezbi: [null as number | null],
//     drugiObliciNastave: [null as number | null],
//     istrazivackiRad: [null as number | null],
//     ostaliCasovi: [null as number | null],
//     studijskiProgramIds: this.fb.control<number[]>([], { validators: [Validators.required] })
//   });

//   sifraCtrl = new FormControl<Sifra | string | null>(null);
//   godinaCtrl = new FormControl<GodinaStudija | string | null>(null);

//   filteredSifre$!: Observable<Sifra[]>;
//   filteredGodine$!: Observable<GodinaStudija[]>;

//   errorMessage = '';

//   constructor(
//     private fb: FormBuilder,
//     private predmetService: PredmetService,
//     public dialogRef: MatDialogRef<PredmetEditComponent>,
//     @Inject(MAT_DIALOG_DATA) public data: PredmetDialogData
//   ) { }

//   ngOnInit(): void {

//     this.filteredSifre$ = this.sifraCtrl.valueChanges.pipe(
//       startWith(this.sifraCtrl.value),
//       map(val => this.filterSifre(val))
//     );

//     this.filteredGodine$ = this.godinaCtrl.valueChanges.pipe(
//       startWith(this.godinaCtrl.value),
//       map(val => this.filterGodine(val))
//     );

//     if (this.data.mode === 'edit' && this.data.predmet) {

//       const p = this.data.predmet;

//       this.form.patchValue({
//         naziv: p.naziv,
//         esbn: p.esbn ?? null,
//         obavezan: !!p.obavezan,
//         brojPredavanja: p.brojPredavanja ?? null,
//         brojVezbi: p.brojVezbi ?? null,
//         drugiObliciNastave: p.drugiObliciNastave ?? null,
//         istrazivackiRad: p.istrazivackiRad ?? null,
//         ostaliCasovi: p.ostaliCasovi ?? null,
//         studijskiProgramIds: (p.studijskiProgrami || []).map((sp: StudijskiProgram) => sp.id!)
//       });

//       // =======================
//       // SIFRA
//       // =======================
//       if (p.sifra) {
//         const match = this.data.sifre.find(
//           s => s.id === p.sifra?.id
//         );

//         this.sifraCtrl.setValue(
//           match ?? { id: p.sifra.id ?? null, tekst: p.sifra.tekst }
//         );
//       }

//       // =======================
//       // GODINA (FIXED 100%)
//       // =======================
//       if (p.godinaStudija) {

//         const targetId = p.godinaStudija.id ?? null;

//         const matchGodina = this.data.godine.find(
//           g => g.id === targetId
//         );

//         this.godinaCtrl.setValue(
//           matchGodina ?? {
//             id: targetId,
//             godina: p.godinaStudija.godina
//           }
//         );
//       }
//     }
//   }

//   // =======================
//   // DISPLAY
//   // =======================

//   displaySifra = (val?: Sifra | string | null): string =>
//     typeof val === 'string'
//       ? val
//       : val?.tekst ?? (val?.id != null ? `ID: ${val.id}` : '');

//   displayGodina = (val?: GodinaStudija | string | null): string =>
//     typeof val === 'string'
//       ? val
//       : val?.godina != null
//         ? val.godina.toString()
//         : '';

//   // =======================
//   // FILTERS
//   // =======================

//   private filterSifre(val: Sifra | string | null): Sifra[] {
//     const q =
//       typeof val === 'string'
//         ? val.toLowerCase()
//         : (val?.tekst ?? '').toLowerCase();

//     return this.data.sifre.filter(s =>
//       (s.tekst ?? '').toLowerCase().includes(q)
//     );
//   }

//   private filterGodine(val: GodinaStudija | string | null): GodinaStudija[] {
//     const q =
//       typeof val === 'string'
//         ? val.toLowerCase()
//         : (val?.godina?.toString() ?? '');

//     return this.data.godine.filter(g =>
//       g.godina?.toString().includes(q) ||
//       g.id?.toString().includes(q)
//     );
//   }

//   // =======================
//   // SUBMIT
//   // =======================
//   onSubmit(): void {
//     if (this.form.invalid) return;

//     const v = this.form.value;

//     const sifraVal = this.sifraCtrl.value;
//     const godinaVal = this.godinaCtrl.value;

//     const sifraObj =
//       sifraVal && typeof sifraVal !== 'string' && sifraVal.id != null
//         ? { id: sifraVal.id }
//         : undefined;

//     const godinaObj =
//       godinaVal && typeof godinaVal !== 'string' && godinaVal.id != null
//         ? { id: godinaVal.id }
//         : undefined;

//     const dto: Predmet = {
//       id: this.data.predmet?.id,
//       naziv: v.naziv!,
//       esbn: v.esbn ?? undefined,
//       obavezan: !!v.obavezan,
//       brojPredavanja: v.brojPredavanja ?? undefined,
//       brojVezbi: v.brojVezbi ?? undefined,
//       drugiObliciNastave: v.drugiObliciNastave ?? undefined,
//       istrazivackiRad: v.istrazivackiRad ?? undefined,
//       ostaliCasovi: v.ostaliCasovi ?? undefined,

//       studijskiProgrami: (v.studijskiProgramIds || []).map(id => ({
//         id
//       } as StudijskiProgram)),

//       sifra: sifraObj,
//       godinaStudija: godinaObj
//     };

//     const req$ = this.data.mode === 'add'
//       ? this.predmetService.create(dto)
//       : this.predmetService.update(dto.id!, dto);

//     req$.subscribe({
//       next: () => this.dialogRef.close(true),
//       error: (err) => {
//         console.error(err);
//         this.errorMessage = err.error?.message ?? 'Greška';
//       }
//     });
//   }
// }