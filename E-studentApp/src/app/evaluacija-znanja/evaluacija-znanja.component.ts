import { Component, OnInit } from '@angular/core';
import { PrijavljeniIspit } from '../../models/prijavljenIspit';
import { StudentNaGodini } from '../../models/studentNaGodini/studentNaGodini';
import { PrijavljeniIspitService } from '../../Services/prijavljeniIspit.service';
import { PohadjanjePredmeta } from '../../models/pohadjanjePredmeta';
import { PohadjanjePredmetaService } from '../../Services/pohadjanje_predmeta.service';
import { NgClass, NgFor } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';
import { GenericFormComponent } from '../generics/generic-form/generic-form.component';
import { TipEvaluacije } from '../../models/tipEvaluacije';
import { TipEvaluacijeService } from '../../Services/tip-evaluacije.service';
import { FormsModule } from '@angular/forms';
import { EvaluacijaZnanjaService } from '../../Services/evaluacija-znanja.service';

@Component({
  selector: 'app-evaluacija-znanja',
  standalone: true,
  imports: [NgFor, NgxPaginationModule, NgClass, FormsModule],
  templateUrl: './evaluacija-znanja.component.html',
  styleUrl: './evaluacija-znanja.component.css'
})
export class EvaluacijaZnanjaComponent implements OnInit {
  currentPage: number = 1;
  itemsPerPage: number = 15;
  prijavljeniIspiti: PrijavljeniIspit[] = [];
  studentNaGodini: StudentNaGodini[] = [];
  pohadjanjePredmeta: PohadjanjePredmeta[] = [];
  selectedStudent: any;
  tipoviEvaluacije: TipEvaluacije[] = [];
  selectedTipEvaluacije: TipEvaluacije | null = null;  // Za praćenje izabrane vrednosti


  // Dodaj bodovi varijablu
  bodovi: number | null = null;






  constructor(
    private prijavljenIspitService: PrijavljeniIspitService,
    private tipEvaluacijeService: TipEvaluacijeService,
    private evaluacijaZnanjaService: EvaluacijaZnanjaService
  ) { }

  ngOnInit(): void {
    this.prijavljeiIspiti();
    this.loadTipovi();
  }


  prijavljeiIspiti() {
    this.prijavljenIspitService.getAll().subscribe(data => {
      this.prijavljeniIspiti = data;

      console.log("Prijavljeni Ispiti: ", this.prijavljeniIspiti);

      // Iterirajte kroz prijavljene ispite i izvučete podatke o studentu na godini
      this.prijavljeniIspiti.forEach(ispit => {
        if (ispit.studentNaGodini) {
          console.log("Student na godini: ", ispit.studentNaGodini);
        } else {
          console.log("Student na godini nije prisutan");
        }
      });
    });
  }

  loadTipovi() {
    this.tipEvaluacijeService.getAll().subscribe(data => {
      this.tipoviEvaluacije = data;
      console.log("Tipovi evaluacije: ", this.tipoviEvaluacije)
    })
  }

  openModal(prijavljeniIspit: PrijavljeniIspit) {
    this.selectedStudent = prijavljeniIspit;

  }

  UpisiBodove() {
    console.log("Selected Student ID: ", this.selectedStudent?.id);  // Debugging log

    if (this.selectedStudent && this.bodovi !== null && this.selectedTipEvaluacije) {

      const evaluacijaZnanja = {
        bodovi: this.bodovi,
        ishod: {
          id: this.selectedStudent.predmet.id,
          predmet: { id: this.selectedStudent.predmet.id }
        },
        tipEvaluacije: {
          id: this.selectedTipEvaluacije.id
        },
        prijavljenIspit: {
          id: this.selectedStudent.id
        }

      };

      this.evaluacijaZnanjaService.create(evaluacijaZnanja).subscribe(response => {
        console.log("Evaluacija znanja kreirana: ", response);
      });
    } else {
      console.log("Nisu uneti svi potrebni podaci");
    }
  }







}
