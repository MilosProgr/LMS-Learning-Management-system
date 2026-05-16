
import { RegistrovaniKorisnik } from "../registrovaniKorisnik";

export interface Obavestenje {
    sadrzaj: string;
    registrovaniKorisnik: RegistrovaniKorisnik;
    id: number;
    naslov: string;
    tekst: string;
    datum?: string;
    vreme?: string;
}