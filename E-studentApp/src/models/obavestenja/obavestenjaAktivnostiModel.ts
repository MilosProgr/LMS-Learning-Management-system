import { RegistrovaniKorisnik } from "../registrovaniKorisnik";
import { Obavestenje } from "./obavestenjeModel";

export interface ObavestenjeAktivnosti {

    obavestenje: Obavestenje;

    registrovaniKorisnici: RegistrovaniKorisnik[];

    mode: string;

    id?: number;

    naslov?: string;

    sadrzaj?: string;

    procitano?: boolean;

    vremePostavljanja?: string;

    datum?: number[] | null;

    vreme?: number[] | null;

    registrovaniKorisnik: {
        id?: number,
        ime?: string,
        prezime?: string,
        korisnickoIme?: string;
    }
}