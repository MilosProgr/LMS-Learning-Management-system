import { Component, Input } from '@angular/core';
import { NavItem } from '../nav-bar-model';
import { Router, RouterLink } from '@angular/router';
import { NgClass, NgFor, NgIf } from '@angular/common';
import { LoginService } from '../../../Services/login.service';

@Component({
  selector: 'app-nav-bar-generic',
  standalone: true,
  imports: [NgClass, RouterLink, NgIf, NgFor],
  templateUrl: './nav-bar-generic.component.html',
  styleUrl: './nav-bar-generic.component.css'
})
export class NavBarGenericComponent {

  @Input() navItems: NavItem[] = []; 

  imeKorisnika: string = '';
  ulogaKorisnika: string = '';
  user = this.loginService.user;

  constructor(private router: Router, private loginService: LoginService) {}

  ngOnInit(): void {

    if (this.user) {
      console.log("Moj ulogovani korisnik: ", this.user);

      this.imeKorisnika = this.user.sub || this.user.korisnickoIme || 'Nepoznat';
      this.ulogaKorisnika = Array.isArray(this.user.roles) ? this.user.roles[0].replace('ROLE_', '') : '';

      console.log(`Ime korisnika: ${this.imeKorisnika}, uloga korisnika ${this.ulogaKorisnika}`);

    }
  }

  postaviBojuIkoniceProfila(): string {
    if (!Array.isArray(this.user.roles)) return 'text-secondary';

    const roles = this.user.roles.map((r: string) => r.replace('ROLE_', '').toUpperCase());

    // Prioritetni redosled: ADMIN > SLUZBA > NASTAVNIK > STUDENT > KORISNIK
    if (roles.includes('ADMIN')) return 'text-danger';      // crvena
    if (roles.includes('SLUZBA')) return 'text-warning';    // žuta
    if (roles.includes('NASTAVNIK')) return 'text-success'; // zelena
    if (roles.includes('STUDENT')) return 'text-student';   // zlatna

    return 'text-secondary';
  }


}
