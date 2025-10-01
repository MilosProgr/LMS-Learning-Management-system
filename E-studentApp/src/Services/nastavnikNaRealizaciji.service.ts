import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CrudService } from '../app/generics/generic-service';
import { environment } from '../app/environments/environment';
import { NastavnikNaRealizaciji } from '../models/nastavnikNaRealizacijiModel';

@Injectable({
  providedIn: 'root'
})
export class NastavnikNaRealizacijiService extends CrudService<NastavnikNaRealizaciji> {
  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/nastavniciNaRealizaciji`);
  }

}