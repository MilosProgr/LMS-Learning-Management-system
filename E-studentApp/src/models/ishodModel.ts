import { Predmet } from './predmetModel';

export interface Ishod {
  id: number;
  opis: string;
  polozeno?: boolean;
  predmet: Predmet;
  nastavniMaterijali: any[]; // Adjust this based on your actual data
  aktivnosti: any; // Adjust this based on your actual data
}