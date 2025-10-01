import { PohadjanjePredmeta } from "../pohadjanjePredmeta";

export interface StudentNaGodini {
    id?: number | null;
    datumUpisa?: string[];
    brojIndeksa?: string | null;
    prosek?: number;
    student?: {
        id: number | null;
        ime?: string;
        prezime?: string;
        statusStudiranja?: string;
    }
    
    godinaStudija?: {
        id: number;
        godina?: string;
    }
    studijskiProgram: {
        id: number;
        naziv?: string;
        godinaStudija?: string;
    };
    
    pohadjanja?: PohadjanjePredmeta[];
}