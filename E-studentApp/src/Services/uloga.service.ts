import { Injectable } from "@angular/core";
import { CrudService } from "../app/generics/generic-service";
import { HttpClient } from "@angular/common/http";
import { environment } from "../app/environments/environment";
import { Uloga } from "../models/uloga";

@Injectable({
  providedIn: 'root'
})
export class UlogaService extends CrudService<Uloga> {

  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/uloge`)
  }
}