import { Adresa } from "../adresaModel";
import { Drzava } from "../drzava";
import { Mesto } from "../mesto";
import { Nastavnik } from "../nastavnik.model";
import { Univerzitet } from "../univerzitetModel";
import { Fakultet } from "./fakultetModel";

export interface FakultetDialogData {
    mode?: 'edit' | 'create';
    fakultet?: Fakultet;
    mesta: Mesto[];
    drzave: Drzava[];
    adrese: Adresa[];
    univerziteti: Univerzitet[];
    nastavniciBezFakulteta: Nastavnik[];
}