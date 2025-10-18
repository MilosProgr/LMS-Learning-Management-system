import { Component, OnInit } from '@angular/core';
import { Predmet } from '../../models/predmetModel';
import { PredmetService } from '../../Services/predmet.service';
import { Student } from '../../models/student.model';
import { RegistrovaniKorisnik } from '../../models/registrovaniKorisnik';
import { RegistrovaniKorisnikService } from '../../Services/registrovaniKorisnici.service';
import { LoginService } from '../../Services/login.service';
import { NgxPaginationModule } from 'ngx-pagination';
import { CommonModule, DecimalPipe, NgFor, NgIf } from '@angular/common';
import { IspitniRok } from '../../models/ispitniRok/ispitniRokModel';
import { IspitniRokService } from '../../Services/ispitniRok/ispitni-rok.service';
import { StudentNaGodini } from '../../models/studentNaGodini/studentNaGodini';
import { StudentNaGodiniService } from '../../Services/student/studentNaGodini.service';
import { PrijavljeniIspit } from '../../models/prijavljenIspit';
import { PohadjanjePredmeta } from '../../models/pohadjanjePredmeta';
import { PohadjanjePredmetaService } from '../../Services/pohadjanje_predmeta.service';
import { PrijavljeniIspitService } from '../../Services/prijavljeniIspit.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar'
import { forkJoin } from 'rxjs';
import { PrijavaIspitaDialogComponent } from './prijava-ispita-dialog/prijava-ispita-dialog.component';
import { StudentService } from '../../Services/student/student.service';

@Component({
  selector: 'app-prijava-ispita',
  standalone: true,
  imports: [NgxPaginationModule, NgFor, NgIf, DecimalPipe,
    MatButtonModule, MatSnackBarModule, MatDialogModule, MatTooltipModule, MatIconModule
  ],
  templateUrl: './prijava-ispita.component.html',
  styleUrls: ['./prijava-ispita.component.css']
})
export class PrijavaIspitaComponent implements OnInit {
  studentiNaGodini: StudentNaGodini[] = [];
  studenti: Student[] = [];
  prijavljeniIspiti: PrijavljeniIspit[] = [];
  stanjeNaRacunu: number | null = null;

  /** Jedinstveni programi za prikaz sekcija */
  programi: { id: number; naziv: string }[] = [];

  /** Predmeti grupisani po programId (posle filtriranja po godini) */
  predmetiPoProgramu: Record<number, Predmet[]> = {};

  //dobijam format za predmet i ocene
  // 12: 8,   // predmetId 12 -> ocena 8
  ocenaByPredmetId: Record<number, number> = {};

  errorMessage = '';

  // skup svih ID-jeva predmeta koje student pohađa
  pohadjanjePredmetIds = new Set<number>();

  // Paginate po sekciji 
  currentPageByProgram: Record<number, number> = {};
  pagePrijava = 1;
  pagePredmeti = 1;
  itemsPerPage = 10;

  // trackBy helperi
  //azurira se niz entiteta sa istim id, omogucava azuriranje podataka u vec postojecem divu
  trackByProg = (_: number, p: { id: number }) => p.id;
  trackByPredmet = (_: number, p: Predmet) => p.id;
  trackByGodina = (_: number, g: { id: number | null }) => (g.id ?? -1);
  trackByPrijava = (_: number, x: PrijavljeniIspit) => (x as any).id ?? _;

  constructor(
    private predmetService: PredmetService,
    private loginService: LoginService,
    private studentNaGodiniService: StudentNaGodiniService,
    private pohadjanjePredmetaService: PohadjanjePredmetaService,
    private prijavljeniIspitService: PrijavljeniIspitService,
    private studentService: StudentService,
    private dialog: MatDialog
  ) { }
  // metoda za dobavljanje id prijavljenog korisnika
  get prijavljeniKorisnikId(): number | null {
    return this.loginService?.user?.id ?? null;
  }

  ngOnInit(): void {
    this.loadStudentiNaGodini();
  }

