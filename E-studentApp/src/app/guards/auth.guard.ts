import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { LoginService } from '../../Services/login.service';

export const authGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot) => {

  if (inject(LoginService).loggedIn && inject(LoginService).validateRoles(route.data["allowedRoles"])) {

    return true;
  } else {
    // inject(Router).navigate(['/poruka-o-pristupu']);
    console.log("Korisnik nema pristup ovoj opciji!");
    return false;
  }
};
