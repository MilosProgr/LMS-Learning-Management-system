import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { LoginService } from '../../Services/login.service';
import { ProfileService } from '../../Services/profile-service.service';
import { NastavnikService } from '../../Services/nastavnik.service';
import { RezervacijaService } from '../../Services/rezervacija.service';

import { Rezervacija } from '../../models/rezervacija.model';
import { Nastavnik } from '../../models/nastavnik.model';
import { StatusOdmora } from '../../models/status-odmora.enum';
interface ProfileData {
  korisnickoIme: string | undefined;
  ime?: string;
  prezime?: string;
  imePrezime?: string;
  brojSlobodnihDana: number | null;
  brojIskoristenihDana: number | undefined;
  nastavnikId?: number | null;
}

@Component({
  selector: 'app-zahtev-za-godisnji-odmor',
  templateUrl: './zahtev-za-godisnji-odmor.component.html',
  styleUrls: ['./zahtev-za-godisnji-odmor.component.css'],
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule, ReactiveFormsModule]
})
export class ZahtevZaGodisnjiOdmorComponent implements OnInit {

  userRole: string = '';

  profileData: ProfileData | null = null;

  datumOd: string = '';
  datumDo: string = '';
  opis: string = '';

  nastavnici: Nastavnik[] = [];

  message: string = '';
  messageType: string = '';

  rezervacije: Rezervacija[] = [];

  constructor(
    private loginService: LoginService,
    private profileService: ProfileService,
    private nastavniciService: NastavnikService,
    private rezervacijeService: RezervacijaService
  ) { }

  ngOnInit(): void {
    this.loadUserRole();
    this.loadProfileData();
    this.loadNastavnici();
    this.loadRezervacije();
  }

  private loadRezervacije(): void {
    this.rezervacijeService.getAll().subscribe({
      next: (rezervacije: Rezervacija[]) => {
        this.rezervacije = rezervacije;
        console.log(rezervacije);
      },
      error: (error) => {
        console.error('Greška pri učitavanju rezervacija:', error);
      }
    });
  }

  private loadUserRole(): void {
    const token = sessionStorage.getItem('token');

    if (token) {
      try {
        const decodedToken = JSON.parse(atob(token.split('.')[1]));
        const roles = Array.isArray(decodedToken.roles)
          ? decodedToken.roles
          : [];

        this.userRole = roles[0] || '';
      } catch (e) {
        console.error('Error decoding token:', e);
      }
    } else {
      console.log('No token found in session storage.');
    }

    console.log('User Role:', this.userRole);
  }

  private loadNastavnici(): void {
    this.nastavniciService.getAll().subscribe({
      next: (nastavnici: Nastavnik[]) => {
        this.nastavnici = nastavnici;
        console.log(nastavnici);
      },
      error: (error) => {
        console.error('Greška pri učitavanju nastavnika:', error);
      }
    });
  }

