import { Component, OnInit } from '@angular/core';
import { CommonModule, Location } from '@angular/common';

import { ProfileService } from '../../Services/profile-service.service';
import { LoginService } from '../../Services/login.service';
import { StudentNaGodiniService } from '../../Services/student/studentNaGodini.service';

import { Administrator } from '../../models/administrator/administrator';
import { Nastavnik } from '../../models/nastavnik.model';
import { Student } from '../../models/student.model';
import { StudentNaGodini } from '../../models/studentNaGodini/studentNaGodini';
import { RegistrovaniKorisnik } from '../../models/registrovaniKorisnik';
import { SluzbenikStudentske } from '../../models/sluzbenikStudentske/sluzbenikStudentske';

@Component({
  selector: 'app-moj-profil',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './moj-profil.component.html',
  styleUrls: ['./moj-profil.component.css']
})
export class MojProfilComponent implements OnInit {

  userRoles: string[] = [];

  adminData: Administrator | null = null;
  nastavnikData: Nastavnik | null = null;
  studentData: Student | null = null;

  studentNaGodiniData: StudentNaGodini[] = [];

  korisnikData: RegistrovaniKorisnik | null = null;
  sluzbenikData: SluzbenikStudentske | null = null;

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

    const { user } = this.loginService;

    if (user && Array.isArray(user.roles)) {

      this.userRoles = user.roles;

      console.log('Uloge korisnika:', this.userRoles);

    } else {

      console.warn('Korisnik nije ulogovan ili nema role.');
    }
  }

  private ucitajSveProfile(): void {

    const ulogovaniKorisnikId = this.loginService.user?.id;

    if (!ulogovaniKorisnikId) {

      console.warn('Korisnik ID nije pronađen.');
      return;
    }

    // REGISTROVANI KORISNIK
    this.profileService.getKorisnikPodaci().subscribe((data) => {

      if (Array.isArray(data) && data.length > 0) {

        const korisnik = data.find(
          korisnik => korisnik.id === ulogovaniKorisnikId
        );

        if (!korisnik) {
          return;
        }

        this.korisnikData = {
          id: korisnik.id,
          korisnickoIme: korisnik.korisnickoIme ?? 'N/A',
          ime: korisnik.ime ?? '',
          prezime: korisnik.prezime ?? '',
          email: korisnik.email ?? 'N/A',
          roles: korisnik.roles ?? []
        };

        console.log(this.korisnikData);
      }
    });

    for (const role of this.userRoles) {

      switch (role) {

        // ADMIN
        case 'ROLE_ADMIN':

          this.profileService.getAdminProfile().subscribe((data) => {

            if (Array.isArray(data) && data.length > 0) {

              const admin = data.find(
                administrator =>
                  administrator.korisnik?.id === ulogovaniKorisnikId
              );

              if (!admin) {
                return;
              }

              this.adminData = {

                id: admin.id ?? null,

                korisnik: admin.korisnik,

                korisnickoIme:
                  admin.korisnik?.korisnickoIme ?? 'N/A',

                imePrezime:
                  `${admin.korisnik?.ime ?? ''} ${admin.korisnik?.prezime ?? ''}`.trim(),

                email:
                  admin.korisnik?.email ?? 'N/A',

                telefon:
                  admin.telefon ?? 'N/A',

                jmbg:
                  admin.jmbg ?? 'N/A',

                poslovniEmail:
                  admin.poslovniEmail ?? 'N/A',

                datumKreiranjaNaloga:
                  admin.datumKreiranjaNaloga ?? null,

                nalogAktivan:
                  admin.nalogAktivan ?? false
              };
            }
          });

          break;

        // NASTAVNIK
        case 'ROLE_NASTAVNIK':

          this.profileService.getNastavnikProfile().subscribe((data) => {

            if (Array.isArray(data) && data.length > 0) {

              const nastavnik = data.find(
                nastavnik =>
                  nastavnik.korisnik?.id === ulogovaniKorisnikId
              );

              if (!nastavnik) {
                return;
              }

              this.nastavnikData = {

                id: nastavnik.id ?? null,

                korisnik:
                  nastavnik.korisnik,

                korisnickoIme:
                  nastavnik.korisnik?.korisnickoIme ?? 'N/A',

                imePrezime:
                  `${nastavnik.korisnik?.ime ?? ''} ${nastavnik.korisnik?.prezime ?? ''}`.trim(),

                email:
                  nastavnik.korisnik?.email ?? 'N/A',

                telefon:
                  nastavnik.telefon ?? 'N/A',

                jmbg:
                  nastavnik.jmbg ?? 'N/A',

                poslovniMail:
                  nastavnik.poslovniMail ?? 'N/A',

                biografija:
                  nastavnik.biografija ?? 'N/A',

                brojSlobodnihDana:
                  nastavnik.brojSlobodnihDana ?? 0,

                brojIskoriscenihDana:
                  nastavnik.brojIskoriscenihDana ?? 0
              };
            }
          });

          break;

        // STUDENT
        case 'ROLE_STUDENT':

          this.profileService.getStudentProfile().subscribe((data) => {

            if (Array.isArray(data) && data.length > 0) {

              const student = data.find(
                student =>
                  student.korisnik?.id === ulogovaniKorisnikId
              );

              if (!student) {
                return;
              }

              this.studentData = {

                id: student.id ?? 0,

                statusStudiranja:
                  student.statusStudiranja ?? '',

                stanjeNaRacunu:
                  student.stanjeNaRacunu ?? 0,

                predmetiIzabrani:
                  student.predmetiIzabrani ?? false,

                jmbg:
                  student.jmbg ?? 'N/A',

                telefon:
                  student.telefon ?? 'N/A',

                brojIndeksa:
                  student.brojIndeksa ?? '',

                adresa: {
                  id: student.adresa?.id ?? null,
                  ulica: student.adresa?.ulica ?? '',
                  broj: student.adresa?.broj ?? '',
                  mesto: {
                    id: student.adresa?.mesto?.id ?? null
                  }
                },

                drzava: {
                  id: student.drzava?.id ?? 0
                },

                studentNaGodini: {
                  brojIndeksa:
                    student.studentNaGodini?.brojIndeksa ?? ''
                },

                studijskiProgram:
                  student.studijskiProgram,

                mestoPrebivalista:
                  student.mestoPrebivalista,

                korisnik: {

                  korisnickoIme:
                    student.korisnik?.korisnickoIme ?? '',

                  email:
                    student.korisnik?.email ?? '',

                  id:
                    student.korisnik?.id ?? 0,

                  ime:
                    student.korisnik?.ime ?? '',

                  prezime:
                    student.korisnik?.prezime ?? ''
                }
              };
            }
          });

          this.studentNaGodiniService.getAll().subscribe((data) => {

            if (Array.isArray(data) && data.length > 0) {

              this.studentNaGodiniData = data.filter(
                studentNaGodini =>
                  studentNaGodini.student?.id === ulogovaniKorisnikId
              );

              console.log(
                'Student na godini:',
                this.studentNaGodiniData
              );
            }
          });

          break;

        // SLUŽBENIK
        case 'ROLE_SLUZBENIK':

          this.profileService.getSluzbenikProfile().subscribe((data) => {

            if (Array.isArray(data) && data.length > 0) {

              const sluzbenik = data.find(
                sluzbenik =>
                  sluzbenik.korisnik?.id === ulogovaniKorisnikId
              );

              if (!sluzbenik) {
                return;
              }

              this.sluzbenikData = {

                id: sluzbenik.id ?? null,

                korisnik:
                  sluzbenik.korisnik,

                korisnickoIme:
                  sluzbenik.korisnik?.korisnickoIme ?? 'N/A',

                imePrezime:
                  `${sluzbenik.korisnik?.ime ?? ''} ${sluzbenik.korisnik?.prezime ?? ''}`.trim(),

                email:
                  sluzbenik.korisnik?.email ?? 'N/A',

                telefon:
                  sluzbenik.telefon ?? 'N/A',

                jmbg:
                  sluzbenik.jmbg ?? 'N/A',

                nalogAktivan:
                  sluzbenik.nalogAktivan ?? false
              };
            }
          });

          break;
      }
    }
  }

  // POMOĆNE METODE

  formatirajUlogu(uloga: string): string {

    const bezPrefiksa = uloga.replace('ROLE_', '');

    return (
      bezPrefiksa.charAt(0) +
      bezPrefiksa.slice(1).toLowerCase()
    );
  }

  get jedinstveniEmailovi(): string[] {

    const emailovi = new Set<string>();

    if (this.adminData?.poslovniEmail) {
      emailovi.add(this.adminData.poslovniEmail);
    }

    if (this.nastavnikData?.poslovniMail) {
      emailovi.add(this.nastavnikData.poslovniMail);
    }

    return Array.from(emailovi);
  }

  get jedinstveniTelefoni(): string[] {

    const telefoni = new Set<string>();

    if (this.adminData?.telefon) {
      telefoni.add(this.adminData.telefon);
    }

    if (this.nastavnikData?.telefon) {
      telefoni.add(this.nastavnikData.telefon);
    }

    if (this.studentData?.telefon) {
      telefoni.add(this.studentData.telefon);
    }

    if (this.sluzbenikData?.telefon) {
      telefoni.add(this.sluzbenikData.telefon);
    }

    return Array.from(telefoni);
  }

  get prikazaniJMBG(): string | null {

    return (
      this.adminData?.jmbg ||
      this.nastavnikData?.jmbg ||
      this.studentData?.jmbg ||
      this.sluzbenikData?.jmbg ||
      null
    );
  }

  goBack(): void {
    this.location.back();
  }
}