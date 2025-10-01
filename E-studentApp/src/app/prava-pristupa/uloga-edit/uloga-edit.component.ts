import { CommonModule } from '@angular/common';
import {Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { GenericFormComponent } from '../../generics/generic-form/generic-form.component';
import { Uloga } from '../../../models/uloga';
import { FormField } from '../../generics/generic-form/form-model';
import { AbstractControl, FormBuilder, ReactiveFormsModule, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { UlogaService } from '../../../Services/uloga.service';

@Component({
  selector: 'app-uloga-edit',
  standalone: true,
  imports: [CommonModule, RouterLink, GenericFormComponent, ReactiveFormsModule],
  templateUrl: './uloga-edit.component.html',
  styleUrl: './uloga-edit.component.css'
})
export class UlogaEditComponent implements OnInit {

  ulogaData: Uloga | null = null;
  uloge: Uloga[] = [];

  ulogaFormFields: FormField[] = [
    {
      type: 'text',
      label: 'Ime: ',
      name: 'ime',
      validations: [Validators.required, this.roleValidator()],
      value: '' // Vrednost će se kasnije ažurirati
    }
  ];

  constructor(
    private fb: FormBuilder,
    private ulogaService: UlogaService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadUloge();
    const id = Number(this.route.snapshot.paramMap.get('id'));
    console.log("ID izabrane uloge je:", id);

    if (id) {
      this.ulogaService.getOne(id).subscribe({
        next: (data) => {
          this.ulogaData = data;
          console.log("Uloga koja se menja je: ", this.ulogaData);

          // Ažuriraj vrednosti formFields-a
          this.ulogaFormFields = [
            {
              type: 'text',
              label: 'Ime: ',
              name: 'ime',
              validations: [Validators.required],
              value: this.ulogaData?.ime || '' // Popunjavanje sa dobijenom vrednošću
            }
          ];
          console.log("Novi podaci za prikaz su: ", this.ulogaFormFields);
        },
        error: (err) => console.error('Error fetching Uloga data', err)
      });
    }
  }

  loadUloge(): void {
    this.ulogaService.getAll().subscribe((data) => {
      this.uloge = data;
    });
  }

  handleFormSubmit(updatedData: Uloga | any): void {
    console.log("Novi uneti podaci: ", updatedData);

    if (updatedData) {
      const id = Number(this.route.snapshot.paramMap.get('id')); 

        const payload: Uloga = {
          id: id, 
          ime: updatedData.ime, 
        }
        console.log("Payload for update: ", payload);

        this.ulogaService.update(id, payload).subscribe({
          next: () => {
            this.router.navigate(['/uloga-list']);
          },
          error: (err) => console.error('Error updating uloga', err)
        });
      } else {
        console.error('Selected uloga ID is invalid');
      }
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