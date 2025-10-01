import { Injectable } from "@angular/core";
import { CrudService } from "../../app/generics/generic-service";
import { Administrator } from "../../models/administrator/administrator";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../app/environments/environment";

@Injectable({
    providedIn: 'root'
  })
  export class AdministratorService extends CrudService<Administrator> {
    constructor(private http: HttpClient) {
      super(http, `${environment.baseUrl}/api/administratori`);
  
    }
  
  }