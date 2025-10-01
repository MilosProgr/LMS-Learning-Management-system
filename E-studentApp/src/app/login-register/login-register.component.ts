import { Component, ElementRef, ViewChild, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, ValidatorFn, ValidationErrors, AbstractControl } from '@angular/forms';
import { LoginService } from '../../Services/login.service';
import { Router } from '@angular/router';
import { RegistrovaniKorisnik } from '../../models/registrovaniKorisnik';
import { CommonModule } from '@angular/common';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-login-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule], // Ensure ReactiveFormsModule is imported here
  templateUrl: './login-register.component.html',
  styleUrl: './login-register.component.css'
})
export class LoginRegisterComponent implements OnInit {

  errorMessageLogin: string = '';
  errorMessageRegister: string = '';

  isSubmittedLogin = false;
  isSubmittedRegistration = false;

  loginForm!: FormGroup;
  registerForm!: FormGroup;

  @ViewChild('message') messageElement!: ElementRef;

  loginFailed = false;

  constructor(private loginService: LoginService, private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      ime: ['', [Validators.required, Validators.pattern('^[A-Z][a-zA-Z]*')]],
      prezime: ['', [Validators.required, Validators.pattern('^[A-Z][a-zA-Z]*')]],
      korisnickoIme: ['', [Validators.required, Validators.minLength(8), this.usernameAndPasswordValidator()]],
      lozinka: ['', [Validators.required, Validators.minLength(8), this.usernameAndPasswordValidator()]],
      email: ['', [Validators.required, Validators.email]]
    });

    this.loginForm = this.formBuilder.group({
      korisnickoIme: ['', Validators.required],
      lozinka: ['', Validators.required],
    });
  }

  // Logic for signup/login form interactions
  onSignupClick() {
    const message = this.messageElement.nativeElement;
    message.style.transform = 'translateX(100%)';
    if (message.classList.contains('login')) {
      message.classList.remove('login');
    }
    message.classList.add('signup');
  }

  onLoginClick() {
    const message = this.messageElement.nativeElement;
    message.style.transform = 'translateX(0)';
    if (message.classList.contains('signup')) {
      message.classList.remove('signup');
    }
    message.classList.add('login');
  }

  login() {
    if (this.loginForm.valid) {
      this.loginService.login(this.loginForm.value).pipe(
        catchError(err => {
          // console.error('Login error:', err);

          this.loginFailed = true;

          if (err.status === 409) {
            this.errorMessageLogin = err.error?.message || 'Već postoji zapis sa tim podacima.';
            this.ocistiErrorLogin();
          } else if (err.status === 401) {
            this.errorMessageLogin = err.error?.message || 'Neispravno korisničko ime ili lozinka.';
            this.ocistiErrorLogin();
          } else if (err.status === 404) {
            this.errorMessageLogin = err.error?.message || 'Korisnik ne postoji.';
            this.ocistiErrorLogin();
          } else if (err.status === 500) {
            this.errorMessageLogin = err.error?.message || 'Došlo je do greške u sistemu.';
            this.ocistiErrorLogin();
          } else {
            this.errorMessageLogin = 'Prijava bezuspešna! Pokušajte ponovo.';
            this.ocistiErrorLogin();
          }

          return of(null);
        })
      ).subscribe(res => {
        if (res) {
          this.isSubmittedLogin = true;
          this.errorMessageLogin = 'Prijava Uspesna!';
          setTimeout(() => {
            sessionStorage.setItem("token", String(res));
            this.router.navigate(['/obavestenja']);
          }, 1000);
        } else {
          this.loginFailed = true;
        }
      });
    } else {
      console.log('Form is invalid');
    }
  }

  register() {

    if (this.registerForm.valid) {
      const registrovaniKorisnik: RegistrovaniKorisnik = this.registerForm.value;
      this.loginService.register(registrovaniKorisnik).subscribe({
        next: (result) => {
          this.isSubmittedRegistration = true;
          setTimeout(() => {
            sessionStorage.setItem("token", String(result));
            this.router.navigate(['/obavestenja']);
            console.log("Token", result);
          }, 1000);
        },
        error: (err) => {
          // console.error("Registration error:", err);

          //Kasnije ispraviti to da se ispisuje poruka koju salje bekend, problem u responseType: 'text' u servisu
          if (err.status === 409) {
            this.errorMessageRegister = err.error?.message || 'Ne postoji uloga ili je nesto od podataka vec korisceno.';
            this.ocistiErrorRegister();
          } else if (err.status === 404) {
            this.errorMessageRegister = err.error?.message || 'Uloga nije pronađena. Kontaktirajte administratora.';
            this.ocistiErrorRegister();
          } else if (err.status === 400) {
            this.errorMessageRegister = err.error?.message || 'Uneti podaci nisu ispravni.';
            this.ocistiErrorRegister();
          } else if (err.status === 500) {
            this.errorMessageRegister = err.error?.message || 'Došlo je do greške u sistemu.';
            this.ocistiErrorRegister();
          } else {
            this.errorMessageRegister = 'Registracija neuspešna. Pokušajte ponovo.';
            this.ocistiErrorRegister();
          }

          this.isSubmittedRegistration = false;
        }
      });
    } else {
      console.log('Uneti podaci u formi nisu ispravni!');
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

  ocistiErrorLogin() {

    setTimeout(() => {
      this.errorMessageLogin = ""
    }, 2000);
  }

  ocistiErrorRegister() {
    setTimeout(() => {
      this.errorMessageLogin = ""
    }, 2000);
  }
}
