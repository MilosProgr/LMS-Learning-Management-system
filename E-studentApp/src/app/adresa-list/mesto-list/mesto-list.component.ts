import { Component, OnInit } from '@angular/core';
import { Mesto } from '../../../models/mesto';
import { NgxPaginationModule } from 'ngx-pagination';
import { MestoService } from '../../../Services/mesto.service';
import { Router, RouterLink } from '@angular/router';
import { Validators } from '@angular/forms';
import { FormField } from '../../generics/generic-form/form-model';
import { Drzava } from '../../../models/drzava';
import { DrzavaService } from '../../../Services/drzava.service';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-mesto-list',
  standalone: true,
  imports: [NgIf, NgFor, RouterLink, NgxPaginationModule],
  templateUrl: './mesto-list.component.html',
  styleUrl: './mesto-list.component.css'
})
export class MestoListComponent implements OnInit {
  
  currentPage: number = 1;  // trenutna stranica
  itemsPerPage: number = 15;  // broj stavki po stranici\
  mesta: Mesto[] = [];
  drzave: Drzava[] = [];
  errorMessage = ''

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

  constructor(private mestoService: MestoService, private drzavaService: DrzavaService, private router: Router) {
  }

  ngOnInit(): void {
    this.loadMesta();
    //console.log("Mesta su: ", this.mesta);
  }


  loadMesta(): void {
    this.mestoService.getAll().subscribe(mesta => {
      this.mesta = mesta;
      // if (mesta.length > 0) {
      //   this.kljuceviMesto = Object.keys(mesta[0]);
      // }
      console.log("Mesta: ", mesta)
      this.popuniOpcije();
    })
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
    });
  }

  deleteMesto(id: number): void {
    this.mestoService.delete(id).subscribe(() => {
      this.loadMesta();
    },
    (error) => {
      console.error('Greška prilikom brisanja mesta:', error);
      this.errorMessage = "Nije moguće obrisati entitet! Proverite povezane entitete."
    });
  }

  editMesto(id: number): void {
    // Implement the logic to navigate to the edit form or open a modal for editing
    console.log('Editing mesto with id:', id);
    // this.router.navigate(['/edit-adresa', id]); // Example navigation
  }

  popuniOpcije(): void {
    const drzavaField = this.formFieldsMesto.find(field => field.name === 'drzava');
    if (drzavaField) {
      drzavaField.options = this.drzave.map(drzava => ({ key: drzava.id !== undefined ? drzava.id.toString() : "Nema ID", value: drzava.naziv }));
    }
  }
}
