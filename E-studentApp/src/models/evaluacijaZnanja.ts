import { Ishod } from "./ishodModel";
import { RealizacijaPredmeta } from "./realizacijaPredmetaModel";
import { TipEvaluacije } from "./tipEvaluacije";

export interface EvaluacijaZnanja {
    id?: number,
    vremePocetka?: Date,
    vremeZavrsetka?: Date,
    bodovi: number,
    ostvareniBodovi?: number
    ishod?: {
        id: number;
        polozeno?: boolean;
        predmet: {
            id: number;
        }
    },
    instrumentEvaluacije?: {
        id: number;
        opis?: string;
    },
    tipEvaluacije: {
        id: number;
        naziv?: string;
    },
    realizacijaPredmeta?: {
        id: number;
    },
    prijavljenIspit?: {
        id: number;
        brojPrijava?: number;
        prijavljen?: boolean;
    }

    prijavljeniIspit?: {
        id: number;
        brojPrijava?: number;
        prijavljen?: boolean;
    }

}