import { Time } from "@angular/common";

export interface Obavestenje {
    id: number;
    naslov: string;
    tekst: string;
    datum?: string;
    vreme?: string;
}