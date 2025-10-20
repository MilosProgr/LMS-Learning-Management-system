import { PohadjanjePredmeta } from "./pohadjanjePredmeta";

export interface PrijavljeniIspit {
    id?: number;
    prijavljen: boolean,
    brojPrijava?: number;
    predmet: {
        id: number;
        naziv?: string;
        esbn?: number;
        obavezan?: boolean;
    }
    ispitniRok?: {
        id: number | null;
        naziv?: string;
        pocetak?: string;
        kraj?: string;
        redovan?: boolean;
    }
    StudentNaGodini: {
        id: number | number;
        brojIndeksa?: string;
        pohadjanjePredmeta?: PohadjanjePredmeta[]

    },
    evaluzacijaZnanja?: {
        bodovi: number;
    }

}