import { Component, OnInit } from '@angular/core';
import { Uloga } from '../../../models/uloga';
import { UlogaService } from '../../../Services/uloga.service';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { GenericReusableTableComponent } from '../../generics/generic-reusable-table/generic-reusable-table.component';
import { FormField } from '../../generics/generic-form/form-model';
import { AbstractControl, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { GenericFormComponent } from '../../generics/generic-form/generic-form.component';

@Component({
  selector: 'app-uloga-list',
  standalone: true,
  imports: [RouterLink, CommonModule, GenericReusableTableComponent, GenericFormComponent],
  templateUrl: './uloga-list.component.html',
  styleUrl: './uloga-list.component.css'
})
export class UlogaListComponent implements OnInit{
  uloge: Uloga[] = [];
  errorMessage = "";

  kljuceviUloga = [
    { imeKolone: 'Ime', kljuc: 'ime' },
    { imeKolone: 'Id', kljuc: 'id' }
  ];

  constructor(private ulogaService: UlogaService) {}

  ngOnInit(): void {
    this.loadUloge();
    // console.log(this.uloge);
    
  }

  formFieldsUloga: FormField[] = [
    {
      type: 'text',
      label: 'Ime: ',
      name: 'ime',
      validations: [Validators.required, this.roleValidator()]
    }
  ];

  loadUloge(): void {
    this.ulogaService.getAll().subscribe((data) => {
      this.uloge = data;
      // if (this.uloge.length > 0) {
      //   this.kljuceviUloga = Object.keys(this.uloge[0]);
        console.log("kljucevi za ulogu su:", this.kljuceviUloga);
        console.log("Uloge su:", this.uloge);
      // }
    });
  }

  addUloga(formFieldsUloga: any) {
    this.ulogaService.create(formFieldsUloga).subscribe(response => {
      console.log("Uloga uspesno kreirana:", response);
      this.loadUloge();

      this.errorMessage="Uloga uspesno dodata."
      // Obrisati poruku nakon 2 sekunde
      setTimeout(() => {
        this.errorMessage = ''; 
      }, 2000);
    
    });
  }

  deleteUloga(id: number): void {
    this.ulogaService.delete(id).subscribe(() => {
      this.loadUloge(); // Ponovo učitavanje liste nakon brisanja
    });
  }
  
  // Prilagođeni validator za ulogu
  private roleValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;
      // Proverava da li je unos u formatu ROLE_XXX
      const valid = /^ROLE_[A-Z]+$/.test(value);
      return valid ? null : { roleInvalid: true };
    };
  }
}