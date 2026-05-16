import { GodinaStudija } from "../godinaStudija/godinaStudija";
import { Predmet } from "../predmetModel";
import { Sifra } from "../sifra";


export interface PredmetDialogData {
    mode: 'add' | 'edit';
    predmet?: Predmet;
    sifre: Sifra[];
    godine: GodinaStudija[];
}