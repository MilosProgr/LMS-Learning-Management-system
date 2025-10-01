import { HttpClient } from '@angular/common/http';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { BehaviorSubject, of, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from '../app/environments/environment';
import { RegistrovaniKorisnik } from '../models/registrovaniKorisnik';
import { Token } from '../models/token';
import { Router } from '@angular/router';
import { RegistrovaniKorisnikService } from './registrovaniKorisnici.service';
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  registrovaniKorisnici: RegistrovaniKorisnik[] = [];
  private baseUrl = environment.baseUrl;

  token: string | null = null;
  user: any = null;
  rolesSubject: BehaviorSubject<Set<string>> = new BehaviorSubject<Set<string>>(new Set([]));
  loggedOut = false;
  loggedIn = false;

  constructor(private client: HttpClient, @Inject(PLATFORM_ID) private platformId: Object, private router: Router, private registrovaniKorisniciService: RegistrovaniKorisnikService) {
    this.initializeUserFromToken();  // Inicijalizuj korisnika pri osvežavanju stranice
  }

  login(user: RegistrovaniKorisnik) {
    console.log('Attempting login with user:', user);
    return this.client.post(`${this.baseUrl}/api/login`, user, { responseType: 'text' }).pipe(
      tap((token: string) => {
        console.log('Received token:', token);
        if (token) {
          try {
            const decodedToken = JSON.parse(atob(token.split(".")[1]));
            console.log('Dekodirani token:', decodedToken);
            console.log('Roles u dekodiranom tokenu:', decodedToken.roles);

            // Save the token to session storage
            sessionStorage.setItem("token", token);

            // Set the user and loggedIn status
            this.user = {
              ...decodedToken,
              roles: Array.isArray(decodedToken.roles) ? decodedToken.roles : [] // Ensure roles is an array
            };
            this.loggedIn = true;

            const roles = new Set<string>(this.user.roles);
            this.rolesSubject.next(roles);

            console.log('Podaci korisnika:', this.user);
            console.log(this.getUserRoles());
          } catch (e) {
            console.error('Error decoding token:', e);
          }
        }
      }),
      catchError(error => {
        console.error('Login greska:', error);
        return throwError(() => error);
      })
    );
  }

  register(user: RegistrovaniKorisnik) {
    console.log('Pokusaj polaganja sa:', user);
    const role = "ROLE_KORISNIK"
    // console.log('Registracija se vrsi dodeljivanjem:', role);

    return this.client.post(`${this.baseUrl}/api/register/${role}`, user, { responseType: 'text' }).pipe(
      tap((token: string) => {
        console.log('Received token during registration:', token);
        if (token) {
          try {
            sessionStorage.setItem("token", token);

            // Automatically log the user in after registration
            // this.login(user).subscribe();

          } catch (e) {
            console.error('Error handling token after registration:', e);
          }
        }
      }),
      catchError(error => {
        // console.error('Register error:', error);
      return throwError(() => error);
      })
    );
  }

  logout(): void {
    this.token = null;
    this.user = null;
    this.rolesSubject.next(new Set<string>([]));
    this.loggedOut = true;
    this.loggedIn = false;
    sessionStorage.removeItem("token");
  }

  validateRoles(roles: string[]): boolean {
    if (this.user && Array.isArray(this.user.roles)) {
      // console.log("Validacija role:", this.user, this.user.roles);
      const userRoles = new Set<string>(this.user.roles); // Pretvaranje u Set za bržu pretragu
      // console.log('Korisničke uloge:', userRoles);
      const result = roles.some(role => userRoles.has(role)); // Proveravamo da li neka od zadanih uloga postoji u korisničkim ulogama
      // console.log('Rezultat validacije:', result);
      return result;
    } else {
      const token = sessionStorage.getItem("token");
      // console.log("Token iz storage-a:", token);
      if (token) {
        try {
          const decodedToken = this.decodeToken(token);
          const userRoles = new Set<string>(decodedToken.roles || []); // Extract roles from decoded token
          // console.log('Korisničke uloge iz tokena:', userRoles);
          const result = roles.some(role => userRoles.has(role)); // Check roles from token
          // console.log('Rezultat validacije iz tokena:', result);
          this.loggedIn = true;
          return result;
        } catch (error) {
          console.error('Greška prilikom dekodiranja tokena:', error);
        }
      }
      return false;
    }
  }

  // Method to decode a JWT token and extract the payload
  decodeToken(token: string): any {
    try {
      const base64Url = token.split('.')[1]; // Get the payload part of the JWT
      const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/'); // Replace URL-safe characters
      const jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
      }).join(''));
      return JSON.parse(jsonPayload);
    } catch (error) {
      console.error('Greška prilikom dekodiranja JWT tokena:', error);
      throw error; // Re-throw to handle in validateRoles
    }
  }

  getUserRoles(): Set<string> {
    // console.log('Retrieved user roles:', new Set(this.user?.roles || []));
    return new Set(this.user?.roles || []);
  }

  loadRegistrovaniKorisnici(): void {
    this.registrovaniKorisniciService.getAll().subscribe((data) => {
      this.registrovaniKorisnici = data;
    });
  }

  initializeUserFromToken(): void {
    const token = sessionStorage.getItem("token");
    if (token) {
      try {
        const decodedToken = this.decodeToken(token);
        this.user = {
          ...decodedToken,
          roles: Array.isArray(decodedToken.roles) ? decodedToken.roles : []
        };
        this.loggedIn = true;
        const roles = new Set<string>(this.user.roles);
        this.rolesSubject.next(roles);

        console.log('Korisnik inicijalizovan iz tokena:', this.user);
      } catch (error) {
        console.error('Greška prilikom inicijalizacije korisnika iz tokena:', error);
        this.logout();  // U slučaju greške, izloguj korisnika
      }
    }
  }

}
