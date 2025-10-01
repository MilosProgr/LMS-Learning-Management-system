import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginService } from '../../Services/login.service';
import { ProfileService } from '../../Services/profile-service.service';
import { NastavnikService } from '../../Services/nastavnik.service';
import { Rezervacija } from '../../models/rezervacija.model';
import { RezervacijaService } from '../../Services/rezervacija.service';

@Component({
  selector: 'app-zahtev-za-godisnji-odmor',
  templateUrl: './zahtev-za-godisnji-odmor.component.html',
  styleUrls: ['./zahtev-za-godisnji-odmor.component.css'],
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule, ReactiveFormsModule]
})
export class ZahtevZaGodisnjiOdmorComponent implements OnInit {
  userRole: string = "";
  profileData: any;
  datumOd: string = '';
  datumDo: string = '';
  opis: string = '';
  nastavnici: any;
  message: string = '';
  messageType: string = '';
  rezervacije: Rezervacija[] = [];

  constructor(
    private loginService: LoginService,
    private profileService: ProfileService,
    private nastavniciService: NastavnikService,
    private rezervacijeService: RezervacijaService
  ) {}

  ngOnInit(): void {
    this.loadUserRole();
    this.loadProfileData();
    this.loadNastavnici();
    this.loadRezervacije();
  }

  private loadRezervacije(): void {
    this.rezervacijeService.getAll().subscribe(rezervacija => {
      this.rezervacije = rezervacija;
      console.log(rezervacija)
    })
  }

  private loadUserRole(): void {
    const token = sessionStorage.getItem("token");
    if (token) {
      try {
        const decodedToken = JSON.parse(atob(token.split(".")[1]));
        const roles = Array.isArray(decodedToken.roles) ? decodedToken.roles : [];
        this.userRole = roles[0] || "";
      } catch (e) {
        console.error('Error decoding token:', e);
      }
    } else {
      console.log('No token found in local storage.');
    }
    console.log('User Role:', this.userRole);
  }

  private loadNastavnici(): void {
    this.nastavniciService.getAll().subscribe(nastavnik => {
      this.nastavnici = nastavnik;
      console.log(nastavnik);
    });
  }

  private loadProfileData(): void {
    if (!this.userRole) {
      console.error('No user role defined.');
      return;
    }
  
    switch (this.userRole) {
      case 'ROLE_ADMIN':
        this.profileService.getAdminProfile().subscribe((adminData) => {
          if (Array.isArray(adminData) && adminData.length > 0) {
            const admin = adminData[0];
            this.profileData = {
              korisnickoIme: admin.korisnickoIme,
              imePrezime: admin.imePrezime,
              brojSlobodnihDana: admin.brojSlobodnihDana,
              brojIskoristenihDana: admin.brojIskoristenihDana
            };
          } else {
            console.error('No admin data available or data format is incorrect');
          }
        });
        break;
  
      case 'ROLE_NASTAVNIK':
        this.profileService.getNastavnikProfile().subscribe((nastavnikData) => {
          if (Array.isArray(nastavnikData) && nastavnikData.length > 0) {
            const nastavnik = nastavnikData[0];
            this.profileData = {
              korisnickoIme: nastavnik.korisnickoIme,
              ime: nastavnik.ime,
              prezime: nastavnik.prezime,
              brojSlobodnihDana: nastavnik.brojSlobodnihDana,
              brojIskoristenihDana: nastavnik.brojIskoristenihDana,
              nastavnikId: nastavnik.id
            };
          } else {
            console.error('No nastavnik data available or data format is incorrect');
          }
        });
        break;

      case 'ROLE_STUDENT':
        this.profileService.getStudentProfile().subscribe((studentData) => {
          if (Array.isArray(studentData) && studentData.length > 0) {
            const student = studentData[0];
            this.profileData = {
              korisnickoIme: student.korisnickoIme,
              ime: student.ime,
              prezime: student.prezime,
              brojSlobodnihDana: student.brojSlobodnihDana,
              brojIskoristenihDana: student.brojIskoristenihDana
            };
          } else {
            console.error('No student data available or data format is incorrect');
          }
        });
        break;

      default:
        console.log('No valid user role found');
    }
  }

  submitRequest(): void {
    if (!this.profileData || !this.profileData.nastavnikId) {
        console.error('Nastavnik ID not available');
        return;
    }

    // Check if a reservation already exists for the current teacher
    const existingRequest = this.rezervacije.find(rezervacija => rezervacija.nastavnikId === this.profileData.nastavnikId && rezervacija.statusOdmora === 'PENDING');
    if (existingRequest) {
        this.setMessage('Već imate aktivan zahtev za godišnji odmor!', 'error');
        return;
    }

    const workdays = this.calculateWorkdays(this.datumOd, this.datumDo);
    const today = new Date();
    const startDate = new Date(this.datumOd);
    const minRequestDate = new Date();
    minRequestDate.setDate(today.getDate() + 15);

    if (this.profileData.brojSlobodnihDana < workdays) {
        this.setMessage('Nemate dovoljno slobodnih dana da kreirate zahtev', 'error');
        return;
    }

    if (startDate < minRequestDate) {
        this.setMessage('Zahtev mora da se kreira najmanje 15 dana unapred!', 'error');
        return;
    }

    const request = {
        datumOd: this.datumOd,
        datumDo: this.datumDo,
        opis: this.opis,
        nastavnik: {
            id: this.profileData.nastavnikId
        },
        statusOdmora: 'PENDING'
    };

    this.profileService.submitVacationRequest(request).subscribe(
        (response) => {
            console.log('Zahtev za godišnji odmor uspešno kreiran', response);
            this.setMessage('Zahtev za godišnji odmor uspešno kreiran', 'success');
        },
        (error) => {
            console.error('Error pri kreiranju zahteva', error);
            this.setMessage('Error pri kreiranju zahteva', 'error');
        }
    );
  }


  private setMessage(message: string, type: string): void {
    this.message = message;
    this.messageType = type;
  }

  private calculateWorkdays(startDate: string, endDate: string): number {
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
