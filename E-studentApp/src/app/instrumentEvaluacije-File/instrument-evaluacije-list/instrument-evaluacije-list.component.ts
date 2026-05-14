import { Component, OnInit } from '@angular/core';
import { FileInstrumentEvaluacije } from '../../../models/FileInstrumentEvaluacijeModel';
import { InstrumentEvaluacijeEditComponent } from '../instrument-evaluacije-edit/instrument-evaluacije-edit.component';
import { InstrumentEvaluacijeService } from '../../../Services/fileInstrumentEvaluacije.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-instrument-evaluacije-list',
  standalone: true,
  imports: [CommonModule, NgxPaginationModule, MatDialogModule],
  templateUrl: './instrument-evaluacije-list.component.html',
  styleUrl: './instrument-evaluacije-list.component.css'
})
export class InstrumentEvaluacijeListComponent implements OnInit {
  instrumenti: FileInstrumentEvaluacije[] = [];
  errorMessage = '';
  currentPage = 1;
  itemsPerPage = 10;

  constructor(
    private instrumentEvaluacijeService: InstrumentEvaluacijeService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.loadInstrumenti();
  }

  loadInstrumenti(): void {
    this.instrumentEvaluacijeService.getAll().subscribe({
      next: (res: FileInstrumentEvaluacije[]) => { this.instrumenti = res; },
      error: (err: unknown) => console.error(err)
    });
  }

  openAddDialog(): void {
    const dialogRef = this.dialog.open(InstrumentEvaluacijeEditComponent, {
      width: '420px',
      data: { mode: 'add' }
    });

    dialogRef.afterClosed().subscribe((res: FileInstrumentEvaluacije) => { if (res) this.loadInstrumenti(); });
  }

  openEditDialog(instrument: FileInstrumentEvaluacije): void {
    const dialogRef = this.dialog.open(InstrumentEvaluacijeEditComponent, {
      width: '420px',
      data: { mode: 'edit', instrument }
    });

    dialogRef.afterClosed().subscribe((res: FileInstrumentEvaluacije) => { if (res) this.loadInstrumenti(); });
  }

  deleteInstrument(id: number): void {
    this.instrumentEvaluacijeService.delete(id).subscribe({
      next: () => this.loadInstrumenti(),
      error: (err: HttpErrorResponse) => {
        console.error(err);
        this.errorMessage = 'Nije moguće obrisati instrument evaluacije.';
      }
    });
  }
}
