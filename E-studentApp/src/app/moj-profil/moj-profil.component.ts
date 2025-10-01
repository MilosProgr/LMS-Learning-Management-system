import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../../Services/profile-service.service';
import { CommonModule, NgFor, DatePipe } from '@angular/common';
import { LoginService } from '../../Services/login.service';
import { StudentNaGodiniService } from '../../Services/student/studentNaGodini.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-moj-profil',
  templateUrl: './moj-profil.component.html',
  styleUrls: ['./moj-profil.component.css'],
  standalone: true,
  imports: [CommonModule]
})
export class MojProfilComponent implements OnInit {
  userRoles: string[] = [];
  adminData: any = null;
  nastavnikData: any = null;
  studentData: any = null;
  studentNaGodiniData: any = null;
  korisnikData: any = null;
  sluzbenikData: any = null;

  constructor(
    private loginService: LoginService,
    private profileService: ProfileService,
    private studentNaGodiniService: StudentNaGodiniService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.ucitajUlogeKorisnika();
    this.ucitajSveProfile();
  }

  private ucitajUlogeKorisnika(): void {
    const user = this.loginService.user;
    if (user && Array.isArray(user.roles)) {
      this.userRoles = user.roles;
      console.log(`Uloge korisnika iz funkcije: ${this.userRoles}`);

    } else {
      console.warn('Korisnik nije ulogovan ili nema role.');
    }
  }

