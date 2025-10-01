import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { Student } from '../../models/student.model';
import { StudentService } from '../../Services/student/student.service';
import { StudentNaGodiniService } from '../../Services/student/studentNaGodini.service';
import { NgxPaginationModule } from 'ngx-pagination';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-student',
  standalone: true,
  imports: [CommonModule, FormsModule, TranslateModule, NgxPaginationModule],
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {
  students: Student[] = [];
  filteredStudents: Student[] = [];
  searchText: string = '';
  currentPage: number = 1;
  itemsPerPage: number = 10;
  isLoading: boolean = false;
  error: string = '';

  constructor(
    private studentService: StudentService,
    private studentNaGodiniService: StudentNaGodiniService,
    private translate: TranslateService
  ) {
    translate.setDefaultLang('lat');
    translate.use('lat');
  }

  ngOnInit(): void {
    this.fetchStudents();
  }

  fetchStudents(): void {
  this.isLoading = true;
  this.error = '';

  this.studentService.getAll().subscribe({
    next: (students: Student[]) => {
      if (!students || students.length === 0) {
        this.students = [];
        this.filteredStudents = [];
        this.isLoading = false;
        return;
      }

      this.studentNaGodiniService.getAllStudentNaGodini().subscribe({
        next: (allSNG) => {
          students.forEach(student => {
            const sngMatch = allSNG.find(sng => sng.student?.id === student.id);
              if (sngMatch) {
                student.studentNaGodini = { 
                  brojIndeksa: sngMatch.brojIndeksa ?? undefined 
                };

                student.studijskiProgram = sngMatch.studijskiProgram
                  ? { 
                      id: sngMatch.studijskiProgram.id, 
                      naziv: sngMatch.studijskiProgram.naziv ?? '' 
                    } 
                  : undefined;
              }
          });

          this.students = students;
          this.filteredStudents = [...students];
          this.isLoading = false;

          console.log('Students merged with StudentNaGodini:', this.students);
        },
        error: (err) => {
          console.error('Error fetching all StudentNaGodini:', err);
          this.students = students;
          this.filteredStudents = [...students];
          this.isLoading = false;
        }
      });
    },
    error: (error) => {
      console.error('Error fetching students:', error);
      this.error = 'Greška pri učitavanju studenata: ' + (error.message || error);
      this.isLoading = false;
      this.students = [];
      this.filteredStudents = [];
    }
  });
}


  filterStudents(): void {
    if (!this.searchText || this.searchText.trim() === '') {
      this.filteredStudents = [...this.students];
      this.currentPage = 1;
      return;
    }

    const searchTerm = this.searchText.toLowerCase().trim();
    console.log('Filtering students with term:', searchTerm);

    this.filteredStudents = this.students.filter(student => {
      try {
        const ime = this.getNestedProperty(student, 'korisnik.ime', '').toLowerCase();
        const prezime = this.getNestedProperty(student, 'korisnik.prezime', '').toLowerCase();
        const jmbg = (student.jmbg || '').toString().toLowerCase();
        const telefon = (student.telefon || '').toString().toLowerCase();
        const brojIndeksa = this.getNestedProperty(student, 'studentNaGodini.brojIndeksa', '').toString().toLowerCase();
        const programNaziv = this.getNestedProperty(student, 'studijskiProgram.naziv', '').toLowerCase();

        return ime.includes(searchTerm) || 
               prezime.includes(searchTerm) || 
               jmbg.includes(searchTerm) || 
               telefon.includes(searchTerm) || 
               brojIndeksa.includes(searchTerm) || 
               programNaziv.includes(searchTerm);
      } catch (error) {
        console.warn('Error filtering student:', student, error);
        return false;
      }
    });

    this.currentPage = 1;
    console.log('Filtered results:', this.filteredStudents.length);
  }

  getNestedProperty(obj: any, path: string, defaultValue: any = ''): any {
    try {
      return path.split('.').reduce((current, key) => {
        return current && current[key] !== undefined ? current[key] : defaultValue;
      }, obj);
    } catch {
      return defaultValue;
    }
  }

  getFormattedAddress(student: Student): string {
    try {
      const ulica = this.getNestedProperty(student, 'adresa.ulica', '');
      const broj = this.getNestedProperty(student, 'adresa.broj', '');
      const grad = this.getNestedProperty(student, 'adresa.grad', '');
      
      let address = '';
      if (ulica) address += ulica;
      if (broj) address += (address ? ' ' + broj : broj);
      if (grad) address += (address ? ', ' + grad : grad);
      
      return address || 'N/A';
    } catch {
      return 'N/A';
    }
  }

  onPageChange(page: number): void {
    this.currentPage = page;
  }

  refreshData(): void {
    this.fetchStudents();
  }
}