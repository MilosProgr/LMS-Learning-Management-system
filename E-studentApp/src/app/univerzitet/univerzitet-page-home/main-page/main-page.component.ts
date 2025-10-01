import { Component, OnInit } from '@angular/core';
import { NavComponent } from '../nav/nav.component';
import { FooterComponent } from '../footer/footer.component';
import { NgFor } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Univerzitet } from '../../../../models/univerzitetModel';
import { UniverzitetService } from '../../../../Services/univerzitet.service';
import { NgxPaginationModule } from 'ngx-pagination';

@Component({
  selector: 'app-main-page',
  standalone: true,
  imports: [FooterComponent, NgFor, RouterLink, NgxPaginationModule],
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.css'
})
export class MainPageComponent implements OnInit{
  univerziteti: Univerzitet[] = [];

  currentPage: number = 1;
  itemsPerPage: number = 10;

  constructor(private univerzitetService: UniverzitetService) { }
  ngOnInit(): void {
    this.loadFakulteti();
  }

  loadFakulteti(): void {
    this.univerzitetService.getAll().subscribe(univerziteti => {
      this.univerziteti = univerziteti;
      console.log(univerziteti);
    });
  }
}
