import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { StudijskiProgram } from '../../models/studijskiprogramModel';
import { StudijskiProgramService } from '../../Services/studijski-program.service';
import { GenericFormComponent } from '../generics/generic-form/generic-form.component';
import { Fakultet } from '../../models/fakultetModel';
import { FakultetService } from '../../Services/fakultet.service';
import { Validators } from '@angular/forms';
import { FormField } from '../generics/generic-form/form-model';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-studijskiprogram',
  standalone: true,
  imports: [NgFor, NgIf, RouterLink, GenericFormComponent],
  templateUrl: './studijskiprogram.component.html',
  styleUrl: './studijskiprogram.component.css'
})
export class StudijskiprogramComponent implements OnInit {


  programi: StudijskiProgram[] = [];
  fakulteti: Fakultet[] = [];
  errorMessage="";

  constructor(private programService: StudijskiProgramService, private fakultetService: FakultetService, private router: Router) { }

  formFieldsStudijskiProgram: FormField[] = [
    {
      type: 'text',
      label: 'Naziv: ',
      name: 'naziv',
      validations: [Validators.required, Validators.pattern('^[A-Z].*')]
    },
    {
      type: 'select',
      label: 'Odabir Fakultet: ',
      name: 'fakultet',
      validations: [Validators.required]
    }
  ];

  ngOnInit(): void {
    this.loadProgrami();
    this.loadFakulteti();
  }

  loadProgrami() {
    this.programService.getAll().subscribe(programi => {
      this.programi = programi;
      console.log("Studijski programi su: ", programi);
    })
  }

  loadFakulteti(): void {
    this.fakultetService.getAll().subscribe(fakulteti => {
      this.fakulteti = fakulteti;
      console.log("fakulteti", fakulteti)
      this.popuniOpcije();
    })
  }

  addStudijskiProgram(formFieldsStudijskiProgram: any) {

    const payload = {
      naziv: formFieldsStudijskiProgram.naziv,
      fakultet: {
        id: formFieldsStudijskiProgram.fakultet,
        naziv: formFieldsStudijskiProgram.fakultet.naziv,
        adresa: formFieldsStudijskiProgram.fakultet.adresa,
        univerzitet: formFieldsStudijskiProgram.fakultet.univerzitet
      }
    }
    console.log("payload",  payload);
    
    console.log(formFieldsStudijskiProgram);
    this.programService.create(payload).subscribe(response => {
      console.log('Studijski program uspešno kreiran:', response);
      this.loadProgrami();

      this.router.navigate(['/studijskiProgram-list']);

      this.errorMessage="Studijski program uspesno dodat."
      // Obrisati poruku nakon 2 sekunde
      setTimeout(() => {
        this.errorMessage = ''; 
      }, 2000);
    });
    console.log("Svi kreirani programi su: ", this.programi);
    
    }

  popuniOpcije(): void {
    const fakultetField = this.formFieldsStudijskiProgram.find(field => field.name === 'fakultet');
    if (fakultetField) {
      fakultetField.options = this.fakulteti.map(fakultet => ({ key: fakultet.id?.toString()?? '', value: fakultet.naziv}));
    }
  }

  izbrisiStudijskiProgram(id: number): void {
    this.programService.delete(id).subscribe(() => {
      this.loadProgrami();
    });
  }

}

