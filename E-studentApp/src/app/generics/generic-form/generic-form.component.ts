import { Component, EventEmitter, Input, OnInit, OnChanges, SimpleChanges, Output } from '@angular/core';
import { FormField } from './form-model';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-generic-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './generic-form.component.html',
  styleUrl: './generic-form.component.css'
})
export class GenericFormComponent implements OnInit, OnChanges {
  @Input() poljaUnutrasnjeForme: FormField[] = []; // Polja za unutrasnju generičku formu
  @Output() newEntitySubmit = new EventEmitter<any>(); // Dodato za unutrašnju formu

  @Input() fields: FormField[] = [];
  @Input() cancelRoute: string = ''; // input za ponistenje akcije forme
  @Output() ngSubmit = new EventEmitter<any>();
  @Output() SelectChangeEvent = new EventEmitter<any>();
  form!: FormGroup;
  miniForm!: FormGroup; //forma za unutrasnju generičku form
  isSubmitted = false;
  isSubmittedMiniForm = false;
  prikaziUnutrasnjuFormu = false; // Kontrola za prikazivanje nove forme za entitet unutrasnje forme

  constructor(private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.createForm();
    this.createNewEntityForm();
  }

  ngOnChanges(promene: SimpleChanges): void {
    if (promene['fields']) {
      this.createForm(); // Ponovno kreiranje forme kada se polja promene
    }
    if (promene['poljaUnutrasnjeForme']) {
      this.createNewEntityForm(); // Ponovno kreiranje forme kada se polja promene
    }
  }

  private createForm(): void {
    this.form = this.fb.group({});
    this.fields.forEach(field => {
      const control = this.fb.control(
        field.value,
        this.bindValidations(field.validations || [])
      );
      this.form.addControl(field.name, control);
    });
  }

  createNewEntityForm(): void {
    this.miniForm = this.fb.group({});
    this.poljaUnutrasnjeForme.forEach(field => {
      const control = this.fb.control(
        field.value || '',
        this.bindValidations(field.validations || [])
      );
      this.miniForm.addControl(field.name, control);
    });
  }

  bindValidations(validations: any) {
    if (validations.length > 0) {
      const validList: any[] = [];
      validations.forEach((validation: any) => {
        validList.push(validation);
      });
      return Validators.compose(validList); //Validators.compose uzima niz validacija i kombinuje ih u jednu funkciju. Ako ne postoje validacije, vraća null.

    }
    return null;
  }

  onSubmit() {
    console.log(this.form); // vidićeš invalid fieldove
    if (this.form.valid) {
      
      this.isSubmitted = true; //dodati i proveru da li je zahtev ka serveru dobro prosao
      this.ngSubmit.emit(this.form.value);
      this.form.reset();

      setTimeout(() => {
        this.isSubmitted = false;
      }, 3000); // 3 sekunde
    }
    else {
      console.log("Forma nije validna!");
    }
  }

  onNewEntitySubmit(): void {
    if (this.miniForm.valid) {
      console.log('Mini forma je validna');
      this.isSubmittedMiniForm = true;
      this.newEntitySubmit.emit(this.miniForm.value);
      this.closeNewEntityForm();
      this.miniForm.reset();
      setTimeout(() => {
        this.isSubmittedMiniForm = false;
      }, 3000); // 3 sekunde 

    } else {
      console.log('Mini forma nije validna');
    }
  }

  onCancel(): void {
    if (this.cancelRoute) {
      this.router.navigate([this.cancelRoute]); // Navigacija ka prosleđenoj ruti
    } else {
      console.error("Definisana ruta za povratak nije ispravna!");
    }
  }

  onSelectChange(event: any) {
    const selectedValue = event.target.value;

    if (selectedValue === 'addNew') {
      this.prikaziUnutrasnjuFormu = true;
    } else {
      this.prikaziUnutrasnjuFormu = false;
      this.SelectChangeEvent.emit(selectedValue); // Emituje se izabrana vrednost kada nije "Dodaj novi"
    }
  }
  openAddNewEntityDialog(noviEntitetData: any) {
    // Ovde možete otvoriti modal dijalog ili preusmeriti korisnika na stranicu za dodavanje novog entiteta
    console.log("Novi entitet dodan:", noviEntitetData);
    this.closeNewEntityForm();
    // Dodajte logiku za čuvanje novog entiteta

  }

  closeNewEntityForm(): void {
    this.prikaziUnutrasnjuFormu = false; // Zatvara deo forme za novi entitet
  }

}
