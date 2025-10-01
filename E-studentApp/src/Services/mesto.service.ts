import { Injectable } from '@angular/core';
import { CrudService } from '../app/generics/generic-service';
import { Mesto } from '../models/mesto';
import { HttpClient } from '@angular/common/http';
import { environment } from '../app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MestoService extends CrudService<Mesto> {
  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/mesta`);
  }


}