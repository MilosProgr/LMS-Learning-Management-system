import { Component } from '@angular/core';
import { NavItem } from '../../generics/nav-bar-model';
import { Fakultet } from '../../../models/fakultetModel';
import { FakultetService } from '../../../Services/fakultet.service';
import { LoginService } from '../../../Services/login.service';
import { Router, RouterModule } from '@angular/router';
import { NgClass, NgFor, NgIf } from '@angular/common';
import { NavBarGenericComponent } from '../../generics/nav-bar-generic/nav-bar-generic.component';
import { FakultetListComponent } from '../../fakultet/fakultet-list/fakultet-list.component';

@Component({
  selector: 'app-nav-bar-neregistrovani',
  standalone: true,
  imports: [NgIf, NgClass, RouterModule, NgFor, FakultetListComponent, NavBarGenericComponent],
  templateUrl: './nav-bar-neregistrovani.component.html',
  styleUrl: './nav-bar-neregistrovani.component.css'
})
export class NavBarNeregistrovaniComponent {
  
  navItems: NavItem[] = [  
    // { label: 'E-Student', link: '/obavestenja', isVisible: true },
    { label: 'Univerziteti', link: '/univerzitet-home', isVisible: true },
    { label: 'Fakulteti', link: '/fakultet-home', isVisible: true },

    { label: 'Prijavi se', isButton: true, action: () => this.navigateToLogin(), isVisible: true }
  ] 

  fakulteti: Fakultet[] = [];

  constructor(
    private fakultetService: FakultetService,
    public loginService: LoginService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.loadFakulteti();
  }

  
  loadFakulteti(): void {
    this.fakultetService.getAll().subscribe(fakulteti => {
      this.fakulteti = fakulteti;
    });
  }

  //metoda za navigaciju na profil
  navigateToProfile() {
    this.router.navigate(['/moj-profil']);
  }

    // Metod za log out
    odjava() {
      this.loginService.logout();
      this.router.navigate(['/']); // Navigacija na login stranicu
    }

    navigateToLogin() {
      this.router.navigate(['/prijava']);
    }
}
