import { CommonModule, NgClass, NgIf } from '@angular/common';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, RouterModule, NavigationEnd } from '@angular/router';
import { Subscription } from 'rxjs';
import { LoginService } from '../../Services/login.service';
import { ObavestenjeService } from '../../Services/obavestenja/obavestenje.service';
import { RegistrovaniKorisnik } from '../../models/registrovaniKorisnik';
import { StudentService } from '../../Services/student/student.service';
import { Student } from '../../models/student.model';

@Component({
  selector: 'app-side-bar',
  standalone: true,
  imports: [RouterModule, NgIf, NgClass, CommonModule],
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent implements OnInit, OnDestroy {
  private routeSubscription: Subscription | undefined;

  private prijavljeniKorisnik: RegistrovaniKorisnik = this.loginService.user
  protected mojStudent: Student | undefined
  isStudent = false;

  constructor(
    private loginService: LoginService,
    private router: Router,
    public obavestenjeService: ObavestenjeService,
    private studentService: StudentService
  ) { }

  ngOnInit() {
    this.isStudent = this.loginService.validateRoles(['ROLE_STUDENT']); // ⬅️ utvrdi rolu

    if (this.isStudent) {
      this.loadStudent(); 
    }

    this.routeSubscription = this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        if (event.url.includes('/obavestenja')) {
          // this.obavestenjeService.resetNotificationCount();
        }
      }
        if (this.isStudent) this.loadStudent(); //da bi se refresovao prikaz side bara
    });
  }

  ngOnDestroy() {
    if (this.routeSubscription) {
      this.routeSubscription.unsubscribe();
    }
  }

  proveraUloge(uloga: string): boolean {
    return this.loginService.validateRoles([uloga]);
  }

  private loadStudent(): void {
    const korisnikId = this.prijavljeniKorisnik?.id;

    // usko sužavanje tipa: odbaci ako je undefined/null ili NaN
    if (korisnikId == null || !Number.isFinite(korisnikId)) {
      console.error('Nedostaje ID  ulogovanog korisnika.');
      return;
    }
    this.studentService.getOne(korisnikId).subscribe({
      next: (student) => {
        this.mojStudent = student
        // console.log("Moj ulogovani student: ", this.mojStudent);
        
      },
      error: (err) => {
        console.error(err);
        console.error('Greška pri učitavanju studenta.');
      }
    });
  }
}
