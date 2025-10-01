import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { Student } from '../../models/student.model';
import { StudentService } from '../../Services/student/student.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-student-edit',
  standalone: true,
  templateUrl: './student-edit.component.html',
  imports: [FormsModule, CommonModule, RouterModule],
  styleUrls: ['./student-edit.component.css']
})
export class StudentEditComponent implements OnInit {
  student: Student | null = null;

  constructor(private studentService: StudentService, private router: Router) {}

  ngOnInit(): void {
    this.studentService.getStudent().subscribe(
      (data: Student[]) => {
        if (data.length > 0) {
          this.student = data[0];
        }
      },
      (error) => {
        console.error('Error fetching student data', error);
      }
    );
  }

  onSubmit(): void {
    if (this.student) {
      this.studentService.updateStudent(this.student).subscribe(
        () => {
          alert('Profile updated successfully');
          this.router.navigate(['/student-profile']);
        },
        (error) => {
          console.error('Error updating student data', error);
        }
      );
    }
  }
}