  private loadStudentiNaGodini(): void {
    this.studentNaGodiniService.getAll().subscribe({
      next: (data) => {
        this.studentiNaGodini = data ?? [];
        this.loadPredmetiByProgram();
        this.loadOceneMap();
        this.loadPrijavljeniIspiti();
        this.loadStanjeNaRacunu();
      },
      error: () => {
        console.error("Greska u ucitavanju studenata na godini!");
      }
    });
  }

  private loadStanjeNaRacunu(): void {
    const uid = this.prijavljeniKorisnikId;
    if (!uid) { this.stanjeNaRacunu = null; return; }

    this.studentService.getAll().subscribe({
      next: (lista: Student[]) => {
        const moj = (lista ?? []).find(s => s.id === uid);
        this.stanjeNaRacunu = moj?.stanjeNaRacunu ?? null;
      },
      error: () => { this.stanjeNaRacunu = null; }
    });
  }


  private loadPrijavljeniIspiti(): void {
    const uid = this.prijavljeniKorisnikId;

    if (!uid) {
      console.error('❌ Id prijavljenog korisnika nije učitan!');
      this.prijavljeniIspiti = [];
      return;
    }

    // pronađi sve "studentNaGodini" zapise za tog korisnika
    const sngIds = new Set<number>(
      (this.studentiNaGodini ?? [])
        .filter(s => s?.student?.id === uid && typeof s.id === 'number')
        .map(s => s.id as number)
    );

    if (sngIds.size === 0) {
      console.warn('⚠️ Nema pronađenih upisa (StudentNaGodini) za korisnika', uid);
      this.prijavljeniIspiti = [];
      return;
    }

    // povuci sve prijave
    this.prijavljeniIspitService.getAll().subscribe({
      next: (sve: PrijavljeniIspit[]) => {
        if (!Array.isArray(sve)) {
          console.error('❌ Neočekivan format prijava ispita!', sve);
          this.prijavljeniIspiti = [];
          return;
        }

        // filtriraj samo prijave koje pripadaju ovom korisniku
        this.prijavljeniIspiti = sve.filter((pi: any) => {
          const sid =
            pi?.StudentNaGodini?.id ??
            pi?.studentNaGodini?.id ??
            pi?.student_na_godini?.id ??
            null;
          return typeof sid === 'number' && sngIds.has(sid);
        });

        console.group('✅ Moje prijave');
        console.log('Ukupno (sve):', sve.length);
        console.log('Filtrirane (moje):', this.prijavljeniIspiti);
        console.log('SNG ID-jevi:', Array.from(sngIds));
        console.groupEnd();
      },
      error: (err) => {
        console.error('❌ Greška u učitavanju prijava ispita.', err);
        this.prijavljeniIspiti = [];
      }
    });
  }


