import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Univerzitet } from '../models/univerzitetModel';
import { CrudService } from '../app/generics/generic-service';
import { environment } from '../app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UniverzitetService extends CrudService<Univerzitet> {
  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/univerziteti`);

  }

  createUniverzitet(payload: any) {
    return this.http.post(`${environment.baseUrl}/api/univerziteti/univeriztetAdd`, payload);
  }
  updateUniverzitet(id: number, payload: any) {
    return this.http.put(`${environment.baseUrl}/api/univerziteti/univerzitetEdit/${id}`, payload);
  }
}
