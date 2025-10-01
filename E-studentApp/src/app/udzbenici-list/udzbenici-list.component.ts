import { Component, OnInit } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { UdzbenikService } from '../../Services/udzbenik.service';
import { PredmetService } from '../../Services/predmet.service';
import { Predmet } from '../../models/predmetModel';
import { Udzbenik } from '../../models/udzbenik.model';
import { StudijskiProgramService } from '../../Services/studijski-program.service';
import { StudijskiProgram } from '../../models/studijskiprogramModel';

@Component({
  selector: 'app-udzbenici-list',
  templateUrl: './udzbenici-list.component.html',
  styleUrls: ['./udzbenici-list.component.css'],
  standalone: true,
  imports: [NgFor, NgIf, ReactiveFormsModule, FormsModule]
})
export class UdzbeniciListComponent implements OnInit {

  udzbenici: Udzbenik[] = [];
  predmetList: Predmet[] = [];
  programi: StudijskiProgram[] = [];
  selectedProgramId: number | null = null;

  newUdzbenikForm: FormGroup;
  errorMessage = '';
  
  constructor(
    private udzbenikService: UdzbenikService,
    private predmetService: PredmetService,
    private programService: StudijskiProgramService,
    private fb: FormBuilder,
    private router: Router
  ) {
    this.newUdzbenikForm = this.fb.group({
      naziv: ['', Validators.required],
      predmetId: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadUdzbenici();
    this.loadStudijskiProgrami();
  }

  loadUdzbenici(): void {
    this.udzbenikService.getAll().subscribe(udzbenici => {
      this.udzbenici = udzbenici;
      
      console.log('Fetched udzbenici:', this.udzbenici);

      this.udzbenici.forEach(udz => {
        console.log('Predmet for udzbenik', udz.id, ':', udz.predmet);
        console.log('GodinaStudija:', udz.predmet?.godinaStudija);
      });
    });
  }


  loadStudijskiProgrami(): void {
    this.programService.getAll().subscribe(programi => {
      this.programi = programi;
    });
  }

  onProgramChange(): void {
    if (this.selectedProgramId) {
      this.predmetService.getPredmetiByProgram(this.selectedProgramId)
        .subscribe(predmeti => this.predmetList = predmeti);
    } else {
      this.predmetList = [];
    }
  }

  addUdzbenik(): void {
    if (this.newUdzbenikForm.valid) {
      const predmetId = this.newUdzbenikForm.value.predmetId;
      const selectedPredmet = this.predmetList.find(p => p.id === predmetId);

      if (!selectedPredmet) {
        this.errorMessage = 'Izaberite validan predmet';
        return;
      }

      const payload: Udzbenik = {
        naziv: this.newUdzbenikForm.value.naziv,
        predmet: {
          id: selectedPredmet.id,
          godinaStudija: { id: selectedPredmet.godinaStudija?.id }
        } as Predmet
      };

      this.udzbenikService.create(payload).subscribe(() => {
        this.errorMessage = 'Udžbenik uspešno dodat!';
        this.loadUdzbenici();
        this.newUdzbenikForm.reset();
        this.predmetList = [];
        this.selectedProgramId = null;

        setTimeout(() => this.errorMessage = '', 2000);
      });
    }
  }

  deleteUdzbenik(id: number | undefined): void {
    if (!id) return;

    if (confirm('Da li ste sigurni da želite obrisati udžbenik?')) {
      this.udzbenikService.delete(id).subscribe({
        next: () => this.loadUdzbenici(),
        error: (err) => {
          console.error('Greška prilikom brisanja udžbenika', err);
          this.errorMessage = 'Greška prilikom brisanja udžbenika';
          setTimeout(() => this.errorMessage = '', 3000);
        }
      });
    }
  }

}
