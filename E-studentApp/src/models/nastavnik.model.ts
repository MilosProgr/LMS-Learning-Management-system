// import { StatusOdmora } from "./status-odmora.enum";

import { RegistrovaniKorisnik } from './registrovaniKorisnik';

export interface Nastavnik {
    ime: string;
    prezime: string;
    brojIskoristenihDana: number;

    id?: number | null;

    korisnik?: RegistrovaniKorisnik;

    korisnickoIme?: string;

    imePrezime?: string;

    email?: string;

    telefon?: string;

    jmbg?: string;

    poslovniMail?: string;

    biografija?: string;

    brojSlobodnihDana?: number;

    brojIskoriscenihDana?: number;
}
// statusOdmora ?: StatusOdmora;
