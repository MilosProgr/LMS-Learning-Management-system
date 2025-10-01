import { Component, Input, OnInit } from '@angular/core';
import { Obavestenje } from '../../models/obavestenja/obavestenjeModel';
import { CommonModule, NgClass, NgFor } from '@angular/common';
import { ObavestenjeService } from '../../Services/obavestenja/obavestenje.service';
import { Router } from '@angular/router';
import { LoginService } from '../../Services/login.service';
import { MatExpansionModule } from '@angular/material/expansion';
import { ObavestenjaAktivnostiService } from '../../Services/obavestenja/obavestenjaAktivnosti.service';

@Component({
  selector: 'app-obavestenja',
  standalone: true,
  imports: [NgFor, CommonModule, MatExpansionModule],
  templateUrl: './obavestenja-prikaz.component.html',
  styleUrl: './obavestenja-prikaz.component.css'
})
export class ObavestenjaPrikazComponent implements OnInit {

  listaObavestenja: Obavestenje[] = [];
  listaObavestenjaAktivnosti: any[] = [];
  procitanaObavestenjaAktivnosti: any[] = [];
  prijavljeniKorisnik = this.loginService.user;

  constructor(private obavestenjeService: ObavestenjeService,
    private obavestenjaAktivnostiService: ObavestenjaAktivnostiService, public loginService: LoginService,
    private router: Router) { }

  ngOnInit(): void {
    this.loadObavestenja();
    this.ucitajObavestenjaAktivnosti();
    console.log(this.listaObavestenja);
    console.log("Prijavljeni korisnik:", this.prijavljeniKorisnik);

  }

  loadObavestenja(): void {
    this.obavestenjeService.getAll().subscribe(listaObavestenja => {
      this.listaObavestenja = listaObavestenja;
      console.log(listaObavestenja);
    });
  }
  ucitajObavestenjaAktivnosti() {
    const userId = this.loginService.user?.id;   // id prijavljenog korisnika
    // console.log("ID korisnika: ", userId);

    this.obavestenjaAktivnostiService.getAll().subscribe(data => {
      //samo obaveštenja koja pripadaju ulogovanom korisniku
      // console.log("Podaci:", data);

      this.listaObavestenjaAktivnosti = data.filter((obavestenje: any) =>
        obavestenje.registrovaniKorisnik.id === userId &&
        obavestenje.procitano === false
      );
      console.log(this.listaObavestenjaAktivnosti);

      this.procitanaObavestenjaAktivnosti = data.filter((obavestenje: any) =>
        obavestenje.registrovaniKorisnik.id === userId &&
        obavestenje.procitano === true
      );
      console.log(this.procitanaObavestenjaAktivnosti);


    });
  }
  
  oznaciProcitano(obavestenje: any) {
    this.obavestenjaAktivnostiService.oznaciKaoProcitano(obavestenje.id).subscribe(() => {
      // ukloni iz liste nepročitanih
      this.listaObavestenjaAktivnosti = this.listaObavestenjaAktivnosti.filter(o => o.id !== obavestenje.id);

      // dodaj u listu pročitanih
      this.procitanaObavestenjaAktivnosti.push({ ...obavestenje, procitano: true });
    });
  }

  obrisiObavestenje(id: number): void {
    console.log("Id obavestenja je:", id);
      this.obavestenjeService.delete(id).subscribe(() => {
        this.loadObavestenja();
        window.location.reload();
      });
  }
}
