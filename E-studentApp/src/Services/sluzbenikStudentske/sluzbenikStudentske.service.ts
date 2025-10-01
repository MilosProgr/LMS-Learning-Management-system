import { Injectable } from "@angular/core";
import { CrudService } from "../../app/generics/generic-service";
import { SluzbenikStudentske } from "../../models/sluzbenikStudentske/sluzbenikStudentske";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../app/environments/environment";

@Injectable({
    providedIn: 'root'
  })
  export class SluzbenikStudentskeService extends CrudService<SluzbenikStudentske> {
    constructor(private http: HttpClient) {
      super(http, `${environment.baseUrl}/api/sluzbenici`);
  
    }
  }