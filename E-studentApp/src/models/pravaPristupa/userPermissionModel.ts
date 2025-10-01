import { Uloga } from "../uloga";

export interface UserPermissionList {
    id: number;

    korisnik: {
        id: number;
        ime: string;
        prezime: string;
        korisnickoIme: string;
        lozinka: string;
        email: string;
    }
    listaUloga: Uloga[];
}

export interface UserPermission {

    id: number;
    permission?: {
        id: number;
        ime: string;
    }
    korisnik: {
        id: number;
        ime?: string;
        prezime?: string;
        korisnickoIme: string;
        lozinka?: string;
        email?: string;
        pravaPristupa?: [],
        obavestenjaAktivnosti?: []
    }

}