export interface Univerzitet {
    id?: number | null;
    naziv: string;
    opis?: string;
    datumOsnivanja: string | null; // ili Date | null;
    adresa: {
        id?: number | null;
        ulica: string;
        broj: string;
        mesto?: {
            id?: number | null;
        } 
    }
    rektor: {
        id?: number;
        korisnik?:{
            id?: number;
            ime?: string;
            prezime?: string;
            korisnickoIme?: string;
            email?: string;
        }
    }
}