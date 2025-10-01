import { CommonModule, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NgxPaginationModule } from 'ngx-pagination';

import { Predmet } from '../../../models/predmetModel';
import { StudentNaGodini } from '../../../models/studentNaGodini/studentNaGodini';

import { PredmetService } from '../../../Services/predmet.service';
import { LoginService } from '../../../Services/login.service';
import { StudentNaGodiniService } from '../../../Services/student/studentNaGodini.service';
import { PohadjanjePredmetaService } from '../../../Services/pohadjanje_predmeta.service';
import { forkJoin, map } from 'rxjs';

@Component({
  selector: 'app-student-predmeti',
  standalone: true,
  imports: [CommonModule, NgIf, NgFor, NgxPaginationModule],
  templateUrl: './student-predmeti.component.html',
  styleUrls: ['./student-predmeti.component.css']
})
export class StudentPredmetiComponent implements OnInit {
  studentiNaGodini: StudentNaGodini[] = [];

  /** Jedinstveni programi za prikaz sekcija */
  programi: { id: number; naziv: string }[] = [];

  /** Predmeti grupisani po programId (posle filtriranja po godini) */
  predmetiPoProgramu: Record<number, Predmet[]> = {};

  //dobijam format za predmet i ocene
  // 12: 8,   // predmetId 12 -> ocena 8
  ocenaByPredmetId: Record<number, number> = {};

  // skup svih ID-jeva predmeta koje student pohađa
  pohadjanjePredmetIds = new Set<number>();

  errorMessage = '';

  // Paginate po sekciji 
  currentPageByProgram: Record<number, number> = {};
  itemsPerPage = 10;

  // trackBy helperi
  trackByProg = (_: number, p: { id: number }) => p.id;
  trackByPredmet = (_: number, p: Predmet) => p.id;
  trackByGodina = (_: number, g: { id: number | null }) => (g.id ?? -1);

  constructor(
    private predmetService: PredmetService,
    private loginService: LoginService,
    private studentNaGodiniService: StudentNaGodiniService,
    private pohadjanjePredmetaService: PohadjanjePredmetaService
  ) { }

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
      },
      error: () => {
        this.errorMessage = 'Greška prilikom učitavanja upisa.';
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
            const g = (u as any).godinaStudija;
            return typeof g === 'number' ? g : g?.id;
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

          // filtriranje po godini; ako predmet nema godinu dozvoli i to
          const filtrirano = Array.from(uniq.values()).filter(p => {
            const pg = (p as any).godinaStudija;
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
            idsPronadjeni.add(predmetId); // dodaje predmet u set svih pohađanih predmeta
            if (typeof ocena === 'number' && !Number.isNaN(ocena)) {
              mapOcena[predmetId] = ocena; // ako predmet ima upisanu ocenu dodaje se u mapu
            }
          }
        }

        this.ocenaByPredmetId = mapOcena;
        this.pohadjanjePredmetIds = idsPronadjeni;
      },
      error: (err) => {
        console.error('Bar jedan poziv je pao:', err);
        this.ocenaByPredmetId = {};
        this.pohadjanjePredmetIds.clear();
      }
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

      //proverava da li se predmet nalazi u pohadjanju
      const pohađa = this.pohadjanjePredmetIds.has(p.id!); 

      return uSekciji && pohađa; // filtriranje i po sekciji i pohađanju
    });
  }

  //funkcija za dobavljanje konacinih ocena za predmet
  getOcenaForPredmet(predmetId: number): number | null {
    const v = this.ocenaByPredmetId[predmetId];
    return typeof v === 'number' ? v : null;


  }
  //sumiranje bodova za premete kome je upisana ocena
  sumEspZaSekciju(programId: number, godinaId: number | null): number {
    const predmeti = this.getPredmetiZaProgramIGodinu(programId, godinaId);
    return predmeti.reduce((sum, p) => {
      const oc = this.getOcenaForPredmet(p.id!);
      if (oc != null) {
        return sum + this.getEspFromPredmet(p);
      }
      return sum;
    }, 0);
  }

  // Vraca ESPB vrednost iz modela Predmet
  getEspFromPredmet(p: Predmet): number {
    // Ako tvoj model ima baš jedno polje (npr. espb), možeš ostaviti samo to.
    const raw = (p as any).espb ?? (p as any).esbn ?? (p as any).ects ?? (p as any).esp;
    const n = typeof raw === 'string' ? Number(raw) : raw;
    return (typeof n === 'number' && !Number.isNaN(n)) ? n : 0;
  }


}
