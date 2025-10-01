import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Univerzitet } from '../../../models/univerzitetModel';
import { UniverzitetService } from '../../../Services/univerzitet.service';
import { FormField } from '../../generics/generic-form/form-model';
import { Validators } from '@angular/forms';
import { AdresaService } from '../../../Services/adresa.service';
import { Router, RouterLink } from '@angular/router';
import { Nastavnik } from '../../../models/nastavnik.model';
import { NastavnikService } from '../../../Services/nastavnik.service';
import { Mesto } from '../../../models/mesto';
import { MestoService } from '../../../Services/mesto.service';
import { NgxPaginationModule } from 'ngx-pagination';
import { Drzava } from '../../../models/drzava';
import { DrzavaService } from '../../../Services/drzava.service';
import { MatDialog, matDialogAnimations } from '@angular/material/dialog';
import { UniverzitetEditComponent } from '../univerzitet-edit/univerzitet-edit.component';
import { Adresa } from '../../../models/adresaModel';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-univerzitet-list',
  standalone: true,
  imports: [NgFor, RouterLink, NgIf, NgxPaginationModule, DatePipe],
  templateUrl: './univerzitet-list.component.html',
  styleUrl: './univerzitet-list.component.css'
})
export class UniverzitetListComponent implements OnInit {

  currentPage: number = 1;  // trenutna stranica
  itemsPerPage: number = 10;  // broj stavki po stranici

  univerziteti: Univerzitet[] = [];
  nastavnicibezUniverziteta: any[] = [];
  mesta: Mesto[] = [];
  drzave: Drzava[] = [];
  adrese: Adresa[] = [];
  errorMessage: string = '';

  constructor(private univerzitetService: UniverzitetService, private adresaService: AdresaService,
    private mestoService: MestoService, private nastavnikService: NastavnikService,
    private router: Router, private drzavaService: DrzavaService,
    private dialog: MatDialog) { }

  ngOnInit(): void {

    this.loadDrzave();
    this.loadUniverziteti();
    this.loadSlobodniNastavnikUniverzitet();
    this.loadMesta();
    this.loadAdrese();
  }

  openAddDialog() {
    const dialogRef = this.dialog.open(UniverzitetEditComponent, {
      width: '600px',
      data: {
        mode: 'add', 
        mesta: this.mesta,
        adrese: this.adrese,
        drzave: this.drzave,
        nastavniciBezUniverziteta: this.nastavnicibezUniverziteta
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log("Dialog za dodavanje zatvoren!");
        
        this.loadUniverziteti();
        
      }
    });
  }

  openEditDialog(univerzitet: Univerzitet) {
    const dialogRef = this.dialog.open(UniverzitetEditComponent, {
      width: '600px',
      data: {
        univerzitet,
        mode: 'edit',
        mesta: this.mesta,
        adrese: this.adrese,
        drzave: this.drzave,
        nastavniciBezUniverziteta: this.nastavnicibezUniverziteta
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      this.loadUniverziteti();
      console.log("Dialog za edit zatvoren!");
      
    });
  }


  loadUniverziteti(): void {
    this.univerzitetService.getAll().subscribe(univerziteti => {
      this.univerziteti = univerziteti;
      console.log("Univerziteti:", this.univerziteti);
    });
  }

  loadMesta(): void {
    this.mestoService.getAll().subscribe(mesta => {
      this.mesta = mesta;
      // console.log("Mesta:", this.mesta);
    });
  }

  loadDrzave(): void {
    this.drzavaService.getAll().subscribe(data => {
      this.drzave = data;
    })
  }

  loadAdrese(): void {
    this.adresaService.getAll().subscribe(adrese => {
      this.adrese = adrese;
      // console.log("Adrese:", this.adrese);
    });
  }

  loadSlobodniNastavnikUniverzitet() {
    this.nastavnikService.getNastavniciBezUniverziteta().subscribe(nastavnici => {
      this.nastavnicibezUniverziteta = nastavnici;

      // console.log("Slobodni znastavnici bez univerziteta", this.nastavnicibezUniverziteta)
    })
  }

  izbrisiUniverzitet(id: number): void {
    console.log('Pokušaj brisanja univerziteta sa ID:', id);

    this.univerzitetService.delete(id).subscribe(
      () => {
        console.log(`Univerzitet sa ID ${id} je uspešno obrisan.`);
        this.loadUniverziteti(); // Ponovo učitajte listu univerziteta
      },
      (error) => {
        console.error('Greška prilikom brisanja univerziteta:', error);
        this.errorMessage = "Nije moguće obrisati entitet! Proverite povezane entitete."

      }
    );
  }
}
