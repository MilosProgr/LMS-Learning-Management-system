import { DatePipe, NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { NgxPaginationModule } from 'ngx-pagination';
import { Semestar } from '../../../models/semestarModel';
import { SemestarService } from '../../../Services/semestar.service';
import { MatDialog } from '@angular/material/dialog';
import { SemestarEditComponent } from './semestar-edit/semestar-edit.component';
import { timeout } from 'rxjs';

@Component({
  selector: 'app-semestar-list',
  standalone: true,
  imports: [NgFor, NgIf, NgxPaginationModule, DatePipe],
  templateUrl: './semestar-list.component.html',
  styleUrl: './semestar-list.component.css'
})
export class SemestarListComponent {

  semestri: Semestar[] = [];
  errorMessage = '';
  currentPage = 1;
  itemsPerPage = 10;

  constructor(
    private semestarService: SemestarService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.loadSemestri();
  }

  loadSemestri(): void {
    this.semestarService.getAll().subscribe({
      next: (res) => {
        this.semestri = res;
        console.log('Semestri:', this.semestri);
      },
      error: (err) => {
        console.error(err);
        this.errorMessage = 'Greška pri učitavanju semestara.';
        setTimeout(() => {
          this.errorMessage = ""
        }, 2000);
      }
    });
  }

  openAddDialog(): void {
    const dialogRef = this.dialog.open(SemestarEditComponent, {
      width: '400px',
      data: { mode: 'add' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadSemestri();
    });
  }

  openEditDialog(semestar: Semestar): void {
    const dialogRef = this.dialog.open(SemestarEditComponent, {
      width: '400px',
      data: { mode: 'edit', semestar }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadSemestri();
    });
  }

  deleteSemestar(id: number): void {
    this.semestarService.delete(id).subscribe({
      next: () => this.loadSemestri(),
      error: (err) => {
        console.error(err);
        this.errorMessage = 'Nije moguće obrisati semestar.';
      }
    });
  }
}
