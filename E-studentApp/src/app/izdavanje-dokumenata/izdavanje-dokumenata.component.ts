import { Component, OnInit } from '@angular/core';
import { jsPDF } from 'jspdf';
import { Student } from '../../models/student.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';
import { StudentService } from '../../Services/student/student.service';

@Component({
  selector: 'app-izdavanje-dokumenata',
  standalone: true,
  imports: [FormsModule, CommonModule, NgxPaginationModule],
  templateUrl: './izdavanje-dokumenata.component.html',
  styleUrls: ['./izdavanje-dokumenata.component.css']
})
export class IzdavanjeDokumenataComponent implements OnInit {

  students: Student[] = [];
  expandedStudentId: number | undefined;
  searchTerm: string = '';
  itemsPerPage: number = 8;
  page: number = 1;

  constructor(private studentService: StudentService) { }

  ngOnInit(): void {
    this.loadStudents();
  }

  loadStudents(): void {
    this.studentService.getStudent().subscribe((students: Student[]) => {
      this.students = students;
      console.log("Studenti", students);
    });
  }

  toggleExpandedRow(studentId?: number): void {
    if (studentId) {
      this.expandedStudentId = this.expandedStudentId === studentId ? undefined : studentId;
    }
  }


  getExpandedStudent(): Student | undefined {
    return this.students.find(student => student.id === this.expandedStudentId);
  }

  filterStudents(): Student[] {
    if (!this.searchTerm) {
      return this.students;
    }
    const term = this.searchTerm.toLowerCase();
    return this.students.filter(student =>
      student.korisnik.ime.toLowerCase().includes(term) ||
      student.korisnik.prezime.toLowerCase().includes(term)
      // student.korisnik.jmbg.includes(term)
    );
  }

  get paginatedStudents(): Student[] {
    const filtered = this.filterStudents();
    const startIndex = (this.page - 1) * this.itemsPerPage;
    return filtered.slice(startIndex, startIndex + this.itemsPerPage);
  }

  onTableDataChange(event: number): void {
    this.page = event;
  }

  generatePDF(documentType: string, student: Student): void {
    const doc = new jsPDF();

    if (!student) {
      alert('Please select a student first!');
      return;
    }

    if (documentType === 'punomoc') {
      this.generatePunomoc(doc, student);
    } else if (documentType === 'ugovor') {
      this.generateUgovor(doc, student);
    } else if (documentType === 'sporazum') {
      this.generateSporazum(doc, student);
    }

    doc.save(`${documentType}_${student.korisnik.ime}.pdf`);
  }

  generatePunomoc(doc: jsPDF, student: Student): void {
    doc.setFontSize(20);
    doc.setFont("Helvetica", "bold");
    doc.text('UNIVERZITET SINGIDUNUM', 105, 20, { align: 'center' });

    doc.setFontSize(16);
    doc.text('PUNOMOC', 105, 40, { align: 'center' });

    doc.setFontSize(12);
    doc.setFont("Helvetica", "normal");

    doc.text('Ovim se punomocem ovlascuje:', 10, 60);
    doc.text(`Student: ${student.korisnik.ime} ${student.korisnik.prezime},`, 10, 70);
    // doc.text(`sa JMBG: ${student.korisnik.jmbg},`, 10, 80);
    doc.text(`i prebivalistem u: ${student.mestoPrebivalista?.naziv}.`, 10, 90);
    doc.text('Da zastupa sve interese studenta u vezi sa:', 10, 110);
    doc.text('1. Upisom na fakultet.', 10, 120);
    doc.text('2. Ispitima i ocjenama.', 10, 130);
    doc.text('3. Svim drugim administrativnim poslovima.', 10, 140);
    doc.text(`Datum: ${this.getCurrentDate()}`, 10, 160);

    doc.text('Potpis ovlascenika: ____________________', 10, 180);
    doc.text('Potpis studenta: ____________________', 10, 190);

    doc.setDrawColor(0);
    doc.setFillColor(200, 220, 255);
    // doc.rect(10, 10, 190, 30, 'F');
  }

  generateUgovor(doc: jsPDF, student: Student): void {
    doc.setFontSize(20);
    doc.setFont("Helvetica", "bold");
    doc.text('UNIVERZITET SINGIDUNUM', 105, 15, { align: 'center' });

    doc.setFontSize(16);
    doc.text('UGOVOR O STUDIRANJU', 105, 35, { align: 'center' });

    doc.setFontSize(12);
    doc.setFont("Helvetica", "normal");
    doc.text('Ugovor zakljucen izmedju:', 10, 60);
    doc.text(`Fakulteta: [Fakultet],`, 10, 75);
    // doc.text(`Student: ${student.ime} ${student.prezime},`, 10, 90);
    // doc.text(`sa JMBG: ${student.jmbg},`, 10, 105);
    doc.text(`i prebivalistem u: ${student.mestoPrebivalista?.naziv},`, 10, 120);
    doc.text('Predmet ugovora:', 10, 140);
    doc.text('1. Fakultet se obavezuje da studentu omoguci uslove za izvodjenje nastave u skladu sa studijskim programom.', 10, 155);
    doc.text('2. Student se obavezuje da postuje pravila studiranja i ispunjava sve obaveze.', 10, 175);
    doc.text('Rok studiranja:', 10, 195);
    doc.text('Student se upisuje u skolsku godinu [Godina], a rok zavrsetka je [Rok].', 10, 210);

    doc.text(`Datum: ${this.getCurrentDate()}`, 10, 260);
    doc.text('Potpis dekana: ____________________', 10, 275);
    doc.text('Potpis studenta: ____________________', 10, 290);

    doc.setDrawColor(0);
    doc.setFillColor(200, 220, 255);
    // doc.rect(10, 10, 190, 20, 'F'); 
  }

  generateSporazum(doc: jsPDF, student: Student): void {
    doc.setFontSize(20);
    doc.setFont("Helvetica", "bold");
    doc.text('UNIVERZITET SINGIDUNUM', 105, 15, { align: 'center' });

    doc.setFontSize(16);
    doc.text('SPORAZUM O MENTORSTVU', 105, 35, { align: 'center' });

    doc.setFontSize(12);
    doc.setFont("Helvetica", "normal");
    doc.text(`Sporazum izmedju mentora i studenta ${student.korisnik.ime} ${student.korisnik.prezime},`, 10, 60);
    // doc.text(`sa JMBG: ${student.jmbg},`, 10, 75);
    doc.text(`i prebivalistem u: ${student.mestoPrebivalista?.naziv}.`, 10, 90);
    doc.text('Mentorske obaveze:', 10, 110);
    doc.text('1. Mentor pomaze u pripremi diplomskog rada.', 10, 125);
    doc.text('2. Mentor prati napredak studenta.', 10, 140);
    doc.text('Obaveze studenta:', 10, 160);
    doc.text('1. Student redovno izvestava mentora o napretku.', 10, 175);
    doc.text('2. Student postuje savete mentora.', 10, 190);

    doc.text(`Datum: ${this.getCurrentDate()}`, 10, 260);
    doc.text('Potpis mentora: ____________________', 10, 275);
    doc.text('Potpis studenta: ____________________', 10, 290);

    doc.setDrawColor(0);
    doc.setFillColor(200, 220, 255);
    // doc.rect(10, 10, 190, 20, 'F');
  }

  getCurrentDate(): string {
    const today = new Date();
    const day = String(today.getDate()).padStart(2, '0');
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const year = today.getFullYear();
    return `${day}.${month}.${year}`;
  }
}
