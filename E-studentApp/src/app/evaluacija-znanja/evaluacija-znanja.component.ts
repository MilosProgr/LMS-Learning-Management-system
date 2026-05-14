import { Component, OnInit } from '@angular/core';
import { PrijavljeniIspit } from '../../models/prijavljenIspit';
import { StudentNaGodini } from '../../models/studentNaGodini/studentNaGodini';
import { PrijavljeniIspitService } from '../../Services/prijavljeniIspit.service';
import { PohadjanjePredmeta } from '../../models/pohadjanjePredmeta';
import { NgClass, NgFor } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';
import { TipEvaluacije } from '../../models/tipEvaluacije';
import { TipEvaluacijeService } from '../../Services/tip-evaluacije.service';
import { FormsModule } from '@angular/forms';
import { EvaluacijaZnanjaService } from '../../Services/evaluacija-znanja.service';
import { EvaluacijaZnanja } from '../../models/evaluacijaZnanja';

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

  selectedPrijavljeniIspit: PrijavljeniIspit | null = null;

  tipoviEvaluacije: TipEvaluacije[] = [];
  selectedTipEvaluacije: TipEvaluacije | null = null;

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

  prijavljeiIspiti(): void {

    this.prijavljenIspitService.getAll().subscribe(data => {

      this.prijavljeniIspiti = data;

      console.log(
        'Prijavljeni Ispiti: ',
        this.prijavljeniIspiti
      );

      this.prijavljeniIspiti.forEach(ispit => {

        if (ispit.StudentNaGodini) {

          console.log(
            'Student na godini: ',
            ispit.StudentNaGodini
          );

        } else {

          console.log(
            'Student na godini nije prisutan'
          );

        }

      });

    });

  }

  loadTipovi(): void {

    this.tipEvaluacijeService.getAll().subscribe(data => {

      this.tipoviEvaluacije = data;

      console.log(
        'Tipovi evaluacije: ',
        this.tipoviEvaluacije
      );

    });

  }

  openModal(prijavljeniIspit: PrijavljeniIspit): void {

    this.selectedPrijavljeniIspit = prijavljeniIspit;

  }

  UpisiBodove(): void {

    console.log(
      'Selected PrijavljeniIspit ID: ',
      this.selectedPrijavljeniIspit?.id
    );

    const prijavljeniIspitId = this.selectedPrijavljeniIspit?.id;
    const predmetId = this.selectedPrijavljeniIspit?.predmet?.id;
    const tipEvaluacijeId = this.selectedTipEvaluacije?.id;

    if (
      this.selectedPrijavljeniIspit &&
      this.bodovi !== null &&
      this.selectedTipEvaluacije &&
      prijavljeniIspitId != null &&
      predmetId != null &&
      tipEvaluacijeId != null
    ) {

      const evaluacijaZnanja: EvaluacijaZnanja = {

        bodovi: this.bodovi,

        ishod: {

          id: predmetId,

          predmet: {
            id: predmetId
          }

        },

        tipEvaluacije: {

          id: tipEvaluacijeId

        },

        prijavljeniIspit: {

          id: prijavljeniIspitId

        }

      };

      this.evaluacijaZnanjaService
        .create(evaluacijaZnanja)
        .subscribe(response => {

          console.log(
            'Evaluacija znanja kreirana: ',
            response
          );

        });

    } else {

      console.log(
        'Nisu uneti svi potrebni podaci'
      );

    }

  }

}