import { AktivnostiComponent } from '../app/aktivnosti/aktivnosti.component';
import { NastavniMaterijal } from './nastavni-materijal.model';
import { Predmet } from './predmetModel';

export interface Ishod {
  editMode: boolean;
  id: number;
  opis: string;
  polozeno?: boolean;
  predmet: Predmet;
  nastavniMaterijali: NastavniMaterijal[]; // Adjust this based on your actual data
  aktivnosti: AktivnostiComponent; // Adjust this based on your actual data
}