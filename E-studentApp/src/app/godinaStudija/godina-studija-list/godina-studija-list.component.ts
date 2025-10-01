import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NgxPaginationModule } from 'ngx-pagination';
import { GodinaStudija } from '../../../models/godinaStudija/godinaStudija';
import { GodinaStudijaService } from '../../../Services/godinaStudija/godinaStudijaModel.service';
import { MatDialog } from '@angular/material/dialog';
import { GodinaStudijaEditComponent } from '../godina-studija-edit/godina-studija-edit.component';

@Component({
  selector: 'app-godina-studija-list',
  standalone: true,
  imports: [NgFor, NgIf, NgxPaginationModule],
  templateUrl: './godina-studija-list.component.html',
  styleUrl: './godina-studija-list.component.css'
})
export class GodinaStudijaListComponent implements OnInit {
  godine: GodinaStudija[] = [];
  errorMessage = '';
  currentPage = 1;
  itemsPerPage = 10;

  constructor(
    private godinaStudijaService: GodinaStudijaService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadGodine();
  }

  loadGodine() {
    this.godinaStudijaService.getAll().subscribe({
      next: (res) => {
        this.godine = res
        console.log(this.godine);
        
      },
      error: (err) => console.error(err)
    });
  }

  openAddDialog() {
    const dialogRef = this.dialog.open(GodinaStudijaEditComponent, {
      width: '400px',
      data: { mode: 'add' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadGodine();
    });
  }

  openEditDialog(godina: GodinaStudija) {
    const dialogRef = this.dialog.open(GodinaStudijaEditComponent, {
      width: '400px',
      data: { mode: 'edit', godina }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadGodine();
    });
  }

  deleteGodina(id: number) {
    this.godinaStudijaService.delete(id).subscribe({
      next: () => this.loadGodine(),
      error: (err) => {
        console.error(err);
        this.errorMessage = "Nije moguće obrisati godinu studija.";
      }
    });
  }
}
