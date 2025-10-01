import { Component, OnInit } from '@angular/core';
import { Drzava } from '../../../models/drzava';
import { FormField } from '../../generics/generic-form/form-model';
import { Validators } from '@angular/forms';
import { DrzavaService } from '../../../Services/drzava.service';
import { Router, RouterLink } from '@angular/router';
import { NgFor, NgIf } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';

@Component({
  selector: 'app-drzava-list',
  standalone: true,
  imports: [NgFor, NgIf, RouterLink, NgxPaginationModule],
  templateUrl: './drzava-list.component.html',
  styleUrl: './drzava-list.component.css'
})
export class DrzavaListComponent implements OnInit {
  drzave: Drzava[] = [];
  errorMessage = ''
  currentPage: number = 1;  // trenutna stranica
  itemsPerPage: number = 10;  // broj stavki po stranici

  
  kljuceviDrzava = [
    { imeKolone: 'Id', kljuc: 'id' },
    { imeKolone: 'Naziv', kljuc: 'naziv' },
  ];

  formFieldsDrzava: FormField[] = [
    {
      type: 'text',
      label: 'Naziv: ',
      name: 'naziv',
      validations: [Validators.required, Validators.pattern('^[A-Z].*')]
    }
  ];

  constructor(private drzavaService: DrzavaService, private router: Router) {}

  ngOnInit(): void {
    this.loadDrzave();
  }


  loadDrzave(): void {
    this.drzavaService.getAll().subscribe(drzave => {
      this.drzave = drzave;
      // if (drzave.length > 0) {
      //   this.kljuceviDrzava = Object.keys(drzave[0]);
      // }
      console.log(drzave)
      console.log("kljucevi za drzavu su:", this.kljuceviDrzava);
    });
  }

  addDrzava(formFieldsDrzava: any) {
    this.drzavaService.create(formFieldsDrzava).subscribe(response => {
      console.log("Drzava uspesno kreirana:", response);
      this.loadDrzave();
    });
  }
  deleteDrzava(id: number): void {
    this.drzavaService.delete(id).subscribe(() => {
      this.loadDrzave();
    },
    (error) => {
      console.error('Greška prilikom brisanja drzave:', error);
      this.errorMessage = "Nije moguće obrisati entitet! Proverite povezane entitete."
    }
  );
  }
  editDrzava(id: number): void {
    // Implement the logic to navigate to the edit form or open a modal for editing
    console.log('Editing address with id:', id);
    // this.router.navigate(['/edit-adresa', id]); // Example navigation
  }
}
