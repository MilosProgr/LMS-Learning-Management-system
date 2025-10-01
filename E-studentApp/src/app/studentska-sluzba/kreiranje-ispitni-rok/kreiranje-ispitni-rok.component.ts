import { Component, OnInit } from '@angular/core';
import { IspitniRok } from '../../../models/ispitniRok/ispitniRokModel';
import { IspitniRokService } from '../../../Services/ispitniRok/ispitni-rok.service';
import { DatePipe, NgFor, NgIf } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';
import { MatDialog } from '@angular/material/dialog';
import { IspitniRokEditComponent } from './ispitni-rok-edit/ispitni-rok-edit.component';

@Component({
  selector: 'app-kreiranje-ispitni-rok',
  standalone: true,
  imports: [NgFor, NgIf, NgxPaginationModule, DatePipe],
  templateUrl: './kreiranje-ispitni-rok.component.html',
  styleUrl: './kreiranje-ispitni-rok.component.css'
})
export class KreiranjeIspitniRokComponent implements OnInit {

    currentPage = 1;
  itemsPerPage = 10;

  rokovi: IspitniRok[] = [];
  errorMessage = '';

  constructor(
    private rokService: IspitniRokService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadRokovi();
  }

  openAddDialog() {
    const dialogRef = this.dialog.open(IspitniRokEditComponent, {
      width: '600px',
      data: { mode: 'add' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadRokovi();
      }
    });
  }

  openEditDialog(rok: IspitniRok) {
    const dialogRef = this.dialog.open(IspitniRokEditComponent, {
      width: '600px',
      data: { mode: 'edit', rok }
    });

    dialogRef.afterClosed().subscribe(() => {
      this.loadRokovi();
    });
  }

  loadRokovi(): void {
    this.rokService.getAll().subscribe({
      next: (res) => {
        this.rokovi = res ?? [];
        const total = this.rokovi.length;
        if (total === 0) {
          this.currentPage = 1;
        } else {
          const maxPage = Math.max(1, Math.ceil(total / this.itemsPerPage));
          if (this.currentPage > maxPage) this.currentPage = maxPage;
        }
      },
      error: (err) => console.error(err)
    });
  }

  izbrisiRok(id: number): void {
    if (id == null) return;

    const pre = this.rokovi;
    this.rokovi = this.rokovi.filter(r => r.id !== id);

    if (this.rokovi.length === 0) this.currentPage = 1;

    this.rokService.delete(id).subscribe({
      next: () => this.loadRokovi(),
      error: (err) => {
        console.error('Greška pri brisanju roka:', err);
      }
    });
  }

  trackById(index: number, item: IspitniRok) {
    return item.id;
  }

}
