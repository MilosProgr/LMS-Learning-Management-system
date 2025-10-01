import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Adresa } from '../models/adresaModel';
import { CrudService } from '../app/generics/generic-service';
import { environment } from '../app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdresaService extends CrudService<Adresa> {
  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/adrese`);
  }


}
