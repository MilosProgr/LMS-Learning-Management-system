import { Injectable } from "@angular/core";
import { CrudService } from "../app/generics/generic-service";
import { PohadjanjePredmeta } from "../models/pohadjanjePredmeta";
import { HttpClient } from "@angular/common/http";
import { environment } from "../app/environments/environment";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class PohadjanjePredmetaService extends CrudService<PohadjanjePredmeta> {
    constructor(private http: HttpClient) {
        super(http, `${environment.baseUrl}/api/PohadjanjePredmeta`)
    }

    prijaviPredmete(studentNaGodiniId: number, predmetiIds: number[]): Observable<any> {
        return this.http.post(
            `${environment.baseUrl}/api/PohadjanjePredmeta/${studentNaGodiniId}`,
            predmetiIds
        );
    }

    getByStudentNaGodiniId(sngId: number): Observable<PohadjanjePredmeta[]> {
        return this.http.get<PohadjanjePredmeta[]>(
            `${environment.baseUrl}/api/PohadjanjePredmeta/student-na-godini/${sngId}`
        );
    }

    patchOcenaById(ppId: number, ocena: number) {
  return this.http.patch(`${this.baseUrl}/${ppId}`,
    { konacnaOcena: ocena },
    { headers: { 'Content-Type': 'application/merge-patch+json' } });
}



}