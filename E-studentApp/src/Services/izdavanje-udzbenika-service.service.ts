import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CrudService } from '../app/generics/generic-service';
import { environment } from '../app/environments/environment';

export interface IzdavanjeUdzbenika {
  id: number;
  podnosilac_zahteva_id: number;
  odobreno: boolean | null;
  naziv?: string;
    studijskiProgram?: { id: number; naziv?: string };
}


@Injectable({
  providedIn: 'root'
})
export class IzdavanjeUdzbenikService extends CrudService<IzdavanjeUdzbenika> {
  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/izdavanjeUdzbenika`);
  }
}