  /**
   * Kreiranje liste programa iz upisa i za svaki program povlacenje predmeta.
   * Filtriranje predmeta po godinama koje taj student ima u tom programu.
   */
  private loadPredmetiByProgram(): void {
    const uid = this.prijavljeniKorisnikId;
    if (!uid) { this.errorMessage = 'Korisnik nije prijavljen.'; return; }

    const mojiUpisi = this.studentiNaGodini.filter(s => s.student?.id === uid);
    if (mojiUpisi.length === 0) {
      this.errorMessage = 'Student nije upisan ni na jednu godinu.';
      return;
    }

    // Jedinstveni programi (id, naziv)
    const programMap = new Map<number, string>();
    for (const upis of mojiUpisi) {
      const sp = upis.studijskiProgram;
      if (sp?.id) programMap.set(sp.id, sp.naziv ?? `Program ${sp.id}`);
    }
    this.programi = Array.from(programMap, ([id, naziv]) => ({ id, naziv }));

    // Za svaki program povuceni predmete i filtrirani po godinama koje student ima u tom programu
    for (const prog of this.programi) {
      const studProgramId = prog.id;

      const allowedYearIds = new Set<number>(
        mojiUpisi
          .filter(u => u.studijskiProgram?.id === studProgramId)
          .map(u => {
            const g = (u as any).godinaStudija;                // broj ili objekat
            return typeof g === 'number' ? g : g?.id;          // normalizuj na number (id)
          })
          .filter((x): x is number => typeof x === 'number')
      );

      this.predmetService.getPredmetiByProgram(studProgramId).subscribe({
        next: (data) => {
          // uklanjanje duplikata po predmet.id
          const uniq = new Map<number, Predmet>();
          for (const p of (data ?? [])) {
            if (p?.id != null && !uniq.has(p.id)) uniq.set(p.id, p);
          }

          // filtriranje po godini (po ID-u); ako predmet nema godinu -> dozvoli
          const filtrirano = Array.from(uniq.values()).filter(p => {
            const pg = (p as any).godinaStudija;               // broj | objekat | undefined
            const pgId = typeof pg === 'number' ? pg : pg?.id;
            return pgId == null || allowedYearIds.has(pgId);
          });

          this.predmetiPoProgramu[studProgramId] = filtrirano;

          if (!this.currentPageByProgram[studProgramId]) this.currentPageByProgram[studProgramId] = 1;
        },
        error: () => {
          this.errorMessage = 'Greška prilikom učitavanja predmeta.';
        }
      });
    }
  }

  private loadOceneMap(): void {
    const uid = this.prijavljeniKorisnikId;
    if (!uid) return;

    const mojiUpisi = this.studentiNaGodini.filter(s => s.student?.id === uid && !!s.id);
    if (mojiUpisi.length === 0) {
      this.ocenaByPredmetId = {};
      this.pohadjanjePredmetIds.clear();
      return;
    }

    const pozivi = mojiUpisi.map(upis =>
      this.pohadjanjePredmetaService.getByStudentNaGodiniId(upis.id!)
    );

    forkJoin(pozivi).subscribe({
      next: (nizNizova: any[][]) => {
        const pohadjanja = nizNizova.flat();
        const mapOcena: Record<number, number> = {};
        const idsPronadjeni = new Set<number>();

        for (const rec of pohadjanja) {
          const predmetId: number | undefined = rec?.predmet?.id;
          const ocena = rec?.konacnaOcena;
          if (typeof predmetId === 'number') {
            idsPronadjeni.add(predmetId);
            if (typeof ocena === 'number' && !Number.isNaN(ocena)) {
              mapOcena[predmetId] = ocena;
            }
          }
        }

        this.ocenaByPredmetId = mapOcena;
        this.pohadjanjePredmetIds = idsPronadjeni;
        console.log('Pohađam ID-jeve:', Array.from(this.pohadjanjePredmetIds));
      },
      error: (err) => {
        console.error('Bar jedan poziv je pao:', err);
        this.ocenaByPredmetId = {};
        this.pohadjanjePredmetIds.clear();
      }
    });
  }

  //DEO ZA DIALOG ------------------------------------------------

  openDialog(p: Predmet) {
    console.log("Moj predmet: ", p);

    const studentId = this.prijavljeniKorisnikId;
    if (!studentId) { return; }

    const sngId = this.getSngIdForPredmet(p);
    if (!sngId) { return; }

    const cena = 500;

    const ref = this.dialog.open(PrijavaIspitaDialogComponent, {
      width: '600px',
      data: {
        predmet: p,
        studentId,
        studentNaGodiniId: sngId,
        cenaPrijave: cena
      }
    });

    ref.afterClosed().subscribe(res => {
      this.loadStanjeNaRacunu();
      this.loadPrijavljeniIspiti();
      console.log(this.prijavljeniIspiti);

    });
  }

  // ----------------- Pomoćne funkcije za prikaz -----------------

  /** Normalizuje "godinaStudija" u { id, label } */
  private normalizeGodina(val: any): { id: number | null; label: string } {
    if (val == null) return { id: null, label: 'Bez godine' };
    if (typeof val === 'number') return { id: val, label: `${val}. godina` };
    const id = typeof val?.id === 'number' ? val.id : null;
    const label = (val?.naziv ?? val?.godina ?? (id != null ? `Godina #${id}` : 'Bez godine')) + '';
    return { id, label };
  }

