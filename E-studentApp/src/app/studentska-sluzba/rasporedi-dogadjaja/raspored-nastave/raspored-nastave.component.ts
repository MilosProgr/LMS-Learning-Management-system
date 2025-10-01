// import { Component, OnInit } from '@angular/core';
// import { StudijskiProgramService } from '../../../../Services/studijski-program.service';
// import { StudijskiProgram } from '../../../../models/studijskiprogramModel';
// import { PredmetService } from '../../../../Services/predmet.service';
// import { Predmet } from '../../../../models/predmetModel';
// import { TipNastave } from '../../../../models/tipNastave';
// import { TipNastaveService } from '../../../../Services/tip-nastave.service';
// import { TerminNastave } from '../../../../models/terminNastaveModel';
// import { TerminNastaveService } from '../../../../Services/termin-nastave.service';
// import { FormField } from '../../../generics/generic-form/form-model';
// import { Validators } from '@angular/forms';
// import { CommonModule } from '@angular/common';
// import { GenericReusableTableComponent } from '../../../generics/generic-reusable-table/generic-reusable-table.component';
// import { GenericFormComponent } from '../../../generics/generic-form/generic-form.component';
// import { RealizacijaPredmeta } from '../../../../models/realizacijaPredmetaModel';
// import { RealizacijaPredmetaService } from '../../../../Services/realizacija-predmeta.service';
// import { concatMap, toArray } from 'rxjs/operators';
// import { from } from 'rxjs';

// @Component({
//   selector: 'app-raspored-nastave',
//   standalone: true,
//   imports: [CommonModule, GenericReusableTableComponent, GenericFormComponent],
//   templateUrl: './raspored-nastave.component.html',
//   styleUrl: './raspored-nastave.component.css'
// })

// export class RasporedNastaveComponent implements OnInit {
//   ngOnInit(): void {
//     this.loadStudijskiProgram(),
//       this.loadPredmeti(),
//       this.loadRealizacije()
//   }

//   studijskiProgrami: StudijskiProgram[] = []; // Array to store available programs
//   predmeti: Predmet[] = [];
//   realizacijaPredmeta: RealizacijaPredmeta[] = [];
//   terminiNastave: TerminNastave[] = [];
//   tipoviNastave: TipNastave[] = [];

//   selectedProgramId: number | undefined;
//   selectedPredmetId: number | undefined;
//   selectedRealizacijaId: number | undefined;
//   selectedTerminId: number | null | undefined;
//   selectedTipId: number | null = null;



//   isFormVisible: boolean = false;
//   currentStep: number = 1;

//   kljucevi: string[] = [];
//   kljuceviPredmet: string[] = [];

//   formData: any = {
//     studijskiProgram: null,
//     predmet: null,
//     realizacijaPredmeta: null,
//     termini: [],
//     tipNastave: []
//   };

//   constructor(
//     private studijskiProgramService: StudijskiProgramService,
//     private predmetService: PredmetService,
//     private terminNastaveService: TerminNastaveService,
//     private tipNastaveService: TipNastaveService,
//     private serviceRealizacija: RealizacijaPredmetaService
//   ) { }

//   loadStudijskiProgram(): void {
//     this.studijskiProgramService.getAll().subscribe(sprogram => {
//       this.studijskiProgrami = sprogram;
//       if (this.studijskiProgrami.length > 0) {
//         this.kljucevi = Object.keys(this.studijskiProgrami[0])
//       }
//     })
//   }

//   addStudijskiProgram(newProgramData: Partial<StudijskiProgram> = {}): void {
//     const newProgram: StudijskiProgram = {
//       id: newProgramData.id,
//       naziv: newProgramData.naziv,
//       fakultet: {
//         id: 1,
//       }
//     };
//     this.studijskiProgramService.create(newProgram).subscribe(addedProgram => {
//       this.studijskiProgrami.push(addedProgram);
//       this.selectedProgramId = addedProgram.id;
//     });
//   }


//   loadPredmeti(): void {
//     this.predmetService.getAll().subscribe(predmet => {
//       this.predmeti = predmet;
//       if (this.predmeti.length > 0) {
//         this.kljuceviPredmet = Object.keys(this.predmeti[0])
//       }
//       console.log(this.predmeti)
//     })
//   }

