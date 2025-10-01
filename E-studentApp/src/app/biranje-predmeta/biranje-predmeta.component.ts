import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';

import { LoginService } from '../../Services/login.service';
import { PredmetService } from '../../Services/predmet.service';
import { StudentNaGodiniService } from '../../Services/student/studentNaGodini.service';
import { PohadjanjePredmetaService } from '../../Services/pohadjanje_predmeta.service';

import { Predmet } from '../../models/predmetModel';
import { StudentNaGodini } from '../../models/studentNaGodini/studentNaGodini';

@Component({
  selector: 'app-biranje-predmeta',
  standalone: true,
  imports: [CommonModule, MatCheckboxModule, MatButtonModule],
  templateUrl: './biranje-predmeta.component.html',
  styleUrls: ['./biranje-predmeta.component.css']
})
export class BiranjePredmetaComponent implements OnInit {

  errorMessage = '';

  /** Selekcija po sekciji: programId -> (godKey -> [predmetId, ...]) */
  selectedByProgGod: Record<number, Record<string, number[]>> = {};

  /** Svi upisi (SNG) iz bekenda */
  studentiNaGodini: StudentNaGodini[] = [];

  /** Jedinstveni programi za prikaz sekcija */
  programi: { id: number; naziv: string }[] = [];

  //Predmeti grupisani po programId (posle filtriranja po godini) 
  predmetiPoProgramu: Record<number, Predmet[]> = {};

  // trackBy helperi za šablon
  trackByProg = (_: number, p: { id: number }) => p.id;
  trackByPredmet = (_: number, p: Predmet) => p.id;
  trackByGodina = (_: number, g: { id: number | null }) => (g.id ?? -1);


  constructor(
    private predmetService: PredmetService,
    private loginService: LoginService,
    private studentNaGodiniService: StudentNaGodiniService,
    private pohadjanjePredmetaService: PohadjanjePredmetaService,
    private router: Router
  ) {}

  get prijavljeniKorisnikId(): number | null {
    return this.loginService?.user?.id ?? null;
  }


  /** učitaj upise, pa izgradi programe i predmete po programu */
  ngOnInit(): void {
    this.loadStudentiNaGodini();
  }

  /** Učitaj sve zapise StudentNaGodini */
  loadStudentiNaGodini(): void {
    this.studentNaGodiniService.getAll().subscribe({
      next: (data) => {
        this.studentiNaGodini = data;
        this.loadPredmetiByProgram();
      },
      error: () => {
        this.errorMessage = 'Greška prilikom učitavanja upisa.';
      }
    });
  }

