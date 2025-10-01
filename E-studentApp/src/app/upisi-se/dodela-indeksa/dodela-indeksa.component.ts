import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { StudentService } from '../../../Services/student/student.service';
import { RegistrovaniKorisnikService } from '../../../Services/registrovaniKorisnici.service';
import { GodinaStudijaService } from '../../../Services/godinaStudija/godinaStudijaModel.service';
import { Student } from '../../../models/student.model';
import { GodinaStudija } from '../../../models/godinaStudija/godinaStudija';
import { AsyncPipe, CommonModule, NgFor, NgIf } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { StudentNaGodiniService } from '../../../Services/student/studentNaGodini.service';
import { StudentNaGodini } from '../../../models/studentNaGodini/studentNaGodini';
import { RegistrovaniKorisnik } from '../../../models/registrovaniKorisnik';
import { MatFormField, MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatOption } from '@angular/material/core';
import { MatAutocomplete, MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatSelectModule } from '@angular/material/select';
import { map, Observable, startWith } from 'rxjs';
import { StudijskiProgramService } from '../../../Services/studijski-program.service';
import { StudijskiProgram } from '../../../models/studijskiprogramModel';

@Component({
  selector: 'app-dodela-indeksa',
  standalone: true,
  imports: [CommonModule, MatFormField, MatLabel, MatOption, ReactiveFormsModule, MatInputModule, MatFormFieldModule, MatAutocompleteModule, MatSelectModule],
  templateUrl: './dodela-indeksa.component.html',
  styleUrl: './dodela-indeksa.component.css'
})
export class DodelaIndeksaComponent implements OnInit {

  studenti: Student[] = [];
  korisnici: RegistrovaniKorisnik[] = [];
  godineStudija: GodinaStudija[] = [];
  studijskiProgrami: StudijskiProgram[] = [];

  form: FormGroup;
  errorMessage = "";

  filteredStudenti$!: Observable<Student[]>;

  constructor(
    private korisnikService: RegistrovaniKorisnikService,
    private studentService: StudentService,
    private godinaStudijaService: GodinaStudijaService,
    private studijskiProgramService: StudijskiProgramService,
    private studentNaGodiniService: StudentNaGodiniService,
    private fb: FormBuilder,
    private router: Router
  ) {
    this.form = this.fb.group({
      student: ['', [Validators.required, this.studentSelectionValidator()]],
      studijskiProgram: ['', Validators.required],
      godinaStudija: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadGodineStudije();
    this.loadStudenti();
    this.loadStudijskiProgrami();
    this.loadKorisnici();
  }

  displayStudent = (s?: Student | null): string =>
    s ? `${s.korisnik?.ime ?? ''} ${s.korisnik?.prezime ?? ''}`.trim() : '';

  private fullName(s: Student): string {
    return `${s.korisnik?.ime ?? ''} ${s.korisnik?.prezime ?? ''}`.trim();
  }

  addBrojIndeksa() {
    if (this.form.invalid) return;

    const selectedStudent = this.form.value.student as Student | null;
    if (!selectedStudent?.id) {
      this.errorMessage = 'Izaberite studenta iz liste.';
      return;
    }

    const payload: StudentNaGodini = {
      student: { id: Number(selectedStudent.id) },
      studijskiProgram: { id: Number(this.form.value.studijskiProgram) },
      godinaStudija: { id: Number(this.form.value.godinaStudija) }
    };

    this.studentNaGodiniService.create(payload).subscribe({
      next: (res) => {
        this.errorMessage = "Student je uspešno upisan i dodeljen mu je broj indeksa.";
        setTimeout(() => {
          this.errorMessage = '';
          this.router.navigate(['/obavestenja']);
        }, 2000);
      },
      error: (err) => {
        if (err.status === 409) {
          this.errorMessage = err.error?.message || 'Student je vec upisan!.';
        } else {
          console.error('Greška pri upisu:', err);
          this.errorMessage = "Došlo je do greške prilikom upisa studenta.";
        }
      }

    });
  }

  loadStudenti(): void {
    this.studentService.getAll().subscribe(data => {
      this.studenti = data;

      // pokretanje filtera
      this.filteredStudenti$ = this.form.get('student')!.valueChanges.pipe(
        startWith(this.form.get('student')!.value ?? ''),
        map((value: string | Student | null) =>
          typeof value === 'string' ? value : this.fullName(value as Student)
        ),
        map(name => {
          const q = (name ?? '').toLowerCase();
          return this.studenti.filter(s => this.fullName(s).toLowerCase().includes(q));
        })
      );
    });
  }

  loadGodineStudije(): void {
    this.godinaStudijaService.getAll().subscribe(data => {
      this.godineStudija = data;
    });
  }

  loadStudijskiProgrami(): void {
    this.studijskiProgramService.getAll().subscribe(data => {
      this.studijskiProgrami = data;
    });
  }

  loadKorisnici(): void {
    this.korisnikService.getAll().subscribe(data => {
      this.korisnici = data;
    });
  }
  private studentSelectionValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const v = control.value;
      // valid samo ako je izabran objekat sa id-jem (ne proizvoljan string)
      return v && typeof v === 'object' && 'id' in v ? null : { studentNotSelected: true };
    };
  }
}