  private ucitajSveProfile(): void {

    this.profileService.getKorisnikPodaci().subscribe((data) => {
      if (Array.isArray(data) && data.length > 0) {

        const ulogovaniKorisnikId = this.loginService.user.id
        const korisnik = data.find(a => a.id === ulogovaniKorisnikId);

        this.korisnikData = {
          korisnickoIme: korisnik.korisnickoIme ?? 'N/A',
          imePrezime: `${korisnik.ime ?? ''} ${korisnik.prezime ?? ''}`.trim(),
          email: korisnik.email ?? 'N/A',
        };
        console.log(this.korisnikData);
      }
    });

    for (const role of this.userRoles) {
      switch (role) {
        case 'ROLE_ADMIN':
          this.profileService.getAdminProfile().subscribe((data) => {
            if (Array.isArray(data) && data.length > 0) {

              const ulogovaniKorisnikId = this.loginService.user.id
              const admin = data.find(a => a.korisnik?.id === ulogovaniKorisnikId);

              this.adminData = {
                korisnickoIme: admin.korisnik.korisnickoIme ?? 'N/A',
                imePrezime: `${admin.korisnik.ime ?? ''} ${admin.korisnik.prezime ?? ''}`.trim(),
                email: admin.korisnik.email ?? 'N/A',
                //ostali podaci
                telefon: admin.telefon ?? 'N/A',
                jmbg: admin.jmbg ?? 'N/A',
                poslovniEmail: admin.poslovniEmail ?? 'N/A',
                datumKreiranjaNaloga: admin.datumKreiranjaNaloga ?? 'N/A',
                nalogAktivan: admin.nalogAktivan ? 'Aktivan' : 'Neaktivan'

              };
            }
          });
          break;

        case 'ROLE_NASTAVNIK':
          this.profileService.getNastavnikProfile().subscribe((data) => {
            if (Array.isArray(data) && data.length > 0) {

              const ulogovaniKorisnikId = this.loginService.user.id
              const nastavnik = data.find(a => a.korisnik?.id === ulogovaniKorisnikId);

              this.nastavnikData = {

                korisnickoIme: nastavnik.korisnik.korisnickoIme ?? 'N/A',
                imePrezime: `${nastavnik.korisnik.ime ?? ''} ${nastavnik.korisnik.prezime ?? ''}`.trim(),
                email: nastavnik.korisnik.email ?? 'N/A',
                //ostali podaci
                telefon: nastavnik.telefon ?? 'N/A',
                jmbg: nastavnik.jmbg ?? 'N/A',
                poslovniEmail: nastavnik.poslovniMail ?? 'N/A',
                biografija: nastavnik.biografija ?? 'N/A',
                brojSlobodnihDana: nastavnik.brojSlobodnihDana ?? 'N/A',
                brojIskoriscenihDana: nastavnik.brojIskoriscenihDana ?? 'N/A'
                // fakultet: nastavnik.fakultet ?? 'N/A'
              };
            }
          });
          break;

        case 'ROLE_STUDENT':
          this.profileService.getStudentProfile().subscribe((data) => {
            if (Array.isArray(data) && data.length > 0) {
              const ulogovaniKorisnikId = this.loginService.user.id
              const student = data.find(a => a.korisnik?.id === ulogovaniKorisnikId);
              // console.log("Student koji dobijam", student);
              this.studentData = {
                korisnickoIme: student.korisnik.korisnickoIme ?? 'N/A',
                imePrezime: `${student.korisnik.ime ?? ''} ${student.korisnik.prezime ?? ''}`.trim(),
                email: student.korisnik.email ?? 'N/A',
                //ostali podaci
                jmbg: student.jmbg ?? 'N/A',
                telefon: student.telefon ?? 'N/A',
                statusStudiranja: student.statusStudiranja ? 'Aktivan' : 'Neaktivan',
                stanjeNaRacunu: student.stanjeNaRacunu ?? 'N/A',

                mestoPrebivalista: student.mestoPrebivalista ?? 'N/A',
                adresa: student.adresa ?? 'N/A',
                drzava: student.drzava ?? 'N/A',
              };
            }
          });
          this.studentNaGodiniService.getAll().subscribe((data) => {
            if (Array.isArray(data) && data.length > 0) {
              const ulogovaniKorisnikId = this.loginService.user.id
              const studentiNaGodini = data.filter(a => a.student?.id === ulogovaniKorisnikId);
              // console.log("Moj studneti na godini",studentiNaGodini);

              this.studentNaGodiniData = studentiNaGodini.map(upis => ({
                prosek: upis.prosek ?? 'N/A',
                datumUpisa: upis.datumUpisa ?? 'N/A',
                brojIndeksa: upis.brojIndeksa ?? 'N/A',
                godinaStudija: upis.godinaStudija?.godina ?? 'N/A',
                studijskiProgram: upis.studijskiProgram.naziv ?? 'N/A',
              }));

              // console.log(this.studentNaGodiniData);

            }
          });
          break;

        case 'ROLE_SLUZBENIK':
          this.profileService.getSluzbenikProfile().subscribe((data) => {
            if (Array.isArray(data) && data.length > 0) {

              const ulogovaniKorisnikId = this.loginService.user.id
              const sluzbenik = data.find(a => a.korisnik?.id === ulogovaniKorisnikId);

              this.sluzbenikData = {
                korisnickoIme: sluzbenik.korisnik.korisnickoIme ?? 'N/A',
                imePrezime: `${sluzbenik.korisnik.ime ?? ''} ${sluzbenik.korisnik.prezime ?? ''}`.trim(),
                email: sluzbenik.korisnik.email ?? 'N/A',
                //ostali podaci
                telefon: sluzbenik.telefon ?? 'N/A',
                jmbg: sluzbenik.jmbg ?? 'N/A',
                nalogAktivan: sluzbenik.nalogAktivan ? 'Aktivan' : 'Neaktivan'

              };
            }
          });
          break;
      }
    }
  }
  //pomocne metode
  formatirajUlogu(uloga: string): string {
    const bezPrefiksa = uloga.replace('ROLE_', '');
    return bezPrefiksa.charAt(0) + bezPrefiksa.slice(1).toLowerCase();
  }

  get jedinstveniEmailovi(): string[] {
    const emailovi = new Set<string>();
    // if (this.korisnikData?.email) emailovi.add(this.korisnikData.email);
    if (this.adminData?.poslovniEmail) emailovi.add(this.adminData.poslovniEmail);
    if (this.nastavnikData?.poslovniEmail) emailovi.add(this.nastavnikData.poslovniEmail);
    // if (this.studentData?.email) emailovi.add(this.studentData.email);
    // if (this.sluzbenikData?.email) emailovi.add(this.sluzbenikData.email);
    return Array.from(emailovi);
  }

  get jedinstveniTelefoni(): string[] {
    const telefoni = new Set<string>();
    if (this.adminData?.telefon) telefoni.add(this.adminData.telefon);
    if (this.nastavnikData?.telefon) telefoni.add(this.nastavnikData.telefon);
    if (this.studentData?.telefon) telefoni.add(this.studentData.telefon);
    if (this.sluzbenikData?.telefon) telefoni.add(this.sluzbenikData.telefon);
    return Array.from(telefoni);

  }

  get prikazaniJMBG(): string | null {
    return this.adminData?.jmbg
      || this.nastavnikData?.jmbg
      || this.studentData?.jmbg
      || this.sluzbenikData?.jmbg
      || null;
  }

  goBack() {
    this.location.back();
  }

}

