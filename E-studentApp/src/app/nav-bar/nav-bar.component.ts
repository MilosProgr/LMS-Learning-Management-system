import { NgClass, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { Fakultet } from '../../models/fakultetModel';
import { FakultetService } from '../../Services/fakultet.service';
import { LoginService } from '../../Services/login.service';
import { NavBarGenericComponent } from '../generics/nav-bar-generic/nav-bar-generic.component';
import { NavItem } from '../generics/nav-bar-model';
import { FakultetListComponent } from '../fakultet/fakultet-list/fakultet-list.component';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [NgIf, NgClass, RouterModule, NgFor, FakultetListComponent, NavBarGenericComponent],
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'] // Corrected to styleUrls
})
export class NavBarComponent implements OnInit {

  navItems: NavItem[] = [  
      { label: 'E-Student', link: '/obavestenja', isVisible: true },
      { label: 'Univerziteti', link: '/univerzitet-home', isVisible: true },
      { label: 'Fakulteti', link: '/fakultet-home', isVisible: true },

      { label: 'Profil', isButton: true, action: () => this.navigateToProfile(), isVisible: true },
      { label: 'Odjavi se', isButton: true, action: () => this.odjava(), isVisible: true }
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

  navigateToProfile() {
    this.router.navigate(['/moj-profil']);
  }

  // Metod za log out
  odjava() {
    this.loginService.logout();
    this.router.navigate(['/']); // Navigacija na login stranicu
  }
  
}