  private loadProfileData(): void {

    if (!this.userRole) {
      console.error('No user role defined.');
      return;
    }

    switch (this.userRole) {

      case 'ROLE_ADMIN':

        this.profileService.getAdminProfile().subscribe({
          next: (adminData) => {

            if (Array.isArray(adminData) && adminData.length > 0) {

              const admin = adminData[0];

              this.profileData = {
                korisnickoIme: admin.korisnickoIme,
                imePrezime: admin.imePrezime,
                brojSlobodnihDana: admin.brojSlobodnihDana,
                brojIskoristenihDana: admin.brojIskoristenihDana
              };

            } else {
              console.error('No admin data available.');
            }
          },
          error: (error) => {
            console.error('Greška pri učitavanju admin profila:', error);
          }
        });

        break;

      case 'ROLE_NASTAVNIK':

        this.profileService.getNastavnikProfile().subscribe({
          next: (nastavnikData) => {

            if (Array.isArray(nastavnikData) && nastavnikData.length > 0) {

              const nastavnik = nastavnikData[0];

              this.profileData = {
                korisnickoIme: nastavnik.korisnickoIme,
                ime: nastavnik.ime,
                prezime: nastavnik.prezime,
                brojSlobodnihDana: nastavnik.brojSlobodnihDana ?? 0,
                brojIskoristenihDana: nastavnik.brojIskoristenihDana ?? 0,
                nastavnikId: nastavnik.id
              };

            } else {
              console.error('No nastavnik data available.');
            }
          },
          error: (error) => {
            console.error('Greška pri učitavanju nastavnik profila:', error);
          }
        });

        break;

      case 'ROLE_STUDENT':

        this.profileService.getStudentProfile().subscribe({
          next: (studentData) => {

            if (Array.isArray(studentData) && studentData.length > 0) {

              const student = studentData[0];

              this.profileData = {
                korisnickoIme: student.korisnickoIme,
                ime: student.ime,
                prezime: student.prezime,
                brojSlobodnihDana: student.brojSlobodnihDana ?? 0,
                brojIskoristenihDana: student.brojIskoristenihDana ?? 0
              };

            } else {
              console.error('No student data available.');
            }
          },
          error: (error) => {
            console.error('Greška pri učitavanju student profila:', error);
          }
        });

        break;

      default:
        console.log('No valid user role found');
    }
  }

  submitRequest(): void {

    if (!this.profileData?.nastavnikId) {
      console.error('Nastavnik ID not available');
      return;
    }

    const existingRequest = this.rezervacije.find(
      (rezervacija) =>
        rezervacija.nastavnikId === this.profileData?.nastavnikId &&
        rezervacija.statusOdmora === StatusOdmora.PENDING
    );

    if (existingRequest) {
      this.setMessage(
        'Već imate aktivan zahtev za godišnji odmor!',
        'error'
      );
      return;
    }

    const workdays = this.calculateWorkdays(
      this.datumOd,
      this.datumDo
    );

    const today = new Date();

    const startDate = new Date(this.datumOd);

    const minRequestDate = new Date();

    minRequestDate.setDate(today.getDate() + 15);

    if (this.profileData.brojSlobodnihDana as number < workdays) {

      this.setMessage(
        'Nemate dovoljno slobodnih dana da kreirate zahtev',
        'error'
      );

      return;
    }

    if (startDate < minRequestDate) {

      this.setMessage(
        'Zahtev mora da se kreira najmanje 15 dana unapred!',
        'error'
      );

      return;
    }

    const request: Rezervacija = {
      datumOd: this.datumOd,
      datumDo: this.datumDo,
      opis: this.opis,

      nastavnik: {
        id: this.profileData.nastavnikId
      } as Nastavnik,

      statusOdmora: StatusOdmora.PENDING
    };

    this.profileService.submitVacationRequest(request).subscribe({
      next: (response) => {

        console.log(
          'Zahtev za godišnji odmor uspešno kreiran',
          response
        );

        this.setMessage(
          'Zahtev za godišnji odmor uspešno kreiran',
          'success'
        );
      },

      error: (error) => {

        console.error(
          'Greška pri kreiranju zahteva',
          error
        );

        this.setMessage(
          'Greška pri kreiranju zahteva',
          'error'
        );
      }
    });
  }

  private setMessage(message: string, type: string): void {
    this.message = message;
    this.messageType = type;
  }

  private calculateWorkdays(
    startDate: string,
    endDate: string
  ): number {

    const start = new Date(startDate);
    const end = new Date(endDate);

    let workdays = 0;

    while (start <= end) {

      const dayOfWeek = start.getDay();

      if (dayOfWeek !== 0 && dayOfWeek !== 6) {
        workdays++;
      }

      start.setDate(start.getDate() + 1);
    }

    return workdays;
  }

  getMinDate(): string {

    const today = new Date();

    today.setDate(today.getDate() + 1);

    return today.toISOString().split('T')[0];
  }
}