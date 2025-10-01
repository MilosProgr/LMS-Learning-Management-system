import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Fakultet } from '../models/fakultetModel';
import { CrudService } from '../app/generics/generic-service';
import { environment } from '../app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FakultetService extends CrudService<Fakultet> {
  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/fakulteti`);
  }

  createFakultet(payload: any) {
  return this.http.post(`${environment.baseUrl}/api/fakulteti/fakultetAdd`, payload);
}

updateFakultet(id: number, payload: any) {
  return this.http.put(`${environment.baseUrl}/api/fakulteti/fakultetEdit/${id}`, payload);
}

}
