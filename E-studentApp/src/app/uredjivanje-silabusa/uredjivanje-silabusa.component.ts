import { Component, OnInit } from '@angular/core';
import { Ishod } from '../../models/ishodModel';
import { IshodService } from '../../Services/ishodi.service';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-uredjivanje-silabusa',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './uredjivanje-silabusa.component.html',
  styleUrl: './uredjivanje-silabusa.component.css'
})

export class UredjivanjeSilabusaComponent implements OnInit {
  ishodi: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<any[]>('http://localhost:8080/api/ishodi').subscribe(data => {
      this.ishodi = data.map(ishod => ({ ...ishod, editMode: false }));
    });
  }

  saveOpis(ishod: any): void {
    this.http.put(`http://localhost:8080/api/ishodi/${ishod.id}`, ishod)
      .subscribe(() => {
        ishod.editMode = false;
      });
  }
}
