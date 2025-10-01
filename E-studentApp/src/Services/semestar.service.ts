import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../app/environments/environment';
import { CrudService } from '../app/generics/generic-service';
import { Semestar } from '../models/semestarModel';

@Injectable({
  providedIn: 'root'
})
export class SemestarService extends CrudService<Semestar> {
  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/semestri`);
  }
}