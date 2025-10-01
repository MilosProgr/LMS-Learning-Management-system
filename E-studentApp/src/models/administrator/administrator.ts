export interface Administrator {
    id?: number | null;
    jmbg: string;
    telefon: string;
    datumKreiranjaNaloga: number[] | null; 
    nalogAktivan: boolean;
    poslovniEmail: string;

}
