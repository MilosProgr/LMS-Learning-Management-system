import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Predmet } from "../../src/models/predmetModel";
import { HttpClient } from '@angular/common/http';
import { CrudService } from '../app/generics/generic-service';
import { environment } from '../app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PredmetService extends CrudService<Predmet> {

  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/predmeti`);
  }

  getPredmetiByProgram(programId: number): Observable<Predmet[]> {
    return this.http.get<Predmet[]>(`${environment.baseUrl}/api/predmeti/program/${programId}`);
  }

  getPredmetiZaStudenta(studentId: number): Observable<Predmet[]> {
    return this.http.get<Predmet[]>(`${environment.baseUrl}/api/predmeti/student-predmet/${studentId}`);
  }

  getByStudent(studentId: number) {
    return this.http.get<Predmet>(`${this.baseUrl}/${studentId}`);
  }


}
