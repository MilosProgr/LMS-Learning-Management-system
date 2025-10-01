import { Fakultet } from "./fakultetModel";
import { GodinaStudija } from "./godinaStudija/godinaStudija";

export interface StudijskiProgram {
    id?: number | undefined;
    naziv?: string;
    fakultet?: {
        id: number;
        naziv?: string;
    };
    godinaStudija?: GodinaStudija;
}