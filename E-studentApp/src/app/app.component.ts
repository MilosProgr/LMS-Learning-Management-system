import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterOutlet } from '@angular/router';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { SideBarComponent } from './side-bar/side-bar.component';
import { LoginService } from '../Services/login.service';
import { NavBarNeregistrovaniComponent } from './nav-bar/nav-bar-neregistrovani/nav-bar-neregistrovani.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, NavBarComponent, NavBarNeregistrovaniComponent, SideBarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  trenutnaRuta: string = '';

  constructor(private router: Router, private activatedRoute: ActivatedRoute, public loginService: LoginService) { 
    this.trenutnaRuta = this.router.url;
    // console.log(this.trenutnaRuta);
  }

  ngOnInit(): void {
    this.router.events.subscribe(() => {
      this.trenutnaRuta = this.router.url;
      // console.log('Trenutna ruta:', this.trenutnaRuta); // Debugging
    });
  }

  isUniverzitetHomeRoute(): boolean {
    return this.trenutnaRuta.startsWith('/univerzitet-home');
  }

  // Metod za proveru uloga korisnika
  proveraUloge(uloga: string): boolean {
    return this.loginService.validateRoles([uloga]);
  }

  }

