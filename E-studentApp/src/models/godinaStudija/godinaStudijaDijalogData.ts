import { GodinaStudija } from "./godinaStudija";

export interface GodinaStudijaDialogData {
    mode: 'edit' | 'create';
    godina?: GodinaStudija;
}