import { RegistrovaniKorisnik } from '../registrovaniKorisnik';

export interface SluzbenikStudentske {

    korisnik: RegistrovaniKorisnik;

    id?: number | null;

    korisnickoIme?: string;

    imePrezime?: string;

    email?: string;

    jmbg?: string;

    telefon?: string;

    nalogAktivan?: boolean;
}