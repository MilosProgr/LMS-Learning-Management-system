import { Injectable } from "@angular/core";
import { CrudService } from "../app/generics/generic-service";
import { Drzava } from "../models/drzava";
import { HttpClient } from "@angular/common/http";
import { environment } from "../app/environments/environment";


@Injectable({
  providedIn: 'root'
})
export class DrzavaService extends CrudService<Drzava> {
  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/drzave`);
  }
}