import { Component } from '@angular/core';
import { GenericFormComponent } from '../../generics/generic-form/generic-form.component';
import { FormBuilder, NgForm, Validators } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Mesto } from '../../../models/mesto';
import { Drzava } from '../../../models/drzava';
import { Adresa } from '../../../models/adresaModel';
import { FormField } from '../../generics/generic-form/form-model';
import { MestoService } from '../../../Services/mesto.service';
import { DrzavaService } from '../../../Services/drzava.service';
import { AdresaService } from '../../../Services/adresa.service';

@Component({
  selector: 'app-adresa-edit',
  standalone: true,
  imports: [GenericFormComponent, NgIf, NgFor, RouterLink],
  templateUrl: './adresa-edit.component.html',
  styleUrl: './adresa-edit.component.css'
})
export class AdresaEditComponent {
  mesta: Mesto[] = [];
  AdresaData: Adresa | null = null;

  formFieldsAdresa: FormField[] = [
    {
      type: 'text',
      label: 'Ulica: ',
      name: 'ulica',
      validations: [Validators.required, Validators.pattern('^[A-Z].*')],
      value: ''
    },
    {
      type: 'text',
      label: 'Broj: ',
      name: 'broj',
      validations: [Validators.required],
      value: ''
    },
    {
      type: 'select',
      label: 'Mesto: ',
      name: 'mesto',
      options: [],
      validations: [Validators.required]
    }
  ];
  
  constructor(
    private fb: FormBuilder,
    private mestoService: MestoService,
    private adresaService: AdresaService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    console.log("ID izabrane adrese je:", id);

    if (id) {
      
      this.adresaService.getOne(id).subscribe({
        next: (data) => {
          this.AdresaData = data;
          console.log("Adresa koje se menja je: ", this.AdresaData);

          // Ažuriraj vrednosti formFields-a
          this.formFieldsAdresa = [
            {
              type: 'text',
              label: 'Ulica: ',
              name: 'ulica',
              validations: [Validators.required],
              value: this.AdresaData.ulica
            },
            {
              type: 'text',
              label: 'Broj: ',
              name: 'broj',
              validations: [Validators.required],
              value: this.AdresaData.broj
            },
            {
              type: 'select',
              label: 'Mesto: ',
              name: 'mesto',
              validations: [Validators.required],
              options: []
            }
          ];
        },
        error: (err) => console.error('Error fetching adresa data', err)
      });
    }
    this.loadMesta();
  }

  handleFormSubmit(updatedData: Mesto | any): void {
    console.log("Adresa za izmenu: ", updatedData); // Debug: Check what data is being received

    if (updatedData) {
      const id = Number(this.route.snapshot.paramMap.get('id')); // Get the mesto ID from the route

      // Debug: Log addresses and selected address ID
      console.log("All mesta: ", this.mesta);
      console.log("Mesto koje se menja: ", updatedData.mesto);

      // Find the selected address by ID
      const selectedMesto = this.mesta.find(mesto => mesto.id === Number(updatedData.mesto));

      // Debug: Log found address and its ID
      console.log("Found Mesto: ", selectedMesto);

      if (selectedMesto && typeof selectedMesto.id === 'number') {
        // Create the payload with only the address ID
        const payload: Adresa = {
          id: id,
          ulica: updatedData.ulica,
          broj: updatedData.broj,
          mesto: { id: selectedMesto.id } 
        };

        console.log("Payload for update: ", payload);
        this.adresaService.update(id, payload).subscribe({
          next: () => {
            this.router.navigate(['/adresa-list']); // Navigate to the list after update
            console.log("Adresa uspesno izmenjena!");
            
          },
          error: (err) => console.error('Error updating Adresa', err) // Handle errors
        });
      } else {
        console.error('Selected mesto ID is invalid'); // Log if the selected address ID is not valid
      }
    }
  }
    

  loadMesta(): void {
    this.mestoService.getAll().subscribe((data) => {
      this.mesta = data;
      this.popuniOpcije();
    });
  }

  popuniOpcije(): void {
    const mestoField = this.formFieldsAdresa.find(field => field.name === 'mesto');
    if (mestoField) {
      mestoField.options = this.mesta.map(mesto => ({ key: mesto.id?.toString() ?? "Nema opcija", value: mesto.naziv }));
    }
  }
}
