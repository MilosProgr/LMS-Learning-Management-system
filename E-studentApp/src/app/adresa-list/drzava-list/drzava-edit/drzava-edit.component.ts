import { Component, OnInit } from '@angular/core';
import { GenericFormComponent } from '../../../generics/generic-form/generic-form.component';
import { ActivatedRoute, Router, RouterLink, RouterModule } from '@angular/router';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Drzava } from '../../../../models/drzava';
import { FormField } from '../../../generics/generic-form/form-model';
import { DrzavaService } from '../../../../Services/drzava.service';

@Component({
  selector: 'app-drzava-edit',
  standalone: true,
  imports: [RouterLink, RouterModule, ReactiveFormsModule, CommonModule, GenericFormComponent],
  templateUrl: './drzava-edit.component.html',
  styleUrls: ['./drzava-edit.component.css'] 
})
export class DrzavaEditComponent implements OnInit {
  drzavaData: Drzava | null = null;

  drzavaFormFields: FormField[] = [
    {
      type: 'text',
      label: 'Naziv: ',
      name: 'naziv',
      validations: [Validators.required, Validators.pattern('^[A-Z].*')],
      value: ''
    }
  ];
  
  constructor(
    private fb: FormBuilder,
    private drzavaService: DrzavaService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    console.log("ID izabrane drzave je:", id);

    if (id) {
      this.drzavaService.getOne(id).subscribe({
        next: (data) => {
          this.drzavaData = data;
          console.log("Drzava koja se menja je: ", this.drzavaData);

          // Ažuriraj vrednosti formFields-a
          this.drzavaFormFields = [
            {
              type: 'text',
              label: 'Naziv: ',
              name: 'naziv',
              validations: [Validators.required],
              value: this.drzavaData ? this.drzavaData.naziv : 'N/A'
            }
          ];
        },
        error: (err) => console.error('Error fetching drzava data', err)
      });
    }
  }

  handleFormSubmit(updatedData: Drzava): void {
    console.log("Funkcija radi", updatedData); // Debug: Proverite koji podaci se primaju

    const id = Number(this.route.snapshot.paramMap.get('id')); // Uzimanje ID-a iz rute

    if (isNaN(id)) {
        console.error("ID nije validan ili nije pronađen u ruti.");
        return; // Prekini funkciju ako ID nije validan
    }

    console.log("ID je Validan", id);
    // Kreirajte payload sa ID-om
    const payload: any = {
        id: id, // Dodajte ID ovde
        naziv: updatedData.naziv // Proverite da li je naziv ispravno postavljen
    };
    console.log("Payload za ažuriranje: ", payload); // Debug: Proverite payload

    this.drzavaService.update(id, payload).subscribe({
        next: () => {
            this.router.navigate(['/adresa-list']); // Navigirajte na listu nakon ažuriranja
            console.log("Država uspešno izmenjena!");
        },
        error: (err) => console.error('Greška prilikom ažuriranja države', err) // Rukovanje greškama
    });
}

}
