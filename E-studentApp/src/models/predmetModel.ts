import { GodinaStudija } from "./godinaStudija/godinaStudija";
import { Sifra } from "./sifra";
import { StudijskiProgram } from "./studijskiprogramModel";

export interface Predmet {
  id?: number;
  naziv?: string;
  esbn?: number;
  obavezan?: boolean;
  brojPredavanja?: number;
  brojVezbi?: number;
  drugiObliciNastave?: number;
  istrazivackiRad?: number;
  ostaliCasovi?: number;
  silabus?: number | null;   
  kursevi?: any
  brojPrijava?: number;
  studijskiProgrami?: StudijskiProgram[];
  sifra?: {
    id?: number;
    tekst?: string;
  }
  godinaStudija?:{
    id?: number | null;
    godina?: string;
  }
}
