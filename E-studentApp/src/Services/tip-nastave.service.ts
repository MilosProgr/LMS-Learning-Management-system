import { Injectable } from '@angular/core';
import { CrudService } from '../app/generics/generic-service';
import { TipNastave } from '../models/tipNastave';
import { HttpClient } from '@angular/common/http';
import { environment } from '../app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TipNastaveService extends CrudService<TipNastave> {

  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/tipNastave`);
  }
}
