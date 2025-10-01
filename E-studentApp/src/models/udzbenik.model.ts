import { GodinaStudija } from "./godinaStudija/godinaStudija";
import { Predmet } from "./predmetModel";
import { StudijskiProgram } from "./studijskiprogramModel";

export interface Udzbenik {
  id?: number;
  naziv: string;

  predmet_id?: number | null;
  izdavanje_udzbenika_id?: number | null;

  godinaStudija?: GodinaStudija | null;
  studijskiProgram?: StudijskiProgram;

  predmet?: Predmet | null;
}
