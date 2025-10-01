export interface RegistrovaniKorisnik {
    id?: number;
    ime: string;
    prezime: string;
    korisnickoIme?: string;
    lozinka?: string;
    email?: string;
    roles?: string[];  // Uloge su niz stringova
}