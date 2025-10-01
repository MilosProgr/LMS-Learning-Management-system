import { Component, OnInit } from '@angular/core';
import { PrijavljeniIspit } from '../../models/prijavljenIspit';
import { StudentNaGodini } from '../../models/studentNaGodini/studentNaGodini';
import { PrijavljeniIspitService } from '../../Services/prijavljeniIspit.service';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';
import { EvaluacijaZnanja } from '../../models/evaluacijaZnanja';
import { StudentNaGodiniService } from '../../Services/student/studentNaGodini.service';
import { PohadjanjePredmeta } from '../../models/pohadjanjePredmeta';
import { PohadjanjePredmetaService } from '../../Services/pohadjanje_predmeta.service';
import { RealizacijaPredmetaService } from '../../Services/realizacija-predmeta.service';
import { RealizacijaPredmeta } from '../../models/realizacijaPredmetaModel';
import { LoginService } from '../../Services/login.service';
import { NastavnikNaRealizacijiService } from '../../Services/nastavnikNaRealizaciji.service';
import { NastavnikService } from '../../Services/nastavnik.service';
import { Nastavnik } from '../../models/nastavnik.model';
import { NastavnikNaRealizaciji } from '../../models/nastavnikNaRealizacijiModel';
import { UpisBodovaDialogComponent } from './upis-bodova-dialog/upis-bodova-dialog.component';
import { UpisOceneDialogComponent } from './upis-ocene-dialog/upis-ocene-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { TipEvaluacije } from '../../models/tipEvaluacije';
import { TipEvaluacijeService } from '../../Services/tip-evaluacije.service';
import { IshodService } from '../../Services/ishodi.service';
import { Ishod } from '../../models/ishodModel';
import { InstrumentEvaluacijeService } from '../../Services/fileInstrumentEvaluacije.service';
import { FileInstrumentEvaluacije } from '../../models/FileInstrumentEvaluacijeModel';
import { PredmetService } from '../../Services/predmet.service';
import { Predmet } from '../../models/predmetModel';
import { forkJoin } from 'rxjs';
import { EvaluacijaZnanjaService } from '../../Services/evaluacija-znanja.service';

@Component({
  selector: 'app-upis-ocena',
  standalone: true,
  imports: [
    CommonModule,
    NgxPaginationModule,


  ],
  templateUrl: './upis-ocena.component.html',
  styleUrl: './upis-ocena.component.css'
})
export class UpisOcenaComponent implements OnInit {

  prijavljeniIspiti: PrijavljeniIspit[] = [];
  filtriraniPrijavljeniIspiti: PrijavljeniIspit[] = [];

  studentNaGodini: StudentNaGodini[] = [];
  evaluzacijaZnanja: EvaluacijaZnanja[] = [];
  realizacijePredmeta: RealizacijaPredmeta[] = [];
  pohadjanjaPredmeta: PohadjanjePredmeta[] = [];
  tipoviEvaluacije: TipEvaluacije[] = [];
  ishodi: Ishod[] = [];
  instrumentiEvaluacije: FileInstrumentEvaluacije[] = [];
  private predmeti: Predmet[] = [];

  private prijavljeniKorisnikId?: number = this.loginService.user?.id;
  private prijavljeniNastavnikNaRealizaciji: NastavnikNaRealizaciji | undefined;
  private realizacijaPredmetaNastavnika: RealizacijaPredmeta[] = [];

  private bodoviByPrijava = new Map<number, number>(); // prijavaId -> zbir bodova
  private ukupnoBodovaZapredmet: number = 0;

  currentPage = 1;
  itemsPerPage = 15;

