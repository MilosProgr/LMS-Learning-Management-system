import { CommonModule, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Adresa } from '../../models/adresaModel';
import { AdresaService } from '../../Services/adresa.service';
import { ReactiveFormsModule, Validators } from '@angular/forms';
import { GenericFormComponent } from '../generics/generic-form/generic-form.component';
import { FormField } from '../generics/generic-form/form-model';
import { Router, RouterLink } from '@angular/router';
import { DrzavaService } from '../../Services/drzava.service';
import { MestoService } from '../../Services/mesto.service';
import { Drzava } from '../../models/drzava';
import { Mesto } from '../../models/mesto';
import { MestoListComponent } from './mesto-list/mesto-list.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { DrzavaListComponent } from './drzava-list/drzava-list.component';

@Component({
  selector: 'app-adresa-list',
  standalone: true,
  imports: [NgFor, NgIf,RouterLink, ReactiveFormsModule, GenericFormComponent, NgxPaginationModule, CommonModule, DrzavaListComponent, MestoListComponent],
  templateUrl: './adresa-list.component.html',
  styleUrl: './adresa-list.component.css'
})
export class AdresaListComponent implements OnInit {
  currentPage: number = 1;  // trenutna stranica
  itemsPerPage: number = 10;  // broj stavki po stranici
    errorMessage = ''
  adrese: Adresa[] = [];

  // kljuceviDrzava = [
  //   { imeKolone: 'Id', kljuc: 'id' },
  //   { imeKolone: 'Naziv', kljuc: 'naziv' },
  //   // { imeKolone: 'Mesta', kljuc: 'mesta' },
  // ];

  // kljuceviAdresa = [
  //   { imeKolone: 'Id', kljuc: 'id' },
  //   { imeKolone: 'Ulica', kljuc: 'ulica' },
  //   { imeKolone: 'Broj', kljuc: 'broj' },
  //   // { imeKolone: 'Mesto', kljuc: 'mestoNaziv' },
  // ];

  drzave: Drzava[] = [];
  mesta: Mesto[] = [];

  constructor(private adresaService: AdresaService, private drzavaService: DrzavaService,
    private mestoService: MestoService, private router: Router) {

  }

  formFieldsAdresa: FormField[] = [
    {
      type: 'text',
      label: 'Ulica: ',
      name: 'ulica',
      validations: [Validators.required, Validators.pattern('^[A-Z].*')]
    },
    {
      type: 'text',
      label: 'Broj: ',
      name: 'broj',
      validations: [Validators.required]
    },
    {
      type: 'select',
      label: 'Mesto: ',
      name: 'mesto',
      options: [],
      validations: [Validators.required]
    }
  ];

  //POLJE ZA MESTO
  formFieldsMesto: FormField[] = [
    {
      type: 'text',
      label: 'Naziv: ',
      name: 'naziv',
      validations: [Validators.required, Validators.pattern('^[A-Z].*')]
    },
    {
      type: 'select',
      label: 'Drzava: ',
      name: 'drzava',
      options: [],
      validations: [Validators.required]
    }
  ];

  formFieldsDrzava: FormField[] = [
    {
      type: 'text',
      label: 'Naziv: ',
      name: 'naziv',
      validations: [Validators.required, Validators.pattern('^[A-Z].*')]
    }
  ];

  ngOnInit(): void {
    this.loadDrzave();
    this.loadMesta();
    this.loadAdrese();
  }

  popuniOpcije(): void {
    const drzavaField = this.formFieldsMesto.find(field => field.name === 'drzava');
    if (drzavaField) {
      drzavaField.options = this.drzave.map(drzava => ({ key: drzava.id !== undefined ? drzava.id.toString() : "Nema ID", value: drzava.naziv }));
    }

    const mestoField = this.formFieldsAdresa.find(field => field.name === 'mesto');
    if (mestoField) {
      mestoField.options = this.mesta.map(mesto => ({ key: mesto.id?.toString() ?? '', value: mesto.naziv }));
    }

  }

  loadAdrese(): void {
    this.adresaService.getAll().subscribe(adrese => {
      this.adrese = adrese;
      console.log('Adrese:', this.adrese);
    });
  }


  loadMesta(): void {
    this.mestoService.getAll().subscribe(mesta => {
      this.mesta = mesta;
      // if (mesta.length > 0) {
      //   this.kljuceviMesto = Object.keys(mesta[0]);
      // }
      console.log("Mesta: ", mesta)
      this.loadAdrese();  // Učitaj adrese nakon što mesta budu učitana
      this.popuniOpcije();
    })
  }

  loadDrzave(): void {
    this.drzavaService.getAll().subscribe(drzave => {
      this.drzave = drzave;
      // if (drzave.length > 0) {
      //   this.kljuceviDrzava = Object.keys(drzave[0]);
      // }
      console.log(drzave)
      this.popuniOpcije();

    });
  }

  addAdresa(formFieldsAdresa: any) {
    console.log(formFieldsAdresa);
    const payload = {
      ulica: formFieldsAdresa.ulica,
      broj: formFieldsAdresa.broj,
      mesto: {
        id: formFieldsAdresa.mesto
      }
    }
    this.adresaService.create(payload).subscribe(response => {
      console.log('Adresa uspešno kreirano:', response);
      this.loadAdrese();

    });
  }

  deleteAdresa(id: number): void {
    this.adresaService.delete(id).subscribe(() => {
      this.loadAdrese();
    });
  }

  addDrzava(formFieldsDrzava: any) {
    this.drzavaService.create(formFieldsDrzava).subscribe(response => {
      console.log("Drzava uspesno kreirana:", response);
      this.loadDrzave();
      this.refresh();  // Pozivanje refresh metode
    });
  }

  addMesto(formFieldsMesto: any) {

    const payload = {
      naziv: formFieldsMesto.naziv,
      drzava: {
        id: formFieldsMesto.drzava
      }
    }
    this.mestoService.create(payload).subscribe(response => {
      console.log("Mesto uspesno kreirano:", response);
      this.loadMesta();
      this.refresh();  // Pozivanje refresh metode
    });
  }

  deleteMesto(id: number): void {
    this.mestoService.delete(id).subscribe(() => {
      this.loadMesta();
    },
    (error) => {
      console.error('Greška prilikom brisanja adrese:', error);
      this.errorMessage = "Nije moguće obrisati entitet! Proverite povezane entitete."
    });
  }

  refresh() {
    const currentUrl = this.router.url;
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentUrl]);
    });
  }
}
