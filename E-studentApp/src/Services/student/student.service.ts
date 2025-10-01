import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Student } from '../../models/student.model';
import { CrudService } from '../../app/generics/generic-service';
import { environment } from '../../app/environments/environment';
import { UpisStudentaRequest } from '../../models/UpisStudentaRequestModel';

@Injectable({
  providedIn: 'root'
})
export class StudentService extends CrudService<Student> {

  private apiUrl = 'http://localhost:8080/api/studenti';
  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/studenti`);
  }
  getStudent(): Observable<Student[]> {
    return this.http.get<Student[]>(this.apiUrl);
  }

  updateStudent(student: Student): Observable<Student> {
    return this.http.put<Student>(`${this.apiUrl}/${student.id}`, student);
  }

  //metoda za uvecanje stanja na racunu studenta
  UvecajStanjeNaRacunu(studentId: number, iznos: number): Observable<Student> {
    return this.http.patch<Student>(`${this.apiUrl}/${studentId}/stanje`, { iznos });
  }

  upisiStudneta(payload: UpisStudentaRequest): Observable<Student> {
    return this.http.post<Student>(`${this.apiUrl}/upis-studenta`, payload);
  }
}
