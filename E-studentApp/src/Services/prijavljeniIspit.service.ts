import { Injectable } from "@angular/core";
import { PrijavljeniIspit } from "../models/prijavljenIspit";
import { HttpClient } from "@angular/common/http";
import { CrudService } from "../app/generics/generic-service";
import { environment } from "../app/environments/environment";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})


export class PrijavljeniIspitService extends CrudService<PrijavljeniIspit> {

    constructor(private http: HttpClient) {
        super(http, `${environment.baseUrl}/api/prijavljeniIspiti`);
    }

    prijavi(body: {
        studentId: number; studentNaGodiniId: number; predmetId: number; ispitniRokId: number;
        cenaPrijave?: number | null;}): Observable<PrijavljeniIspit> {
        return this.http.post<PrijavljeniIspit>(`${environment.baseUrl}/api/prijavljeniIspiti/prijavi`, body);
    }
}