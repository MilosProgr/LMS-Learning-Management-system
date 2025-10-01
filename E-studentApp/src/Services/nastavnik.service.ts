import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Nastavnik } from '../models/nastavnik.model';
import { CrudService } from '../app/generics/generic-service';
import { environment } from '../app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NastavnikService extends CrudService<Nastavnik> {
  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/nastavnici`);
  }

  getNastavniciBezFakulteta() {
    return this.http.get<Nastavnik[]>(`${environment.baseUrl}/api/nastavnici/slobodni-nastavnici-fakultet`)
  }
  
  getNastavniciBezUniverziteta() {
    return this.http.get<Nastavnik[]>(`${environment.baseUrl}/api/nastavnici/slobodni-nastavnici-univerzitet`)
  }

}