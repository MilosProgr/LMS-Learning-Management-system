import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  constructor(private http: HttpClient) {}

  
  getKorisnikPodaci(): Observable<any> {
    return this.http.get(`${environment.baseUrl}/api/korisnici/osnovniPodaci`);
  }

  getAdminProfile(): Observable<any> {
    return this.http.get(`${environment.baseUrl}/api/administratori`);
  }

  getNastavnikProfile(): Observable<any> {
    return this.http.get(`${environment.baseUrl}/api/nastavnici`);
  }

  getStudentProfile(): Observable<any> {
    return this.http.get(`${environment.baseUrl}/api/studenti`);
  }

  getSluzbenikProfile(): Observable<any> {
    return this.http.get(`${environment.baseUrl}/api/sluzbenici`);
  }

  submitVacationRequest(request: any): Observable<any> {
    return this.http.post(`${environment.baseUrl}/api/rezervacije`, request);
  }

  
  
}
