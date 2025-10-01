import { Injectable } from "@angular/core";
import { CrudService } from "../app/generics/generic-service";
import { NastavniMaterijal } from "../models/nastavni-materijal.model";
import { HttpClient } from "@angular/common/http";
import { environment } from "../app/environments/environment";

@Injectable({
  providedIn: 'root'
})
export class NastavniMaterijalService extends CrudService<NastavniMaterijal> {
  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/nasMaterijali`);
  }
}
