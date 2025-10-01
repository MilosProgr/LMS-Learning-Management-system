import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../app/environments/environment';
import { CrudService } from '../app/generics/generic-service';
import { StudijskiProgram } from '../models/studijskiprogramModel';

@Injectable({
  providedIn: 'root'
})
export class StudijskiProgramService extends CrudService<StudijskiProgram> {

  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/programi`)
  }
}
