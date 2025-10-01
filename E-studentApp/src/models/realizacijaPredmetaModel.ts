import { EvaluacijaZnanja } from "./evaluacijaZnanja";
import { NastavnikNaRealizaciji } from "./nastavnikNaRealizacijiModel";
import { Predmet } from "./predmetModel";
import { Semestar } from "./semestarModel";
import { TerminNastave } from "./terminNastaveModel";

export interface RealizacijaPredmeta {
    
    id?: number | undefined,
    predmet?: Predmet
    nastavnikNaRealizaciji?: NastavnikNaRealizaciji
    evaluacijaZnanja?: EvaluacijaZnanja[],
    terminNastave?: TerminNastave[],
    semestri?: Semestar[];
}