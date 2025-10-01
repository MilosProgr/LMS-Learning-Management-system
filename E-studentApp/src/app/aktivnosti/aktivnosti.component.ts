import { Component, OnInit } from '@angular/core';
import { Student } from '../../models/student.model';
import { StudentNaGodini } from '../../models/studentNaGodini/studentNaGodini';
import { EvaluacijaZnanja } from '../../models/evaluacijaZnanja';
import { PrijavljeniIspit } from '../../models/prijavljenIspit';
import { StudentService } from '../../Services/student/student.service';
import { StudentNaGodiniService } from '../../Services/student/studentNaGodini.service';
import { EvaluacijaZnanjaService } from '../../Services/evaluacija-znanja.service';
import { PrijavljeniIspitService } from '../../Services/prijavljeniIspit.service';
import { LoginService } from '../../Services/login.service';
import { RegistrovaniKorisnik } from '../../models/registrovaniKorisnik';
import { forkJoin } from 'rxjs/internal/observable/forkJoin';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-aktivnosti',
  standalone: true,
  imports: [CommonModule,],
  templateUrl: './aktivnosti.component.html',
  styleUrl: './aktivnosti.component.css'
})
export class AktivnostiComponent implements OnInit {

  prijavljeniKorisnik: RegistrovaniKorisnik = this.loginService.user

  studenti: Student[] = [];
  studentiNaGodini: StudentNaGodini[] = [];
  evaluacijeZnanja: EvaluacijaZnanja[] = [];
  prijavljeniIspiti: PrijavljeniIspit[] = [];

  evaluacijeZaPrijavljenog: EvaluacijaZnanja[] = [];

  private predmetByPrijavljeniId = new Map<number, string>();

  loading = false;
  errorMsg: string | null = null;

  constructor(
    private loginService: LoginService,
    private studentService: StudentService,
    private studentNaGodiniService: StudentNaGodiniService,
    private evaluacijeZnanjaService: EvaluacijaZnanjaService,
    private prijavljeniIspitService: PrijavljeniIspitService
  ) { }

  ngOnInit(): void {
    this.ucitajSveIPostaviFiltre();

  }

  private ucitajSveIPostaviFiltre(): void {
    this.loading = true;
    this.errorMsg = null;

    forkJoin({
      studenti: this.studentService.getAll(),
      sng: this.studentNaGodiniService.getAll(),
      prijavljeni: this.prijavljeniIspitService.getAll(),
      evaluacije: this.evaluacijeZnanjaService.getAll()
    }).subscribe({
      next: ({ studenti, sng, prijavljeni, evaluacije }) => {

        this.studenti = studenti ?? [];
        this.studentiNaGodini = sng ?? [];
        this.prijavljeniIspiti = prijavljeni ?? [];
        this.evaluacijeZnanja = evaluacije ?? [];

        // pravljenje mape: prijavljeniIspit.id -> predmet.naziv
        this.predmetByPrijavljeniId.clear();
        for (const pi of this.prijavljeniIspiti) {
          const id = pi?.id;
          const naziv =
            (pi as any)?.predmet?.naziv ??
            null;
          if (typeof id === 'number' && naziv) {
            this.predmetByPrijavljeniId.set(id, naziv);
          }
        }

        //poziv metode za dobavljanje za prijavljenog korinsika
        this.izracunajEvaluacijeZaPrijavljenog();
      },
      error: (err) => {
        console.error('Greška prilikom dobavljanja podataka:', err);
        this.errorMsg = 'Došlo je do greške prilikom učitavanja podataka.';
      },
      complete: () => {
        this.loading = false;
      }
    });
  }

  predmetNazivForEval(e: EvaluacijaZnanja): string {
    const prijId =
      (e as any)?.prijavljeniIspit?.id ??
      (e as any)?.prijavljeniIspitId;

    if (typeof prijId !== 'number') return '—';
    return this.predmetByPrijavljeniId.get(prijId) ?? '—';
  }

  private izracunajEvaluacijeZaPrijavljenog(): void {
    const regId = this.prijavljeniKorisnik?.id;
    if (!regId) {
      this.evaluacijeZaPrijavljenog = [];
      return;
    }

    //studenti prijavljenog korisnika
    const mojiStudenti = this.studenti.filter(s =>
      (s as any)?.korisnik?.id === regId
    );

    const mojiStudentIds = mojiStudenti
      .map(s => s.id)
      .filter((id): id is number => typeof id === 'number');

    if (mojiStudentIds.length === 0) {
      this.evaluacijeZaPrijavljenog = [];
      return;
    }

    //tudentiNaGodini za moje studente
    const mojiSng = this.studentiNaGodini.filter(sng => {
      const sid = (sng as any)?.student?.id ?? (sng as any)?.studentId;
      return mojiStudentIds.includes(sid);
    });

    const mojiSngIds = mojiSng
      .map(sng => sng.id)
      .filter((id): id is number => typeof id === 'number');

    if (mojiSngIds.length === 0) {
      this.evaluacijeZaPrijavljenog = [];
      return;
    }

    //prijavljeni ispiti za moje SNG
    const mojiPrijavljeni = this.prijavljeniIspiti.filter(pi => {
      const sngId = (pi as any)?.studentNaGodini?.id ?? (pi as any)?.studentNaGodiniId;
      return mojiSngIds.includes(sngId);
    });

    const mojiPrijavljeniIds = mojiPrijavljeni
      .map(pi => pi.id)
      .filter((id): id is number => typeof id === 'number');

    if (mojiPrijavljeniIds.length === 0) {
      this.evaluacijeZaPrijavljenog = [];
      return;
    }

    //evaluacije znanja filtrirane po prijavljenim ispitima
    this.evaluacijeZaPrijavljenog = this.evaluacijeZnanja.filter(ev => {
      const prijIspitId = (ev as any)?.prijavljeniIspit?.id ?? (ev as any)?.prijavljeniIspitId;
      return mojiPrijavljeniIds.includes(prijIspitId);
    });
    console.log(this.evaluacijeZaPrijavljenog);
  }

  trackById = (_: number, x: { id?: number }) => x?.id ?? _;

}