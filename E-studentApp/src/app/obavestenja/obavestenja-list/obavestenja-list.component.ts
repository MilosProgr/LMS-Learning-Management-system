import { DatePipe, NgFor, NgIf, SlicePipe } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';
import { Obavestenje } from '../../../models/obavestenja/obavestenjeModel';
import { ObavestenjeService } from '../../../Services/obavestenja/obavestenje.service';
import { MatDialog } from '@angular/material/dialog';
import { ObavestenjeEditComponent } from '../obavestenja-edit/obavestenja-edit.component';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-obavestenja-list',
  standalone: true,
  imports: [NgFor, NgIf, NgxPaginationModule, DatePipe],
  templateUrl: './obavestenja-list.component.html',
  styleUrl: './obavestenja-list.component.css'
})
export class ObavestenjaListComponent implements OnInit {
  obavestenja: Obavestenje[] = [];
  errorMessage = '';
  currentPage = 1;
  itemsPerPage = 10;

  constructor(
    private service: ObavestenjeService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.service.getAll().subscribe({
      next: (res) => {
        this.obavestenja = res
        // console.log(this.obavestenja);
        // console.log(this.obavestenja[1].datum)
        // console.log(this.obavestenja[1].vreme)
                      
      },
      error: (err) => console.error(err)
    });
  }

  openAddDialog() {
    const dialogRef = this.dialog.open(ObavestenjeEditComponent, {
      width: '600px',
      data: { mode: 'add' }
    });
    dialogRef.afterClosed().subscribe(result => { if (result) this.load(); });
  }

  openEditDialog(obavestenje: Obavestenje) {
    const dialogRef = this.dialog.open(ObavestenjeEditComponent, {
      width: '600px',
      data: { mode: 'edit', obavestenje }
    });
    dialogRef.afterClosed().subscribe(result => { if (result) this.load(); });
  }

  delete(id: number) {
    this.service.delete(id).subscribe({
      next: () => this.load(),
      error: (err) => {
        console.error(err);
        this.errorMessage = 'Nije moguće obrisati obaveštenje.';
      }
    });
  }
}
