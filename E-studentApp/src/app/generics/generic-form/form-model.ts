export interface FormField {
    type: string;
    label: string;
    name: string;
    value?: any;
    options?: { key: string; value: string }[]; // Ako je tip 'select' ili 'multiselect'
    validations?: any[];
  }
  