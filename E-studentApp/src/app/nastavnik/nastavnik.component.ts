import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Nastavnik } from '../../models/nastavnik.model';
import { NastavnikService } from '../../Services/nastavnik.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nastavnik',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './nastavnik.component.html',
  styleUrls: ['./nastavnik.component.css']
})
export class NastavnikComponent implements OnInit {
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

  editProfile(): void {
    this.router.navigate(['/nastavnik-uredi-profil']);
  }
}
