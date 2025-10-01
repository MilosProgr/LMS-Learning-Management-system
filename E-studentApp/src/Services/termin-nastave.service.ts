import { Injectable } from '@angular/core';
import { CrudService } from '../app/generics/generic-service';
import { TerminNastave } from '../models/terminNastaveModel';
import { HttpClient } from '@angular/common/http';
import { environment } from '../app/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TerminNastaveService extends CrudService<TerminNastave> {

  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/tNastave`);
  }
}
