import { ValidatorFn } from '@angular/forms';

export interface FormFieldOption {
  key: string;
  value: string;
}

export interface FormField {
  type: string;
  label: string;
  name: string;
  value?: unknown;
  options?: FormFieldOption[];
  validations?: ValidatorFn[];
}