  /** Godine (jedinstvene) koje važe za dati program – za podsekcije */
  getGodineZaProgram(programId: number): { id: number | null; label: string }[] {
    const uid = this.prijavljeniKorisnikId;
    const mojiUpisi = this.studentiNaGodini.filter(
      u => u.student?.id === uid && u.studijskiProgram?.id === programId
    );

    const set = new Map<number | null, string>();
    for (const u of mojiUpisi) {
      const g = this.normalizeGodina((u as any).godinaStudija);
      if (!set.has(g.id)) set.set(g.id, g.label);
    }

    // Ako postoje predmeti bez definisane godine, dodaj bez godine
    const listaPredmeta = this.predmetiPoProgramu[programId] || [];
    const imaBezGodine = listaPredmeta.some(p => {
      const pg = (p as any).godinaStudija;
      return this.normalizeGodina(pg).id === null;
    });
    if (imaBezGodine && !set.has(null)) set.set(null, 'Bez godine');

    const arr = Array.from(set, ([id, label]) => ({ id, label }));
    arr.sort((a, b) => {
      if (a.id == null && b.id == null) return 0;
      if (a.id == null) return 1;
      if (b.id == null) return -1;
      return a.id - b.id;
    });
    return arr;
  }

  /** Vraca predmete za dati program i konkretnu godinu (ili "bez godine") */
  getPredmetiZaProgramIGodinu(programId: number, godinaId: number | null): Predmet[] {
    const lista = this.predmetiPoProgramu[programId] || [];

    return lista.filter(p => {
      const norm = this.normalizeGodina((p as any).godinaStudija);
      const uSekciji =
        (godinaId == null && norm.id == null) ||
        (godinaId != null && norm.id === godinaId);

      const pohađa = this.pohadjanjePredmetIds.has(p.id!);

      const nemaOcenu = this.getOcenaForPredmet(p.id!) == null;

      return uSekciji && pohađa && nemaOcenu;
    });
  }

  //funkcija za dobavljanje konacnih ocena za predmet
  getOcenaForPredmet(predmetId: number): number | null {
    const v = this.ocenaByPredmetId[predmetId];
    return typeof v === 'number' ? v : null;

  }

  // Vraca ESPB vrednost iz modela Predmet
  getEspFromPredmet(p: Predmet): number {
    const raw = (p as any).espb ?? (p as any).esbn ?? (p as any).ects ?? (p as any).esp;
    const n = typeof raw === 'string' ? Number(raw) : raw;
    return (typeof n === 'number' && !Number.isNaN(n)) ? n : 0;
  }

  private getSngIdForPredmet(predmet: Predmet): number | null {
    const uid = this.prijavljeniKorisnikId;
    if (!uid) return null;

    // svi tvoji upisi
    const mojiUpisi = this.studentiNaGodini.filter(u => u.student?.id === uid);
    if (mojiUpisi.length === 0) return null;

    // probaj da nađeš SNG po godini predmeta
    const pg = (predmet as any).godinaStudija;              // broj | objekat | null
    const pgId = typeof pg === 'number' ? pg : pg?.id ?? null;

    if (pgId != null) {
      const pogodak = mojiUpisi.find(u => {
        const ug = (u as any).godinaStudija;                // broj | objekat | null
        const ugId = typeof ug === 'number' ? ug : ug?.id ?? null;
        return ugId === pgId;
      });
      if (pogodak?.id) return pogodak.id;
    }

    // najskoriji upis po datumUpisa
    const sorted = [...mojiUpisi].sort((a: any, b: any) => {
      const da = new Date(a?.datumUpisa ?? 0).getTime();
      const db = new Date(b?.datumUpisa ?? 0).getTime();
      return db - da;
    });
    return sorted[0]?.id ?? null;
  }


}

