import { Component, OnInit } from '@angular/core';
import { Kurs } from '../../../models/kursModel';
import { FormField } from '../../generics/generic-form/form-model';
import { FormBuilder, Validators } from '@angular/forms';
import { KursService } from '../../../Services/kurs.service';
import { ActivatedRoute, Router } from '@angular/router';
import { GenericFormComponent } from '../../generics/generic-form/generic-form.component';

@Component({
  selector: 'app-kurs-edit',
  standalone: true,
  imports: [GenericFormComponent],
  templateUrl: './kurs-edit.component.html',
  styleUrl: './kurs-edit.component.css'
})
export class KursEditComponent implements OnInit {

  kursData: Kurs | null = null;
  kursevi: Kurs[] = [];

  formFieldsKurs: FormField[] = [
    {
      type: 'text',
      label: 'Naziv: ',
      name: 'naziv',
      validations: [Validators.required]
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

  constructor(
    private fb: FormBuilder,
    private kursService: KursService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadKurs();
    const id = Number(this.route.snapshot.paramMap.get('id'));
    console.log("ID izabrane kursa je:", id);

    if (id) {
      this.kursService.getOne(id).subscribe({
        next: (data) => {
          this.kursData = data;
          console.log("Kurs koji se menja je: ", this.kursData);

          // Ažuriraj vrednosti formFields-a
          this.formFieldsKurs = [
            {
              type: 'text',
              label: 'Naziv: ',
              name: 'naziv',
              validations: [Validators.required],
              value: this.kursData.naziv
            },
            {
              type: 'text',
              label: 'Trajanje: ',
              name: 'trajanje',
              validations: [Validators.required],
              value: this.kursData.trajanje
            },
            {
              type: 'text',
              label: 'Oznaka: ',
              name: 'oznaka',
              validations: [Validators.required],
              value: this.kursData.oznaka
            }
          ];
          console.log("Novi podaci za prikaz su: ", this.formFieldsKurs);
        },
        error: (err) => console.error('Error fetching Kurs data', err)
      });
    }
  }

  loadKurs(): void {
    this.kursService.getAll().subscribe((data) => {
      this.kursevi = data;
    });
  }

  handleFormSubmit(updatedData: Kurs | any): void {
    console.log("Novi uneti podaci: ", updatedData);

    if (updatedData) {
      const id = Number(this.route.snapshot.paramMap.get('id')); 

        const payload: Kurs = {
          id: id, 
          naziv: updatedData.naziv, 
          trajanje: updatedData.trajanje,
          oznaka: updatedData.oznaka
        }
        console.log("Payload for update: ", payload);

        this.kursService.update(id, payload).subscribe({
          next: () => {
            this.router.navigate(['/kurs-list']);
          },
          error: (err) => console.error('Error updating kurs', err)
        });
      } else {
        console.error('Selected kurs ID is invalid');
      }
    }
}
