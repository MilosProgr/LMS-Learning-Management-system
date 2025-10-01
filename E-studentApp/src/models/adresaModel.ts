export interface Adresa {
    id?: number;
    broj: string;
    ulica: string;
    mesto: {
        id: number;
        naziv?: string;
    };

}