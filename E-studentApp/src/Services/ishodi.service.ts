import { Injectable } from '@angular/core';
import { Predmet } from '../models/predmetModel';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ishod } from '../models/ishodModel';
import { CrudService } from '../app/generics/generic-service';
import { environment } from '../app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class IshodService extends CrudService<Ishod> {

  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/ishodi`);
  }

  getIshodi(): Observable<Ishod[]> {
    return this.http.get<Ishod[]>(`${environment.baseUrl}/api/ishodi`);
  }

  updateOpis(ishod: Ishod): Observable<Ishod> {
    return this.http.put<Ishod>(`${`${environment.baseUrl}/api/ishodi`}/${ishod.id}`, ishod);
  }
}
