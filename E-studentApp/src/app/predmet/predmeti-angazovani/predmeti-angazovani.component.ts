import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../../Services/login.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NastavnikNaRealizacijiService } from '../../../Services/nastavnikNaRealizaciji.service';
import { RealizacijaPredmetaService } from '../../../Services/realizacija-predmeta.service';
import { RealizacijaPredmeta } from '../../../models/realizacijaPredmetaModel';
import { NgxPaginationModule } from "ngx-pagination";
import { GodinaStudija } from '../../../models/godinaStudija/godinaStudija';
import { Predmet } from '../../../models/predmetModel';
import { StudijskiProgram } from '../../../models/studijskiprogramModel';
import { forkJoin } from 'rxjs';
import { NastavnikNaRealizaciji } from '../../../models/nastavnikNaRealizacijiModel';

@Component({
  selector: 'app-predmeti-angazovani',
  standalone: true,
  imports: [CommonModule, FormsModule, NgxPaginationModule],
  templateUrl: './predmeti-angazovani.component.html',
  styleUrls: ['./predmeti-angazovani.component.css']
})

export class PredmetiAngazovaniComponent implements OnInit {

  errorMessage = '';
  realizacijePredmeta: RealizacijaPredmeta[] = [];
  nastavniciNaRealizaciji: NastavnikNaRealizaciji[] = [];

  prijavljeniKorisnikId = this.loginService.user.id
  trackById = (_: number, item: any) => item?.id ?? _;

  itemsPerPage = 10;
  currentPage = 1;

  mojiNnr: NastavnikNaRealizaciji[] = [];
  mojeRealizacije: RealizacijaPredmeta[] = [];
  lista: any;

  constructor(
    private loginService: LoginService,
    private realizacijaPredmetaService: RealizacijaPredmetaService,
    private nastavnikNaRealizacijiService: NastavnikNaRealizacijiService
  ) { }

  ngOnInit(): void {
    this.ucitajNastavnikeNaRealizaciji();
    this.ucitajRealizacije();
  }
  ucitajRealizacije() {
    this.realizacijaPredmetaService.getAll().subscribe({
      next: (data) => {
        this.realizacijePredmeta = data ?? [];

        // skup NNR id
        const mojiNnrIds = new Set(
          this.mojiNnr.map(n => n.id).filter((id): id is number => typeof id === 'number')
        );

        // filtriraj realizacije koje pripadaju NNR
        this.mojeRealizacije = this.realizacijePredmeta.filter((r: any) => {
          const rid = r?.nastavnikNaRealizaciji?.id ?? r?.nastavnikNaRealizacijiDTO?.id;
          return mojiNnrIds.has(rid);
        });

        console.log('Moje realizacije:', this.mojeRealizacije);
      },
      error: (err) => {
        console.error("Greška u učitavanju realizacija predmeta.", err);
      }
    });
  }

  ucitajNastavnikeNaRealizaciji() {
    this.nastavnikNaRealizacijiService.getAll().subscribe({
      next: (data) => {
        this.nastavniciNaRealizaciji = data ?? [];

        // filtriraj samo moje NNR
        this.mojiNnr = this.nastavniciNaRealizaciji.filter((nnr: any) => {
          const nastavnikId = nnr?.nastavnik?.id;
          const korisnikId = nnr?.nastavnik?.korisnik?.id;
          return nastavnikId === this.prijavljeniKorisnikId || korisnikId === this.prijavljeniKorisnikId;
        });

        console.log('Moji NNR:', this.mojiNnr);
      },
      error: (err) => {
        console.error("Greška u učitavanju nastavnika na realizaciji.", err);
      }
    });
  }

}