  constructor(
    private prijavljenIspisService: PrijavljeniIspitService,
    private studentNaGodiniService: StudentNaGodiniService,
    private realizacijaPredmetaService: RealizacijaPredmetaService,
    private nastavnikNaRealizacijiService: NastavnikNaRealizacijiService,
    private tipEvaluacijeService: TipEvaluacijeService,
    private ishodService: IshodService,
    private instrumentEvaluacijeService: InstrumentEvaluacijeService,
    private predmetService: PredmetService,
    private loginService: LoginService,
    private dialog: MatDialog,
    private pohadjanjePredmetaService: PohadjanjePredmetaService,
    private evaluacijaZnanjaService: EvaluacijaZnanjaService
  ) { }

  ngOnInit(): void {
    this.loadAll();

    this.loadEvaluacijeZnanja();
    this.loadInstrumentiEvaluacije();
    this.loadIshodi();
    this.loadPredmeti();
    this.loadTipoviEvaluacije();
  }

  private loadAll(): void {
    this.prijavljeniKorisnikId = this.loginService.user?.id;

    forkJoin({
      prijave: this.prijavljenIspisService.getAll(),
      realizacije: this.realizacijaPredmetaService.getAll(),
      nnrList: this.nastavnikNaRealizacijiService.getAll(),
      sng: this.studentNaGodiniService.getAll(),
      pohadjanjePredmeta: this.pohadjanjePredmetaService.getAll(),
    })
      .subscribe(({ prijave, realizacije, nnrList, sng, pohadjanjePredmeta, }) => {

        this.prijavljeniIspiti = prijave ?? [];
        this.realizacijePredmeta = realizacije ?? [];
        this.studentNaGodini = sng ?? [];
        this.pohadjanjaPredmeta = pohadjanjePredmeta ?? [];

        const nnr = (nnrList ?? []).find(n => n?.nastavnik?.id === this.prijavljeniKorisnikId
        );
        this.prijavljeniNastavnikNaRealizaciji = nnr;
        this.realizacijaPredmetaNastavnika = [...(nnr?.realizacijaPredmeta ?? [])];

        const predmetIds = new Set(
          this.realizacijaPredmetaNastavnika
            .map(r => Number(r?.predmet?.id))
            .filter(id => Number.isFinite(id))
        );

        this.filtriraniPrijavljeniIspiti = (this.prijavljeniIspiti ?? []).filter(pi =>
          predmetIds.has(Number(pi?.predmet?.id))
        );

        console.log(this.prijavljeniIspiti);
        console.log(this.filtriraniPrijavljeniIspiti);
      });
  }

