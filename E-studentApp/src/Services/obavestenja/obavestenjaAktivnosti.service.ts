import { Injectable } from "@angular/core";
import { CrudService } from "../../app/generics/generic-service";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../app/environments/environment";
import { BehaviorSubject, Observable } from 'rxjs';
import { ObavestenjeAktivnosti } from "../../models/obavestenja/obavestenjaAktivnostiModel";

@Injectable({
  providedIn: 'root'
})
export class ObavestenjaAktivnostiService extends CrudService<ObavestenjeAktivnosti> {

  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/obavestenjaAktivnost`);
  }

  oznaciKaoProcitano(id: number): Observable<void> {
    return this.http.patch<void>(`${this.baseUrl}/${id}/procitano`, {});
  }


  //http://localhost:8080/api/obavestenjaAktivnost/{id}/procitano

}