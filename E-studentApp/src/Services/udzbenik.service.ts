import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CrudService } from '../app/generics/generic-service';
import { environment } from '../app/environments/environment';
import { Udzbenik } from '../models/udzbenik.model';

@Injectable({
  providedIn: 'root'
})
export class UdzbenikService extends CrudService<Udzbenik> {
  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/udzbenici`);
  }
}
