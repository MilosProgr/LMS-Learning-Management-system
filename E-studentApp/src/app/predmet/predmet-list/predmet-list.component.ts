import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Predmet } from '../../../models/predmetModel';
import { PredmetService } from '../../../Services/predmet.service';
import { GenericReusableTableComponent } from '../../generics/generic-reusable-table/generic-reusable-table.component';
import { StudijskiProgram } from '../../../models/studijskiprogramModel';
import { StudijskiProgramService } from '../../../Services/studijski-program.service';
import { MatDialog } from '@angular/material/dialog';
import { PredmetEditComponent } from '../predmet-edit/predmet-edit.component';
import { NgxPaginationModule } from "ngx-pagination";
import { Sifra } from '../../../models/sifra';
import { GodinaStudija } from '../../../models/godinaStudija/godinaStudija';
import { SifraService } from '../../../Services/sifra.service';
import { GodinaStudijaService } from '../../../Services/godinaStudija/godinaStudijaModel.service';

@Component({
  selector: 'app-predmet-list',
  standalone: true,
  imports: [NgFor, NgIf, NgxPaginationModule],
  templateUrl: './predmet-list.component.html',
  styleUrl: './predmet-list.component.css'
})
export class PredmetListComponent implements OnInit {
  currentPage = 1;
  itemsPerPage = 10;

  predmeti: Predmet[] = [];
  programi: StudijskiProgram[] = [];
  sifre: Sifra[] = [];
  godine: GodinaStudija[] = [];

  errorMessage = '';

  constructor(
    private predmetService: PredmetService,
    private programService: StudijskiProgramService,
    private sifraService: SifraService,
    private godinaService: GodinaStudijaService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.loadPredmeti();
    this.loadProgrami();
    this.loadSifre();
    this.loadGodine(); 
  }

  openAddDialog() {
    const dialogRef = this.dialog.open(PredmetEditComponent, {
      width: '600px',
      data: {
        mode: 'add',
        programi: this.programi,
        sifre: this.sifre,
        godine: this.godine

      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadPredmeti();
      }
    });
  }

  openEditDialog(predmet: Predmet) {
    const dialogRef = this.dialog.open(PredmetEditComponent, {
      width: '600px',
      data: {
        mode: 'edit',
        programi: this.programi,
        predmet,
        sifre: this.sifre,
        godine: this.godine
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      this.loadPredmeti();
    });
  }

  loadProgrami(): void {
    this.programService.getAll().subscribe({
      next: (res) => (this.programi = res),
      error: (err) => console.error(err)
    });
  }

    loadSifre(): void {
    this.sifraService.getAll().subscribe({
      next: (res) => (this.sifre = res ?? []),
      error: (err) => console.error(err)
    });
  }

  loadGodine(): void {
    this.godinaService.getAll().subscribe({
      next: (res) =>{
        this.godine = res ?? []
      },
      error: (err) => console.error(err)
    });
  }


trackById(index: number, item: Predmet) { return item.id; }

loadPredmeti(): void {
  this.predmetService.getAll().subscribe({
    next: (res) => {
      this.predmeti = res ?? []; // nikad undefined
      console.log(res);
      
      const total = this.predmeti.length;
      if (total === 0) {
        this.currentPage = 1; // reset kad nema podataka
      } else {
        const maxPage = Math.max(1, Math.ceil(total / this.itemsPerPage));
        if (this.currentPage > maxPage) this.currentPage = maxPage;
      }
    },
    error: (err) => console.error(err)
  });
}

izbrisiPredmet(id: number): void {
  if (id == null) return;

  // optimistički UX (nije obavezno, ali pomaže)
  const pre = this.predmeti;
  this.predmeti = this.predmeti.filter(p => p.id !== id);

  if (this.predmeti.length === 0) this.currentPage = 1;

  this.predmetService.delete(id).subscribe({
    next: () => this.loadPredmeti(),      // poravnaj sa serverom
    error: (err) => {
      console.error('Greška pri brisanju predmeta:', err);
      this.errorMessage = 'Nije moguće obrisati entitet! Proverite povezane entitete.';
      this.predmeti = pre;                // rollback ako padne
    }
  });
}
}
