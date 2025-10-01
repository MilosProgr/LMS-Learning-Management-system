import { Injectable } from "@angular/core";
import { CrudService } from "../app/generics/generic-service";
import { Sifra } from "../models/sifra";
import { HttpClient } from "@angular/common/http";
import { environment } from "../app/environments/environment";

@Injectable({
    providedIn: 'root'
  })
  export class SifraService extends CrudService<Sifra> {
    constructor(private http: HttpClient) {
      super(http, `${environment.baseUrl}/api/sifre`);
    }
  
  
  }