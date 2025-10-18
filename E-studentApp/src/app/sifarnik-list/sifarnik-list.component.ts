import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NgxPaginationModule } from 'ngx-pagination';
import { MatDialog } from '@angular/material/dialog';
import { SifraService } from '../../Services/sifra.service';
import { Sifra } from '../../models/sifra';
import { SifarnikEditComponent } from './sifarnik-edit/sifarnik-edit.component';

@Component({
  selector: 'app-sifarnik-list',
  standalone: true,
  imports: [NgFor, NgIf, NgxPaginationModule],
  templateUrl: './sifarnik-list.component.html',
  styleUrls: ['./sifarnik-list.component.css']
})
export class SifarnikListComponent implements OnInit {
  sifre: Sifra[] = [];
  errorMessage = '';
  currentPage = 1;
  itemsPerPage = 10;

  constructor(
    private sifraService: SifraService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.loadSifre();
  }

  loadSifre() {
    this.sifraService.getAll().subscribe({
      next: res => this.sifre = res,
      error: err => console.error(err)
    });
  }

  openAddDialog() {
    const dialogRef = this.dialog.open(SifarnikEditComponent, {
      width: '400px',
      data: { mode: 'add' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadSifre();
    });
  }

  openEditDialog(sifra: Sifra) {
    const dialogRef = this.dialog.open(SifarnikEditComponent, {
      width: '400px',
      data: { mode: 'edit', sifra }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadSifre();
    });
  }

  deleteSifra(id: number) {
    this.sifraService.delete(id).subscribe({
      next: () => this.loadSifre(),
      error: err => {
        console.error(err);
        this.errorMessage = "Nije moguće obrisati šifru.";
      }
    });
  }
}
