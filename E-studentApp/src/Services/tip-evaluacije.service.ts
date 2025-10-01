import { Injectable } from '@angular/core';
import { CrudService } from '../app/generics/generic-service';
import { TerminNastave } from '../models/terminNastaveModel';
import { HttpClient } from '@angular/common/http';
import { environment } from '../app/environments/environment';
import { TipEvaluacije } from '../models/tipEvaluacije';

@Injectable({
    providedIn: 'root'
})
export class TipEvaluacijeService extends CrudService<TipEvaluacije> {

    constructor(private http: HttpClient) {
        super(http, `${environment.baseUrl}/api/tipEvaluacije`);
    }
}
