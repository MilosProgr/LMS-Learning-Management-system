export interface ObavestenjeAktivnosti {
    id?: number;
    naslov?: string;
    sadrzaj?: string;
    procitano?: boolean;
    vremePostavljanja?: string;

    datum?: number[] | null; 
    vreme?: number[] | null;
    registrovaniKorisnik: {
        id?: Number,
        ime?: string,
        prezime?: string,
        korisnickoIme?: string;
    }
}