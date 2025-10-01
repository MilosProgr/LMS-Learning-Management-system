import { StatusOdmora } from "./status-odmora.enum";

export interface Nastavnik {

    id?: number | null;
    biografija?: string;
    jmbg?: string;
    telefon?: string;
    poslovniMail?: string;
    brojSlobodnihDana?: number;
    brojIskoristenihDana?: number;
    korisnik?:{
        id?: number | null;
        ime?: string;
        prezime?: string;
        korisnickoIme?: string;
        email?: string;
        pravaPristupa?: string[];
    }
    statusOdmora?: StatusOdmora;
    roles?: string[];
    zvanja?: any[];
    naucneOblasti?: any[];
    fakultet?: string | null;
}