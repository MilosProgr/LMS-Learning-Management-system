export interface NastavnikNaRealizaciji {
    id?: number | null;
    brojCasova?: number;
    nastavnik? : {
        id?: number | null;
        biografija?: string;
        jmbg?: string;
        telefon?: string;
        poslovniMail?: string;
        brojSlobodnihDana?: number | null;
        brojIskoristenihDana?: number| null;

    }, 
    realizacijaPredmeta?: any[]
}