  loadPredmetiByProgram(): void {
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

    // Za svaki *jedinstven* program povuci predmete i filtriraj po godinama koje taj student ima u tom programu
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
          for (const p of data) if (p?.id != null && !uniq.has(p.id)) uniq.set(p.id, p);

          // filtriranje po godini (po ID-u)
          const filtrirano = Array.from(uniq.values()).filter(p => {
            const pg = (p as any).godinaStudija;               // broj | objekat | undefined
            const pgId = typeof pg === 'number' ? pg : pg?.id;
            return pgId == null || allowedYearIds.has(pgId);
          });

          this.predmetiPoProgramu[studProgramId] = filtrirano;
        },
        error: () => {
          this.errorMessage = 'Greška prilikom učitavanja predmeta.';
        }
      });
    }
  }

  /**
   * Sačuvaj *sve* trenutno izabrane predmete iz svih sekcija.
   * Grupisanje: (programId, godinaId) -> pronađi odgovarajući SNG i pozovi backend.
   * Nakon obrade svih sekcija, preusmeri na /obavestenja.
   */
  sacuvajSveSekcije(): void {
    const uid = this.prijavljeniKorisnikId;
    if (!uid) { this.errorMessage = 'Korisnik nije prijavljen.'; return; }

    const poslovi: Array<Promise<unknown>> = [];

    for (const [progIdStr, byGod] of Object.entries(this.selectedByProgGod)) {
      const progId = Number(progIdStr);

      for (const [godKey, ids] of Object.entries(byGod)) {
        if (!ids.length) continue;

        const godId = godKey === 'none' ? null : Number(godKey);

        // Pronađi SNG za dati program i (po mogućnosti) istu godinu; fallback: najviša godina
        const kandidati = this.studentiNaGodini
          .filter(s => s.student?.id === uid && s.studijskiProgram?.id === progId)
          .sort((a, b) => (this.normalizeGodina(b.godinaStudija).id ?? 0) - (this.normalizeGodina(a.godinaStudija).id ?? 0));

        const tacan = kandidati.find(k => this.normalizeGodina(k.godinaStudija).id === godId);
        const sng = tacan ?? kandidati[0];
        if (!sng?.id) continue;

        // Wrap u Promise radi grupnog .then() nakon svih poziva
        poslovi.push(new Promise((resolve) => {
          this.pohadjanjePredmetaService
            .prijaviPredmete(sng.id!, ids)
            .subscribe({ next: resolve, error: resolve });
        }));
      }
    }

    if (poslovi.length === 0) { this.errorMessage = 'Niste izabrali nijedan predmet.'; return; }

    Promise.all(poslovi).then(() => {
      this.router.navigate(['/obavestenja']);
    });
  }

  // ===============  POMOĆNE FUNKCIJE ==============

  /** Normalizuje "godinaStudija" u { id, label } bez obzira da li je broj ili objekat */
  private normalizeGodina(val: any): { id: number | null; label: string } {
    if (val == null) return { id: null, label: 'Bez godine' };
    if (typeof val === 'number') return { id: val, label: `${val}. godina` };
    const id = typeof val?.id === 'number' ? val.id : null;
    const label = (val?.naziv ?? val?.godina ?? (id != null ? `Godina #${id}` : 'Bez godine')) + '';
    return { id, label };
  }

  /** Interni ključ za mapiranje selekcije po godini (null -> 'none') */
  private godKey(gid: number | null | undefined): string {
    return gid == null ? 'none' : String(gid);
  }

  /** Godine (jedinstvene) koje važe za dati program – za prikaz podsekcija u UI */
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

    // Ako postoje predmeti bez definisane godine, dodaj "Bez godine"
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

  /** Vraca predmete za dati program i konkretnu godinu */
  getPredmetiZaProgramIGodinu(programId: number, godinaId: number | null): Predmet[] {
    const lista = this.predmetiPoProgramu[programId] || [];
    return lista.filter(p => {
      const norm = this.normalizeGodina((p as any).godinaStudija);
      return (godinaId == null && norm.id == null) || (godinaId != null && norm.id === godinaId);
    });
  }

  /** Provera da li je predmet selektovan u konkretnoj sekciji (program + godina) */
  isChecked(progId: number, godId: number | null, predmetId: number): boolean {
    const key = this.godKey(godId);
    return this.selectedByProgGod[progId]?.[key]?.includes(predmetId) ?? false;
    }

  /** Uključi/isključi predmet u konkretnoj sekciji */
  togglePredmet(progId: number, godId: number | null, predmetId: number, checked: boolean): void {
    const key = this.godKey(godId);
    const byProg = this.selectedByProgGod[progId] ?? (this.selectedByProgGod[progId] = {});
    const list = byProg[key] ?? (byProg[key] = []);
    if (checked) {
      if (!list.includes(predmetId)) list.push(predmetId);
    } else {
      byProg[key] = list.filter(id => id !== predmetId);
    }
  }

  /** Broj izabranih predmeta u jednoj sekciji (program + godina) */
  countProgGod(progId: number, godId: number | null): number {
    const key = this.godKey(godId);
    return this.selectedByProgGod[progId]?.[key]?.length ?? 0;
  }

  /** Ukupan broj izabranih predmeta preko svih sekcija */
  get ukupnoIzabrano(): number {
    return Object.values(this.selectedByProgGod).reduce((sum, byGod) => {
      return sum + Object.values(byGod).reduce((s, arr) => s + arr.length, 0);
    }, 0);
  }
}
