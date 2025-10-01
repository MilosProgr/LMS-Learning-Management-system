import { Component, OnInit } from '@angular/core';
import { RegistrovaniKorisnik } from '../../../../models/registrovaniKorisnik';
import { FormField } from '../../../generics/generic-form/form-model';
import { ActivatedRoute, Router } from '@angular/router';
import { RegistrovaniKorisnikService } from '../../../../Services/registrovaniKorisnici.service';
import { AbstractControl, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { GenericFormComponent } from '../../../generics/generic-form/generic-form.component';

@Component({
  selector: 'app-registrovani-korisnici-edit',
  standalone: true,
  imports: [GenericFormComponent],
  templateUrl: './registrovani-korisnici-edit.component.html',
  styleUrl: './registrovani-korisnici-edit.component.css'
})
export class RegistrovaniKorisniciEditComponent implements OnInit {
  
  registrovaniKorisnikData: RegistrovaniKorisnik | null = null;
  registrovaniKorisnici: RegistrovaniKorisnik[] = [];

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

  constructor(
    private registrovaniKorisniciService: RegistrovaniKorisnikService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadRegistrovaniKorisnici();
    const id = Number(this.route.snapshot.paramMap.get('id'));
    console.log("ID izabranog korisnika je:", id);

    if (!isNaN(id) && id > 0) {  // Provera da li je validan broj i da li je pozitivan
      this.registrovaniKorisniciService.getOne(id).subscribe({
        next: (data) => {
          this.registrovaniKorisnikData = data;
          console.log("Korisnik koji se menja je: ", this.registrovaniKorisnikData);

          // Ažuriraj vrednosti formFields-a
          this.formFieldsRegistrovaniKorisnik= [
            {
              type: 'text',
              label: 'Korisnicko Ime: ',
              name: 'korisnickoIme',
              validations: [Validators.required, Validators.minLength(8) ,this.usernameAndPasswordValidator()],
              value: this.registrovaniKorisnikData?.korisnickoIme || '' 
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
              validations: [Validators.required, Validators.pattern('^[A-Z][a-zA-Z]*')],
              value: this.registrovaniKorisnikData?.ime || '' 
            },
            {
              type: 'text',
              label: 'Prezime: ',
              name: 'prezime',
              validations: [Validators.required, Validators.pattern('^[A-Z][a-zA-Z]*')],
              value: this.registrovaniKorisnikData?.prezime || '' 
            },
            {
              type: 'text',
              label: 'Email: ',
              name: 'email',
              validations: [Validators.required, Validators.email],
              value: this.registrovaniKorisnikData?.email || '' 
            }
          ];
          console.log("Novi podaci za prikaz su: ", this.formFieldsRegistrovaniKorisnik);
        },
        error: (err) => console.error('Greška prilikom učitavanja korisnika', err)
      });
    } else {
      console.error('Nevalidan ID: ', id); // Loguje grešku ako ID nije validan
    }
  }

  loadRegistrovaniKorisnici(): void {
    this.registrovaniKorisniciService.getAll().subscribe((data) => {
      this.registrovaniKorisnici = data;
    });
  }

  handleFormSubmit(updatedData: RegistrovaniKorisnik | any): void {
    console.log("Novi uneti podaci: ", updatedData);

    if (updatedData) {
      const id = Number(this.route.snapshot.paramMap.get('id')); 

      const payload: RegistrovaniKorisnik = {
        id: id, 
        ime: updatedData.ime,
        prezime: updatedData.prezime,
        korisnickoIme: updatedData.korisnickoIme,
        lozinka: updatedData.lozinka,
        email: updatedData.email,
        roles: this.registrovaniKorisnikData?.roles || [] // Zadrži postojeće uloge
      };
      console.log("Payload za update: ", payload);

      this.registrovaniKorisniciService.update(id, payload).subscribe({
        next: () => {
          this.router.navigate(['/registrovaniKorisnik-list']);
        },
        error: (err) => console.error('Greška prilikom ažuriranja korisnika', err)
      });
    } else {
      console.error('ID izabranog korisnika nije validan');
    }
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
