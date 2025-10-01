import { Injectable } from "@angular/core";
import { CrudService } from "../../app/generics/generic-service";
import { StudentNaGodini } from "../../models/studentNaGodini/studentNaGodini";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../app/environments/environment";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class StudentNaGodiniService extends CrudService<StudentNaGodini> {
  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/studentNaGodini`);
  }

  getStudentFromGodina(studentId: number): Observable<StudentNaGodini[]> {
    return this.http.get<StudentNaGodini[]>(`${environment.baseUrl}/api/studentNaGodini/student/${studentId}`)
  }

  getAllStudentNaGodini(): Observable<StudentNaGodini[]> {
    return this.http.get<StudentNaGodini[]>(`${environment.baseUrl}/api/studentNaGodini`);
  }


}