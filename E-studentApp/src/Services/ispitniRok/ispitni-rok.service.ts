import { Injectable } from '@angular/core';
import { CrudService } from '../../app/generics/generic-service';
import { IspitniRok } from '../../models/ispitniRok/ispitniRokModel';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class IspitniRokService extends CrudService<IspitniRok> {

  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/rokovi`)
  }

    getRokoviKojiNisuProsli() {
      return this.http.get<IspitniRok[]>(`${environment.baseUrl}/api/rokovi/aktivni`)
    }
}
