import { Injectable } from "@angular/core";
import { environment } from "../../app/environments/environment";
import { HttpClient } from "@angular/common/http";
import { CrudService } from "../../app/generics/generic-service";
import { GodinaStudija } from "../../models/godinaStudija/godinaStudija";

@Injectable({
    providedIn: 'root'
  })
  export class GodinaStudijaService extends CrudService<GodinaStudija> {
    constructor(private http: HttpClient) {
      super(http, `${environment.baseUrl}/api/godinaStudija`);
    }
  
  }