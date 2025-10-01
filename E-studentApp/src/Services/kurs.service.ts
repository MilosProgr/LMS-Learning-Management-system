import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Kurs } from "../../src/models/kursModel";
import { HttpClient } from '@angular/common/http';
import { CrudService } from '../app/generics/generic-service';
import { environment } from '../app/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class KursService extends CrudService<Kurs> {

    constructor(private http: HttpClient) {
        super(http, `${environment.baseUrl}/api/kursevi`);
    }


}
