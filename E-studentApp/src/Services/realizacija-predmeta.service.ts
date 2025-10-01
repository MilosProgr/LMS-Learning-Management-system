import { Injectable } from '@angular/core';
import { CrudService } from '../app/generics/generic-service';
import { RealizacijaPredmeta } from '../models/realizacijaPredmetaModel';
import { HttpClient } from '@angular/common/http';
import { environment } from '../app/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RealizacijaPredmetaService extends CrudService<RealizacijaPredmeta> {


  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/realizacijaPredmeta`);
  }
  getRealizacijeWithTerminiAndTipNastave(predmetId: number): Observable<RealizacijaPredmeta[]> {
    return this.http.get<RealizacijaPredmeta[]>(`${environment.baseUrl}/api/realizacijaPredmeta/predmet/${predmetId}/realizacija-termini`)
  }

  //   getRealizacijaForNastavnik(nastavnikId: number) {
  //   return this.http.get<any[]>(`/api/realizacija/nastavnik/${nastavnikId}`);
  // }
}
