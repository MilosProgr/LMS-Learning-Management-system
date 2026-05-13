import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../app/environments/environment';
import { RegistrovaniKorisnikOsnovniPodaci } from '../models/registrovaniKorisnikOsnovniPodaci';
import { Administrator } from '../models/administrator/administrator';
import { Nastavnik } from '../models/nastavnik.model';
import { Student } from '../models/student.model';
import { SluzbenikStudentske } from '../models/sluzbenikStudentske/sluzbenikStudentske';
import { Rezervacija } from '../models/rezervacija.model';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  constructor(private http: HttpClient) { }


  getKorisnikPodaci(): Observable<RegistrovaniKorisnikOsnovniPodaci> {
    return this.http.get<RegistrovaniKorisnikOsnovniPodaci>(`${environment.baseUrl}/api/korisnici/osnovniPodaci`);
  }

  getAdminProfile(): Observable<Administrator[]> {
    return this.http.get<Administrator[]>(`${environment.baseUrl}/api/administratori`);
  }

  getNastavnikProfile(): Observable<Nastavnik[]> {
    return this.http.get<Nastavnik[]>(`${environment.baseUrl}/api/nastavnici`);
  }

  getStudentProfile(): Observable<Student[]> {
    return this.http.get<Student[]>(`${environment.baseUrl}/api/studenti`);
  }

  getSluzbenikProfile(): Observable<SluzbenikStudentske[]> {
    return this.http.get<SluzbenikStudentske[]>(`${environment.baseUrl}/api/sluzbenici`);
  }

  submitVacationRequest(request: Rezervacija): Observable<Rezervacija> {
    return this.http.post<Rezervacija>(`${environment.baseUrl}/api/rezervacije`, request);
  }



}
