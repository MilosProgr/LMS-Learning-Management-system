import { Component } from '@angular/core';
import { Fakultet } from '../../../models/fakultetModel';
import { FakultetService } from '../../../Services/fakultet.service';
import { NgFor } from '@angular/common';
import { RouterLink } from '@angular/router';
import { NgxPaginationModule } from 'ngx-pagination';

@Component({
  selector: 'app-fakultet-prikaz',
  standalone: true,
  imports: [NgFor, RouterLink, NgxPaginationModule],
  templateUrl: './fakultet-prikaz.component.html',
  styleUrl: './fakultet-prikaz.component.css'
})
export class FakultetPrikazComponent {

  fakulteti: Fakultet[] = [];

  currentPage: number = 1;
  itemsPerPage: number = 10;

  constructor(private fakultetService: FakultetService) { }
  
  ngOnInit(): void {
    this.loadFakulteti();
  }

  loadFakulteti(): void {
    this.fakultetService.getAll().subscribe(data => {
      this.fakulteti = data;
      console.log(data);
    });
  }
}
