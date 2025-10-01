import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CrudService } from '../app/generics/generic-service';
import { environment } from '../app/environments/environment';
import { FileInstrumentEvaluacije } from '../models/FileInstrumentEvaluacijeModel';

@Injectable({
  providedIn: 'root'
})
export class InstrumentEvaluacijeService extends CrudService<FileInstrumentEvaluacije> {
  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/file/instrumentiEvaluacije`);
  }
}