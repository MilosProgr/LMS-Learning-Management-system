import { RegistrovaniKorisnik } from '../registrovaniKorisnik';

export interface Administrator {
    brojSlobodnihDana: number;
    brojIskoristenihDana: number;

    korisnik: RegistrovaniKorisnik;

    id?: number | null;

    korisnickoIme?: string;

    imePrezime?: string;

    email?: string;

    jmbg?: string;

    telefon?: string;

    datumKreiranjaNaloga?: number[] | null;

    nalogAktivan?: boolean;

    poslovniEmail?: string;
}