//   addPredmet(newPredmetData: Partial<Predmet> = {}): void {
//     const newPredmet: Predmet = {
//       id: 0,
//       naziv: newPredmetData.naziv || 'Novi Predmet',
//       esbn: newPredmetData.esbn || 123456,
//       obavezan: newPredmetData.obavezan || true,
//       brojPredavanja: newPredmetData.brojPredavanja || 30,
//       brojVezbi: newPredmetData.brojVezbi || 15,
//       drugiObliciNastave: newPredmetData.drugiObliciNastave || 5,
//       istrazivackiRad: newPredmetData.istrazivackiRad || 10,
//       ostaliCasovi: newPredmetData.ostaliCasovi || 3,
//       kursevi: [],
//       studijskiProgrami: [{ id: this.selectedProgramId!, naziv: '' }]
//     };
//     this.predmetService.create(newPredmet).subscribe(addedPredmet => {
//       this.predmeti.push(addedPredmet);
//       this.selectedPredmetId = addedPredmet.id;
//     });
//   }

//   loadRealizacije() {
//     this.serviceRealizacija.getAll().subscribe(realizacija => {
//       this.realizacijaPredmeta = realizacija
//       console.log("Ovo su realizacije: ", realizacija)
//     })
//   }

//   addRealizacija(newRealizacijaData: Partial<RealizacijaPredmeta> = {}): void {
//     const newRealizacija: RealizacijaPredmeta = {
//       id: newRealizacijaData.id,
//       predmet: this.predmeti.find(p => p.id === this.selectedPredmetId)!,
//       terminNastave: []
//     };
//     this.serviceRealizacija.create(newRealizacija).subscribe(addedRealizacija => {
//       this.realizacijaPredmeta.push(addedRealizacija);
//       this.selectedRealizacijaId = addedRealizacija.id;
//     });
//   }



//   loadTerminNastave(): void {
//     this.terminNastaveService.getAll().subscribe(termini => {
//       this.terminiNastave = termini;
//     });
//   }

//   addTermin(terminData: Partial<TerminNastave> = {}): void {
//     const newTermin: TerminNastave = {
//       id: 0,
//       vremePocetka: terminData.vremePocetka,
//       vremeKraja: terminData.vremeKraja,
//       tipNasstave: { id: 1, naziv: 'Predavanje' }
//     };
//     this.terminNastaveService.create(newTermin).subscribe(addedTermin => {
//       this.terminiNastave.push(addedTermin);
//       this.selectedTerminId = addedTermin.id;
//     });
//   }

//   loadTipNastave(): void {
//     this.tipNastaveService.getAll().subscribe(tipovi => {
//       this.tipoviNastave = tipovi;
//     });
//   }

//   addTipNastave(nastava: Partial<TipNastave> = {}): void {
//     const newTip: TipNastave = {
//       id: nastava.id,
//       naziv: nastava.naziv
//     }
//     this.tipNastaveService.create(newTip).subscribe(addedTip => {
//       this.tipoviNastave.push(addedTip);
//       this.selectedTerminId = addedTip.id;
//     });
//   }

//   onProgramSelect(event: any): void {
//     const selectedProgramId = event.target.value;

//     if (selectedProgramId) {
//       this.loadPredmetByProgram(selectedProgramId);
//     }
//   }

//   loadPredmetByProgram(programId: number) {
//     this.predmetService.getPredmetiByProgram(programId).subscribe
//       (idprogram => {
//         this.predmeti = idprogram;
//         if (this.predmeti.length > 0) {
//           // Call loadTerminAndTip for each fetched predmet
//           this.loadTerminAndTipForPredmeti(this.predmeti);
//         }

//       })
//   }



//   loadTerminAndTipForPredmeti(predmeti: Predmet[]): void {
//     // Create an observable from the array of predmeti
//     from(predmeti).pipe(
//       concatMap(predmet =>
//         this.serviceRealizacija.getRealizacijeWithTerminiAndTipNastave(predmet.id!)
//       ),
//       toArray()
//     ).subscribe({
//       next: (results) => {
//         this.realizacijaPredmeta = results.flat();
//         console.log('Realizacije sa terminima i tipovima nastave: ', this.realizacijaPredmeta);
//       },
//       error: (error) => {
//         console.error('Error fetching realizacije: ', error);
//       }
//     });
//   }

//   toggleForm(): void {
//     this.isFormVisible = !this.isFormVisible;
//   }

//   nextStep(): void {

//     this.currentStep++;
//   }







// }
