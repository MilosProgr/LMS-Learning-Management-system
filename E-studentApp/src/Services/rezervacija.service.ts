// src/app/services/rezervacija.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Rezervacija } from '../models/rezervacija.model';
import { CrudService } from '../app/generics/generic-service';
import { environment } from '../app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RezervacijaService extends CrudService<Rezervacija> {
  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/rezervacije`);
  }

  updateRezervacija(id: number, rezervacija: Rezervacija): Observable<Rezervacija> {
    return this.http.put<Rezervacija>(`${environment.baseUrl}/api/rezervacije/${id}`, rezervacija);
  }  
}
