import { Component, OnInit } from '@angular/core';
import { GenericFormComponent } from '../../../generics/generic-form/generic-form.component';
import { NgFor, NgIf } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Mesto } from '../../../../models/mesto';
import { FormField } from '../../../generics/generic-form/form-model';
import { FormBuilder, Validators } from '@angular/forms';
import { MestoService } from '../../../../Services/mesto.service';
import { DrzavaService } from '../../../../Services/drzava.service';
import { Drzava } from '../../../../models/drzava';

@Component({
  selector: 'app-mesto-edit',
  standalone: true,
  imports: [GenericFormComponent, NgFor, NgIf, RouterLink],
  templateUrl: './mesto-edit.component.html',
  styleUrl: './mesto-edit.component.css'
})
export class MestoEditComponent implements OnInit  {
  MestoData: Mesto | null = null;
  drzave: Drzava[] = [];

  //POLJE ZA MESTO
  mestoFormFields: FormField[] = [
    {
      type: 'text',
      label: 'Naziv: ',
      name: 'naziv',
      validations: [Validators.required, Validators.pattern('^[A-Z].*')],
      value: ''
    },
    {
      type: 'select',
      label: 'Drzava: ',
      name: 'drzava',
      options: [],
      validations: [Validators.required],
    }
  ];
  
  constructor(
    private fb: FormBuilder,
    private mestoService: MestoService,
    private drzavaService: DrzavaService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    console.log("ID izabranog mesta je:", id);

    if (id) {
      this.mestoService.getOne(id).subscribe({
        next: (data) => {
          this.MestoData = data;
          console.log("Mesto koje se menja je: ", this.MestoData);
          
          // Ažuriraj vrednosti formFields-a
          this.mestoFormFields = [
            {
              type: 'text',
              label: 'Naziv: ',
              name: 'naziv',
              validations: [Validators.required],
              value: this.MestoData.naziv
            },
            {
              type: 'select',
              label: 'Drzava: ',
              name: 'drzava',
              validations: [Validators.required],
              options: [] // Inicijalizuj opcije kao prazne
            }
          ];
  
          // Učitaj sve države nakon što je MestoData postavljeno
          this.loadDrzave(); // Pozovi učitavanje država ovde
        },
        error: (err) => console.error('Error fetching mesto data', err)
      });
    } else {
      this.loadDrzave(); // Učitaj države ako ID nije prisutan
    }
  }

  handleFormSubmit(updatedData: Mesto | any): void {
    console.log("Mesto za izmenu: ", updatedData); // Debug: Check what data is being received

    if (updatedData) {
      const id = Number(this.route.snapshot.paramMap.get('id')); // Get the mesto ID from the route

      // Debug: Log addresses and selected address ID
      console.log("All drzave: ", this.drzave);
      console.log("Drzava koja se menja: ", updatedData.drzava);

      // Find the selected address by ID
      const selectedDrzava = this.drzave.find(drzava => drzava.id === Number(updatedData.drzava));

      // Debug: Log found address and its ID
      console.log("Found drzava: ", selectedDrzava);

      if (selectedDrzava && typeof selectedDrzava.id === 'number') {
        // Create the payload with only the address ID
        const payload: Mesto = {
          id: id,
          naziv: updatedData.naziv,
          //u adresi moraju se ostali podaci dodati u polje,iz nekog razloga ne radi,i kreira se novi id u bazi i u tabeli posle edita
          drzava: { id: selectedDrzava.id } // Only ID of the address
        };

        console.log("Payload for update: ", payload);
        this.mestoService.update(id, payload).subscribe({
          next: () => {
            this.router.navigate(['/adresa-list']); // Navigate to the list after update
            console.log("Mesto uspesno izmenjen!");
            
          },
          error: (err) => console.error('Error updating mesto', err) // Handle errors
        });
      } else {
        console.error('Selected drzava ID is invalid'); // Log if the selected address ID is not valid
      }
    }
  }
    

  loadDrzave(): void {
    this.drzavaService.getAll().subscribe((data) => {
      this.drzave = data;
      this.popuniOpcije();
    });
  }

  popuniOpcije(): void {
    const drzavaField = this.mestoFormFields.find(field => field.name === 'drzava');
    if (drzavaField) {
      drzavaField.options = this.drzave.map(drzava => ({ key: drzava.id !== undefined ? drzava.id.toString() : "Nema ID", value: drzava.naziv }));
    }
  }
  

}
