export interface FakultetUpdateRequest {
    naziv: string;
    opis?: string;
    kontakt: string;

    dekanId: number;
    univerzitetId: number;

    ulica: string;
    broj: string;
    mestoId?: number;
}