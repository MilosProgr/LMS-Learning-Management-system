export interface PrijavljeniPredmetSaOcenom {
    predmetId: number; // ID predmeta
    naziv: string; // Naziv predmeta
    esbn: string; // ESBN predmeta
    obavezan: boolean; // Da li je predmet obavezan
    ocena: number; // Konačna ocena
}