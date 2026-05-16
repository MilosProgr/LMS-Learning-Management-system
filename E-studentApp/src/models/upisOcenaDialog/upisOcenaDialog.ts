import { PohadjanjePredmeta } from "../pohadjanjePredmeta";

export interface UpisOceneDialogData {
    totalBodovi?: number;

    prijavaIspita?: {
        studentNaGodini?: {
            id?: number;
        };

        predmet?: {
            id?: number;
        };
    };

    pohadjanjaPredmeta?: PohadjanjePredmeta[];
}