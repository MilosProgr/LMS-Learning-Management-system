import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../app/environments/environment';
import { CrudService } from '../../app/generics/generic-service';
import { UserPermission, UserPermissionList } from '../../models/pravaPristupa/userPermissionModel';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class UserPermissionService extends CrudService<UserPermission> {

  private korisniciSaUlogamaList = `${environment.baseUrl}/api/korisniciSaUlogama/lista`
  constructor(private http: HttpClient) {
    super(http, `${environment.baseUrl}/api/korisniciSaUlogama`)
  }

  // Nova metoda za poziv drugog URL-a
  getKorisniciSaUlogamaList(): Observable<UserPermissionList[]> {
    return this.http.get<UserPermissionList[]>(this.korisniciSaUlogamaList);
  }
}