  loadTipoviEvaluacije(): void {
    this.tipEvaluacijeService.getAll().subscribe({
      next: (res) => {
        this.tipoviEvaluacije = Array.isArray(res) ? res : [];
        console.log('Tipovi evaluacije:', this.tipoviEvaluacije);
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  loadIshodi(): void {
    this.ishodService.getAll().subscribe({
      next: (res) => {
        this.ishodi = Array.isArray(res) ? res : [];
        console.log('Ishodi:', this.ishodi);
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  loadInstrumentiEvaluacije(): void {
    this.instrumentEvaluacijeService.getAll().subscribe({
      next: (res) => {
        this.instrumentiEvaluacije = Array.isArray(res) ? res : [];
        console.log('Instrumenti evaluacije:', this.instrumentiEvaluacije);
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  loadPredmeti(): void {
    this.predmetService.getAll().subscribe({
      next: (res) => {
        this.predmeti = Array.isArray(res) ? res : [];
        console.log('Predmeti:', this.predmeti);
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  loadEvaluacijeZnanja(): void {
    this.evaluacijaZnanjaService.getAll().subscribe({
      next: (res) => {
        this.evaluzacijaZnanja = Array.isArray(res) ? res : [];
        console.log('Evaluacije znanja:', this.evaluzacijaZnanja);

        // samo polozeni ishodi
        const polozeni = (this.evaluzacijaZnanja as any[]).filter(e =>
          e?.ishod?.polozeno === true || e?.ishod?.polozeno === 1 || e?.ishod?.polozeno === '1' //Pregled po svim mogucim zbog baze
        );

        // sortiranje po vremenu (najnovije prvo)
        const getTs = (e: any) => {
          const t = Date.parse(e?.vremePocetka ?? e?.vremeZavrsetka);
          return Number.isFinite(t) ? t : 0;
        };
        polozeni.sort((a, b) => getTs(b) - getTs(a));

        // samo NAJNOVIJU evaluaciju za svaki (prijavaId, tipEvaluacijeId)
        const vidjeniKljusevi = new Set<string>(); //set za prve pronadjene
        const poslednjePoKljucu: any[] = [];
        for (const e of polozeni) {
          const pid = Number(e?.prijavljeniIspit?.id ?? e?.prijavljenIspit?.id);
          const tipId = Number(e?.tipEvaluacije?.id);
          if (!Number.isFinite(pid) || !Number.isFinite(tipId)) continue;  //provera da li je pid i tipId stvarno broj (nije NaN)
          const key = `${pid}|${tipId}`;
          if (vidjeniKljusevi.has(key)) continue;
          vidjeniKljusevi.add(key);
          poslednjePoKljucu.push(e);
        }

        // sabiranje bodova po prijavi iz tih najnovijih
        const map = new Map<number, number>();
        for (const e of poslednjePoKljucu) {
          const pid = Number(e?.prijavljeniIspit?.id ?? e?.prijavljenIspit?.id);
          const bod = Number(e?.ostvareniBodovi ?? e?.bodovi ?? 0);
          if (!Number.isFinite(pid)) continue;
          map.set(pid, (map.get(pid) ?? 0) + (Number.isFinite(bod) ? bod : 0));
        }

        this.bodoviByPrijava = map; // punjenje mape
      },
      error: (err) => {
        console.error(err);
        this.bodoviByPrijava = new Map();
      }
    });
  }

  // zbir svih bodova iz EvaluacijaZnanja za datu prijavu
  ukupnoBodova(p: PrijavljeniIspit): number {
    const pid = Number(p?.id);
    if (!Number.isFinite(pid)) return 0;

    const izMape = this.bodoviByPrijava.get(pid);
    if (typeof izMape === 'number') return izMape;
    return 0;
  }

  // uslov za aktivaciju dugmeta "Upiši ocenu"
  mozeOcena(p: PrijavljeniIspit): boolean {
    return this.ukupnoBodova(p) >= 51;
  }

  upisiBodove(prijavaIspita: PrijavljeniIspit) {
    const dialogRef = this.dialog.open(UpisBodovaDialogComponent, {
      width: '400px',
      data: {
        mode: 'bodovi',
        prijavaIspita,
        tipoviEvaluacije: this.tipoviEvaluacije,
        realizacijePredmeta: this.realizacijePredmeta,
        ishodi: this.ishodi,
        instrumentiEvaluacije: this.instrumentiEvaluacije,
        predmeti: this.predmeti
      }
    });
    dialogRef.afterClosed().subscribe(ok => {
      if (ok) {
        this.loadAll();
        this.loadEvaluacijeZnanja();

        this.loadTipoviEvaluacije();
        this.loadIshodi();
        this.loadInstrumentiEvaluacije();
        this.loadPredmeti();

      }
    });
  }

  // dijalog za upis ocene
  upisiOcenu(prijavaIspita: PrijavljeniIspit) {
    const totalBodovi = this.ukupnoBodova(prijavaIspita);
    this.ukupnoBodovaZapredmet = totalBodovi;

    const dialogRef = this.dialog.open(UpisOceneDialogComponent, {
      width: '400px',
      data: {
        mode: 'ocena',
        prijavaIspita,
        studentNaGodini: this.studentNaGodini,
        pohadjanjaPredmeta: this.pohadjanjaPredmeta,
        totalBodovi
      }
    });

    dialogRef.afterClosed().subscribe(ok => {
      if (ok) {
        this.loadAll();
        this.loadEvaluacijeZnanja();

        this.loadTipoviEvaluacije();
        this.loadIshodi();
        this.loadInstrumentiEvaluacije();
        this.loadPredmeti();
      }
    });
  }
}
