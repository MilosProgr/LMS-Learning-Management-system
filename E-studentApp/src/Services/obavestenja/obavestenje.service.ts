import { Injectable } from "@angular/core";
import { CrudService } from "../../app/generics/generic-service";
import { Obavestenje } from "../../models/obavestenja/obavestenjeModel";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../app/environments/environment";
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ObavestenjeService extends CrudService<Obavestenje> {
  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/opstaObavestenja`);
  }

  override create(obavestenje: Obavestenje) {
    return super.create(obavestenje); 
  }

}
