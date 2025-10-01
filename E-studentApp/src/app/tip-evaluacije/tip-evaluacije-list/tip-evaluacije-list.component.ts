import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NgxPaginationModule } from 'ngx-pagination';
import { MatDialog } from '@angular/material/dialog';
import { TipEvaluacijeService } from '../../../Services/tip-evaluacije.service';
import { TipEvaluacije } from '../../../models/tipEvaluacije';
import { TipEvaluacijeEditComponent } from '../tip-evaluacije-edit/tip-evaluacije-edit.component';

@Component({
  selector: 'app-tip-evaluacije-list',
  standalone: true,
  imports: [NgFor, NgIf, NgxPaginationModule],
  templateUrl: './tip-evaluacije-list.component.html',
  styleUrls: ['./tip-evaluacije-list.component.css']
})
export class TipEvaluacijeListComponent implements OnInit {
  tipovi: TipEvaluacije[] = [];
  errorMessage = '';
  currentPage = 1;
  itemsPerPage = 10;

  constructor(
    private tipEvaluacijeService: TipEvaluacijeService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadTipovi();
  }

  loadTipovi() {
    this.tipEvaluacijeService.getAll().subscribe({
      next: res => this.tipovi = res,
      error: err => console.error(err)
    });
  }

  openAddDialog() {
    const dialogRef = this.dialog.open(TipEvaluacijeEditComponent, {
      width: '400px',
      data: { mode: 'add' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadTipovi();
    });
  }

  openEditDialog(tip: TipEvaluacije) {
    const dialogRef = this.dialog.open(TipEvaluacijeEditComponent, {
      width: '400px',
      data: { mode: 'edit', tip }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) this.loadTipovi();
    });
  }

  deleteTip(id: number) {
    this.tipEvaluacijeService.delete(id).subscribe({
      next: () => this.loadTipovi(),
      error: err => {
        console.error(err);
        this.errorMessage = "Nije moguće obrisati tip evaluacije.";
      }
    });
  }
}
