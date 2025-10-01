import { Injectable } from "@angular/core";
import { CrudService } from "../app/generics/generic-service";
import { HttpClient } from "@angular/common/http";
import { environment } from "../app/environments/environment";
import { EvaluacijaZnanja } from "../models/evaluacijaZnanja";
import { Observable } from "rxjs";
import { CreateEvaluacijaZnanjaRequest } from "../models/CreateEvaluacijaZnanjaRequest Model";


@Injectable({
    providedIn: 'root'
})
export class EvaluacijaZnanjaService extends CrudService<EvaluacijaZnanja> {
    constructor(private http: HttpClient) {
        super(http, `${environment.baseUrl}/api/evaluacijaZnanja`);
    }


    /** Poseban endpoint za kreiranje evaluacije: POST /api/upis-bodova */
    kreirajEvaluaciju(req: CreateEvaluacijaZnanjaRequest) {
        return this.http.post<EvaluacijaZnanja>(`${environment.baseUrl}/api/evaluacijaZnanja/upis-bodova`,req);
    }
}