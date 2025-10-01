import { Component, OnInit } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { Fakultet } from '../../../models/fakultetModel';
import { Univerzitet } from '../../../models/univerzitetModel';
import { Nastavnik } from '../../../models/nastavnik.model';
import { Adresa } from '../../../models/adresaModel';
import { Mesto } from '../../../models/mesto';
import { Drzava } from '../../../models/drzava';

import { FakultetService } from '../../../Services/fakultet.service';
import { UniverzitetService } from '../../../Services/univerzitet.service';
import { NastavnikService } from '../../../Services/nastavnik.service';
import { AdresaService } from '../../../Services/adresa.service';
import { MestoService } from '../../../Services/mesto.service';
import { DrzavaService } from '../../../Services/drzava.service';

import { Router, RouterLink } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { FakultetEditComponent } from '../fakultet-edit/fakultet-edit.component';
import { DatePipe } from '@angular/common';
import { NgxPaginationModule } from "ngx-pagination";

@Component({
  selector: 'app-fakultet-list',
  standalone: true,
  templateUrl: './fakultet-list.component.html',
  styleUrl: './fakultet-list.component.css',
  imports: [NgFor, NgIf, RouterLink, DatePipe, NgxPaginationModule]
})
export class FakultetListComponent implements OnInit {

  currentPage: number = 1;
  itemsPerPage: number = 10;
  trackById = (_: number, f: Fakultet) => f?.id;

  fakulteti: Fakultet[] = [];
  univerziteti: Univerzitet[] = [];
  mesta: Mesto[] = [];
  drzave: Drzava[] = [];
  adrese: Adresa[] = [];
  nastavniciBezFakulteta: Nastavnik[] = [];
  errorMessage: string = '';

  constructor(
    private fakultetService: FakultetService,
    private univerzitetService: UniverzitetService,
    private mestoService: MestoService,
    private nastavnikService: NastavnikService,
    private adresaService: AdresaService,
    private drzavaService: DrzavaService,
    private router: Router,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.loadFakulteti();
    this.loadUniverziteti();
    this.loadMesta();
    this.loadAdrese();
    this.loadNastavniciBezFakulteta();
    this.loadDrzave();

  }

  openAddDialog() {
    const dialogRef = this.dialog.open(FakultetEditComponent, {
      width: '600px',
      data: {
        mode: 'add',
        univerziteti: this.univerziteti,
        mesta: this.mesta,
        adrese: this.adrese,
        drzave: this.drzave,
        nastavniciBezFakulteta: this.nastavniciBezFakulteta
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadFakulteti();
        this.loadNastavniciBezFakulteta();
      }
    });
  }

  openEditDialog(fakultet: Fakultet) {
    const dialogRef = this.dialog.open(FakultetEditComponent, {
      width: '600px',
      data: {
        fakultet,
        mode: 'edit',
        univerziteti: this.univerziteti,
        mesta: this.mesta,
        adrese: this.adrese,
        drzave: this.drzave,
        nastavniciBezFakulteta: this.nastavniciBezFakulteta
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      this.loadFakulteti();
      this.loadNastavniciBezFakulteta();
    });
  }

  loadFakulteti(): void {
    this.fakultetService.getAll().subscribe(fakulteti => {
      this.fakulteti = fakulteti;
    });
  }

  loadUniverziteti(): void {
    this.univerzitetService.getAll().subscribe(univerziteti => {
      this.univerziteti = univerziteti;
    });
  }

  loadMesta(): void {
    this.mestoService.getAll().subscribe(mesta => {
      this.mesta = mesta;
    });
  }

  loadAdrese(): void {
    this.adresaService.getAll().subscribe(adrese => {
      this.adrese = adrese;
    });
  }

  loadNastavniciBezFakulteta(): void {
    this.nastavnikService.getNastavniciBezFakulteta().subscribe(nastavnici => {
      this.nastavniciBezFakulteta = nastavnici;
      console.log(this.nastavniciBezFakulteta);

    });
  }

  loadDrzave(): void {
    this.drzavaService.getAll().subscribe(data => {
      this.drzave = data;
    });
  }

  izbrisiFakultet(id: number): void {
    this.fakultetService.delete(id).subscribe({
      next: (res) => {
        this.loadFakulteti();
        console.log("Fakultet uspesno odbrisan.");

      },
      error: (err) => {
        console.error('Greška prilikom brisanja fakulteta:', err);

        if (err.status === 409) {
          this.errorMessage = err.error?.message || 'Već postoji zapis sa tim podacima.';
        }else{
          this.errorMessage = "Nije moguće obrisati entitet!";
        }

      }
    });
  }
}
