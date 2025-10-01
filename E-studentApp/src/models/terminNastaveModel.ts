import { TipNastave } from "./tipNastave"

export interface TerminNastave {
    id?: number,
    vremePocetka?: string;
    vremeKraja?: string;
    ishod?: {
        id: number
    },
    realizacijaPredmeta?: {
        id: number
    },
    tipNastave?: TipNastave
}