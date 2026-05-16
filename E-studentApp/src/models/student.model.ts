export interface Student {
  korisnickoIme: string;
  ime: string;
  prezime: string;
  brojSlobodnihDana: number;
  brojIskoristenihDana: number;
  id?: number;
  statusStudiranja?: string;
  stanjeNaRacunu?: number;
  predmetiIzabrani?: boolean;

  jmbg?: string;
  telefon?: string;
  brojIndeksa?: string;

  adresa: {
    id?: number | null;
    ulica: string;
    broj: string;
    mesto?: {
      id?: number | null;
    }
  },
  drzava?: {
    id: number;
  },
  studentNaGodini: {
    brojIndeksa?: string;
  };
  studijskiProgram?: {
    id: number;
    naziv: string;
  },
  mestoPrebivalista?: {
    id: number;
    naziv?: string
  },
  korisnik: {
    korisnickoIme: string;
    email: string;
    id: number,
    ime: string,
    prezime: string
  }
}