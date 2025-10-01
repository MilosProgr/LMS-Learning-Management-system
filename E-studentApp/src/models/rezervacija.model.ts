import { StatusOdmora } from "./status-odmora.enum";

// rezervacija.model.ts
export interface Rezervacija {
    id?: number | null;
    opis: string;
    datumOd?: string;
    datumDo?: string;
    nastavnikId?: number;
    // brojPreostalihDana?: number;
    statusOdmora: StatusOdmora;
    nastavnik: {
    id?: number | null;
    };
  }
  