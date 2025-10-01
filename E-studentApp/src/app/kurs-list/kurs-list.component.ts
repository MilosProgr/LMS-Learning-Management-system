import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Kurs } from '../../models/kursModel';
import { KursService } from '../../Services/kurs.service';
import { GenericReusableTableComponent } from '../generics/generic-reusable-table/generic-reusable-table.component';
import { FormField } from '../generics/generic-form/form-model';
import { Validators } from '@angular/forms';
import { GenericFormComponent } from '../generics/generic-form/generic-form.component';
import { timeout } from 'rxjs';

@Component({
  selector: 'app-kurs-list',
  standalone: true,
  imports: [NgIf, GenericReusableTableComponent, GenericFormComponent],
  templateUrl: './kurs-list.component.html',
  styleUrl: './kurs-list.component.css'
})
export class KursListComponent implements OnInit {
  kursevi: Kurs[] = [];
  kljucevi: string[] = [];
  errorMessage = "";


  constructor(private kursService: KursService) {

  }
  ngOnInit(): void {
    this.loadKursevi();

  }

  formFieldsKurs: FormField[] = [
    {
      type: 'text',
      label: 'Naziv: ',
      name: 'naziv',
      validations: [Validators.required, Validators.pattern('^[A-Z].*')]
    },
    {
      type: 'text',
      label: 'Trajanje: ',
      name: 'trajanje',
      validations: [Validators.required]
    },
    {
      type: 'text',
      label: 'Oznaka: ',
      name: 'oznaka',
      validations: [Validators.required]
    }
  ];
  
  loadKursevi(): void {
    this.kursService.getAll().subscribe(kursevi => {
      this.kursevi = kursevi;
      if (kursevi.length > 0) {
        this.kljucevi = Object.keys(kursevi[0])
      }
      console.log(kursevi);
    });
  }

  addKurs(formFieldsKurs: any) {
    this.kursService.create(formFieldsKurs).subscribe(response => {
      console.log("Kurs uspesno kreiran:", response);
      this.loadKursevi();
      this.errorMessage= "Kurs uspesno dodat."
      // Obrisati poruku nakon 2 sekunde
      setTimeout(() => {
        this.errorMessage = '';  
      }, 2000);
    });
  }

  deleteKurs(id: number): void {
    this.kursService.delete(id).subscribe(() => {
      this.loadKursevi(); // Ponovo učitavanje liste nakon brisanja
    });
  }
}
