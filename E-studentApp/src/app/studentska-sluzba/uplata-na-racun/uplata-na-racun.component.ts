import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { StudentService } from '../../../Services/student/student.service';
import { LoginService } from '../../../Services/login.service';
import { Student } from '../../../models/student.model';
import { map, Observable, startWith } from 'rxjs';
import { MatAutocompleteModule } from '@angular/material/autocomplete';


@Component({
  selector: 'app-uplata-na-racun',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatAutocompleteModule
  ],
  templateUrl: './uplata-na-racun.component.html',
  styleUrl: './uplata-na-racun.component.css'
})
export class UplataNaRacunComponent implements OnInit {

  stanje: number | null = null;
  loading = false;

  students: Student[] = [];
  filteredStudents$!: Observable<Student[]>;
  errorMessage = "";

  form = this.fb.group({
    student: new FormControl<Student | null>(null, { nonNullable: false, validators: [Validators.required] }),
    iznos: [null as number | null, [Validators.required, Validators.min(1)]]
  });

  constructor(
    private fb: FormBuilder,
    private studentService: StudentService,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.ucitajStudente();
    // ažuririranje prikaza stanja kad se izabere student
    this.form.controls.student.valueChanges.subscribe(sel => {
      this.stanje = sel?.stanjeNaRacunu ?? null;
    });
  }

  private ucitajStudente(): void {
    this.studentService.getStudent().subscribe({
      next: (lista) => {
        this.students = lista ?? [];

        const prijavljeniId = this.loginService?.user?.id;
        if (prijavljeniId) {
          const ja = this.students.find(s => s?.id === prijavljeniId || (s as any)?.korisnik?.id === prijavljeniId);
          if (ja) {
            this.form.controls.student.setValue(ja);
          }
        }

        this.filteredStudents$ = this.form.controls.student.valueChanges.pipe(
          startWith(this.form.controls.student.value),
          map(val => this.filterStudents(val))
        );
      },
      error: () => {
      }
    });
  }

  // prikaz imena u inputu
  displayStudent = (s?: Student | null): string => s ? this.getFullName(s) : '';

  //dobavljanje i kreiranje punog imena
  getFullName(s: Student): string {
    const k = (s as any)?.korisnik ?? {};
    const ime = (s as any)?.ime ?? k?.ime ?? '';
    const prezime = (s as any)?.prezime ?? k?.prezime ?? '';
    return `${ime ?? ''} ${prezime ?? ''}`.trim();
  }

  private filterStudents(val: string | Student | null): Student[] {
    const term = (typeof val === 'string' ? val : (val ? this.getFullName(val) : ''))
      .toLowerCase()
      .trim();

    if (!term) return this.students.slice(0, 50);
    return this.students.filter(s => this.getFullName(s).toLowerCase().includes(term));
  }

  uplati(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    const selected = this.form.value.student as Student | null;
    if (!selected?.id) {
      return;
    }

    const iznos = this.form.value.iznos!;
    this.loading = true;

    this.studentService.UvecajStanjeNaRacunu(selected.id, iznos).subscribe({
      next: (student: any) => {
        this.loading = false;
        this.stanje = typeof student?.stanjeNaRacunu === 'number'
          ? student.stanjeNaRacunu
          : ((this.stanje ?? 0) + iznos);
        this.form.controls.iznos.reset();
        this.errorMessage = "Uplata uspesno izvrsena."
      },
      error: (err) => {
        this.loading = false;
        if (err.status === 409) {
        this.errorMessage = err.error?.message || "Doslo je do greske kod uplate."
        }

      }
    });
  }
  
  reset(): void {
    this.form.reset();
  }
}