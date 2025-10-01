import { Drzava } from "./drzava";

export interface Mesto {
    id?: number;
    naziv: string;
    drzava_id?: number;
    drzava: {
        id: number;
        naziv?: string;
    };
}