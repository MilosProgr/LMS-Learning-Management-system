export interface Fakultet {
    id?: number;
    naziv: string;
    kontakt: string;
    opis?: string;
    adresa: {
        id?: number;
        ulica?: string,
        broj?: string;
        mesto: {
            id?: number;
        }
    }
    univerzitet: {
        id?: number;
        naziv?: string
    }
    dekan: {
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