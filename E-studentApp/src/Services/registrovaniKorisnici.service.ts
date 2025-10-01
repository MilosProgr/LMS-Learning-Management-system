import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../app/environments/environment';
import { RegistrovaniKorisnik } from '../models/registrovaniKorisnik';
import { LoginService } from './login.service';
import { CrudService } from '../app/generics/generic-service';

@Injectable({
  providedIn: 'root'
})
export class RegistrovaniKorisnikService extends CrudService<RegistrovaniKorisnik> {
  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/korisnici`);
  }
}
