import { Component, OnInit } from '@angular/core';
import { RegistrovaniKorisnik } from '../../../models/registrovaniKorisnik';
import { RegistrovaniKorisnikService } from '../../../Services/registrovaniKorisnici.service';
import { FormField } from '../../generics/generic-form/form-model';
import { AbstractControl, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { GenericFormComponent } from '../../generics/generic-form/generic-form.component';
import { GenericReusableTableComponent } from '../../generics/generic-reusable-table/generic-reusable-table.component';
import { LoginService } from '../../../Services/login.service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-registrovani-korisnici',
  standalone: true,
  imports: [NgIf, RouterLink, GenericFormComponent, GenericReusableTableComponent],
  templateUrl: './registrovani-korisnici.component.html',
  styleUrl: './registrovani-korisnici.component.css'
})
export class RegistrovaniKorisniciComponent implements OnInit {
  registrovaniKorisnici: RegistrovaniKorisnik[] = [];
  errorMessage= "";

  kljuceviRegistrovaniKorisnik = [
    { imeKolone: 'Korisnicko Ime', kljuc: 'korisnickoIme' },
    { imeKolone: 'Id', kljuc: 'id'},
    // { imeKolone: 'Lozinka', kljuc:  'lozinka'}
  ];

  constructor(private registrovaniKorisniciService: RegistrovaniKorisnikService, private loginService: LoginService) {}

  ngOnInit(): void {
    this.loadRegistrovaniKorisnici();
  }

  formFieldsRegistrovaniKorisnik: FormField[] = [
    {
      type: 'text',
      label: 'Korisnicko Ime: ',
      name: 'korisnickoIme',
      validations: [Validators.required, Validators.minLength(8) ,this.usernameAndPasswordValidator()]
    },
    {
      type: 'password',
      label: 'Lozinka: ',
      name: 'lozinka',
      validations: [Validators.required, Validators.minLength(8), this.usernameAndPasswordValidator()]
    },
    {
      type: 'text',
      label: 'Ime: ',
      name: 'ime',
      validations: [Validators.required, Validators.pattern('^[A-Z][a-zA-Z]*')]
    },
    {
      type: 'text',
      label: 'Prezime: ',
      name: 'prezime',
      validations: [Validators.required, Validators.pattern('^[A-Z][a-zA-Z]*')]
    },
    {
      type: 'text',
      label: 'Email: ',
      name: 'email',
      validations: [Validators.required, Validators.email]
    }
  ];

  loadRegistrovaniKorisnici(): void {
    this.registrovaniKorisniciService.getAll().subscribe((data) => {
      this.registrovaniKorisnici = data;
      // if (this.registrovaniKorisnici.length > 0) {
      //   this.kljuceviRegistrovaniKorisnici = Object.keys(this.registrovaniKorisnici[0]);
        console.log("kljucevi za registrovane korisnike su:", this.kljuceviRegistrovaniKorisnik);
        console.log("Registrovani korisnici su:", this.registrovaniKorisnici);
      // }
    });
  }

  addRegistrovaniKorisnik(formFieldsRegistrovaniKorisnik: any) {

    const noviKorisnik: RegistrovaniKorisnik = {
      ime: formFieldsRegistrovaniKorisnik.ime,
      prezime: formFieldsRegistrovaniKorisnik.prezime,
      korisnickoIme: formFieldsRegistrovaniKorisnik.korisnickoIme,
      lozinka: formFieldsRegistrovaniKorisnik.lozinka,
      email: formFieldsRegistrovaniKorisnik.email,
      roles: []
    };

    this.loginService.register(noviKorisnik).subscribe(response => {
      if (response) {
        console.log('Korisnik uspešno registrovan:', response);
        this.loadRegistrovaniKorisnici();

        this.errorMessage="Studijski program uspesno dodat."
        // Obrisati poruku nakon 2 sekunde
        setTimeout(() => {
          this.errorMessage = ''; 
        }, 2000);
      } else {
        console.error('Došlo je do greške prilikom registracije novog korisnika');
      }
    });
  }

  deleteRegistrovaniKorisnik(id: number): void {
    this.registrovaniKorisniciService.delete(id).subscribe(() => {
      this.loadRegistrovaniKorisnici(); // Ponovo učitavanje liste nakon brisanja
    });
  }

  // Prilagođeni validator za korisničko ime i lozinku
  usernameAndPasswordValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const value = control.value;
    // Proverava da li korisničko ime sadrži barem jedno veliko slovo i jedan broj
    const hasUpperCase = /[A-Z]/.test(value);
    const hasNumber = /\d/.test(value);
    const valid = hasUpperCase && hasNumber;

    return valid ? null : { usernameInvalid: true };
  };
}
}