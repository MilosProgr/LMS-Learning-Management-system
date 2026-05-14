import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, RouterStateSnapshot } from '@angular/router';
import { LoginService } from '../../Services/login.service';

export const authGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot) => {

  if (inject(LoginService).loggedIn && inject(LoginService).validateRoles(route.data["allowedRoles"])) {
    console.log(state);// This line is just to avoid the unused variable warning for 'state'
    return true;
  } else {
    // inject(Router).navigate(['/poruka-o-pristupu']);
    console.log("Korisnik nema pristup ovoj opciji!");
    return false;
  }
};
