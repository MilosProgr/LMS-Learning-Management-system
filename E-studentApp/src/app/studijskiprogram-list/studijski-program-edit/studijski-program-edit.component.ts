import { Component, OnInit } from '@angular/core';
import { GenericFormComponent } from '../../generics/generic-form/generic-form.component';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Fakultet } from '../../../models/fakultetModel';
import { FormBuilder, Validators } from '@angular/forms';
import { FakultetService } from '../../../Services/fakultet.service';
import { FormField } from '../../generics/generic-form/form-model';
import { StudijskiProgram } from '../../../models/studijskiprogramModel';
import { StudijskiProgramService } from '../../../Services/studijski-program.service';

@Component({
  selector: 'app-studijski-program-edit',
  standalone: true,
  imports: [GenericFormComponent, RouterLink],
  templateUrl: './studijski-program-edit.component.html',
  styleUrl: './studijski-program-edit.component.css'
})
export class StudijskiProgramEditComponent implements OnInit{
  
  fakulteti: Fakultet[] = [];
  studijskiProgramData: StudijskiProgram | null = null;

  studijskiProgramFormFields: FormField[] = [
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

  constructor(
    private fb: FormBuilder,
    private fakultetService: FakultetService,
    private studijskiprogramService: StudijskiProgramService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    console.log("ID izabranog studijskog programa je:", id);

    if (id) {
      // Učitajte StudijskiProgram
      this.studijskiprogramService.getOne(id).subscribe({
        next: (data) => {
          this.studijskiProgramData = data;
          console.log("Studijski koji se menja je: ", this.studijskiProgramData);

                this.studijskiProgramFormFields = [
        {
          type: 'text',
          label: 'Naziv: ',
          name: 'naziv',
          validations: [Validators.required],
          value: this.studijskiProgramData.naziv
        },
        {
          type: 'select',
          label: 'Odabir Fakultet: ',
          name: 'fakultet',
          validations: [Validators.required]
        }
      ];
        },
        error: (err) => console.error('Error fetching studijski program data', err)
      });
    }
    // Učitajte sve fakultete
    this.loadFakulteti();
  }


  handleFormSubmit(updatedData: StudijskiProgram | any): void {
    console.log("Novi uneti podaci: ", updatedData);

    if (updatedData) {
      const id = Number(this.route.snapshot.paramMap.get('id')); 

        const payload: StudijskiProgram = {
          id: id, 
          naziv: updatedData.naziv, 
          fakultet: { id: Number(updatedData.fakultet) }
        }
        
        console.log("Payload for update: ", payload);

        this.studijskiprogramService.update(id, payload).subscribe({
          next: () => {
            this.router.navigate(['/studijskiProgram-list']);
          },
          error: (err) => console.error('Error updating studijski program', err)
        });
      } else {
        console.error('Selected studijski program ID is invalid');
      }
  }

  loadFakulteti(): void {
    this.fakultetService.getAll().subscribe(fakulteti => {
      this.fakulteti = fakulteti;
      console.log("Lista fakulteta: ", this.fakulteti)
      this.popuniOpcije();
    })
  }

  popuniOpcije(): void {
    const fakultetField = this.studijskiProgramFormFields.find(field => field.name === 'fakultet');
    if (fakultetField) {
      fakultetField.options = this.fakulteti.map(fakultet => ({
        key: fakultet.id?.toString() ?? '',
        value: fakultet.naziv
      }));
    }
  }
}
