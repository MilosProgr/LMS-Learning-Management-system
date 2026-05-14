import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  OnChanges,
  SimpleChanges,
  Output
} from '@angular/core';

import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
  ValidatorFn
} from '@angular/forms';

import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormField } from './form-model';

@Component({
  selector: 'app-generic-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './generic-form.component.html',
  styleUrl: './generic-form.component.css'
})
export class GenericFormComponent implements OnInit, OnChanges {

  @Input() poljaUnutrasnjeForme: FormField[] = [];
  @Input() fields: FormField[] = [];
  @Input() cancelRoute = '';

  @Output() newEntitySubmit = new EventEmitter<Record<string, unknown>>();
  @Output() ngSubmit = new EventEmitter<Record<string, unknown>>();
  @Output() SelectChangeEvent = new EventEmitter<string>();

  form!: FormGroup;
  miniForm!: FormGroup;

  isSubmitted = false;
  isSubmittedMiniForm = false;
  prikaziUnutrasnjuFormu = false;

  constructor(
    private readonly fb: FormBuilder,
    private readonly router: Router
  ) { }

  ngOnInit(): void {
    this.createForm();
    this.createNewEntityForm();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['fields']) {
      this.createForm();
    }
    if (changes['poljaUnutrasnjeForme']) {
      this.createNewEntityForm();
    }
  }

  private createForm(): void {
    this.form = this.fb.group({});

    this.fields.forEach((field: FormField) => {
      this.form.addControl(
        field.name,
        this.fb.control(
          field.value ?? null,
          this.bindValidations(field.validations)
        )
      );
    });
  }

  private createNewEntityForm(): void {
    this.miniForm = this.fb.group({});

    this.poljaUnutrasnjeForme.forEach((field: FormField) => {
      this.miniForm.addControl(
        field.name,
        this.fb.control(
          field.value ?? '',
          this.bindValidations(field.validations)
        )
      );
    });
  }

  private bindValidations(validations?: ValidatorFn[]): ValidatorFn | null {
    return validations?.length ? Validators.compose(validations) : null;
  }

  onSubmit(): void {
    if (this.form.invalid) {
      return;
    }

    this.isSubmitted = true;
    this.ngSubmit.emit(this.form.value);

    this.form.reset();

    setTimeout(() => {
      this.isSubmitted = false;
    }, 3000);
  }

  onNewEntitySubmit(): void {
    if (this.miniForm.invalid) {
      return;
    }

    this.isSubmittedMiniForm = true;
    this.newEntitySubmit.emit(this.miniForm.value);

    this.closeNewEntityForm();
    this.miniForm.reset();

    setTimeout(() => {
      this.isSubmittedMiniForm = false;
    }, 3000);
  }

  onCancel(): void {
    if (!this.cancelRoute) {
      console.error('Cancel route nije definisan');
      return;
    }
    this.router.navigate([this.cancelRoute]);
  }

  onSelectChange(event: Event): void {
    const value = (event.target as HTMLSelectElement).value;

    if (value === 'addNew') {
      this.prikaziUnutrasnjuFormu = true;
      return;
    }

    this.prikaziUnutrasnjuFormu = false;
    this.SelectChangeEvent.emit(value);
  }

  closeNewEntityForm(): void {
    this.prikaziUnutrasnjuFormu = false;
  }
}