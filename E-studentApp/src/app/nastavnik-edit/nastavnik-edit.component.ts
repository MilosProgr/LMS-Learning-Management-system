import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { NgForm } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { NastavnikService } from '../../Services/nastavnik.service';
import { Nastavnik } from '../../models/nastavnik.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-nastavnik-edit',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule],
  templateUrl: './nastavnik-edit.component.html',
  styleUrls: ['./nastavnik-edit.component.css']
})
export class NastavnikEditComponent implements OnInit {
  nastavnik: Nastavnik | null = null;

  constructor(private nastavnikService: NastavnikService, private router: Router) {}

  ngOnInit(): void {
    this.nastavnikService.getAll().subscribe(
      (data: Nastavnik[]) => {
        if (data.length > 0) {
          this.nastavnik = data[0];
        }
      },
      (error) => {
        console.error('Error fetching nastavnik data', error);
      }
    );
  }

  onSubmit(form: NgForm): void {
    if (form.valid && this.nastavnik) {
      //i ovde proveri da li sam dobar id prosledio za svaki slicaj
      this.nastavnikService.update(this.nastavnik.id!, this.nastavnik).subscribe(
        () => {
          this.router.navigate(['/nastavnik-profile']);
        },
        (error) => {
          console.error('Error updating nastavnik data', error);
        }
      );
    }
  }
}
