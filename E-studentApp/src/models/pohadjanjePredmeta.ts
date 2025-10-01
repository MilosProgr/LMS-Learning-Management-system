export interface PohadjanjePredmeta {
    id?: number;
    konacnaOcena?: number | null;
    predmet: {
        id: number;
        naziv?: string
        esbn?: number;
        obavezan?: boolean,
        brojPredavanja?: number,
        brojVezbi?: number,
        drugiObliciNastave?: number,
        istrazivackiRad?: number,
        ostaliCasovi?: number,
        silabus?: number,
        sifra?: {
            id: number;
            godina?: number;
        },
        // studijskiProgrami: [],
        // godinaStudija: null,
        // realizacijaPredmeta: null,
        // kursevi: []
    }
    studentNaGodini?: {
        id: number;
        datumUpisa?: string;
        brojIndeksa?: string;
        // "student": null;
        // godinaStudija: {
        //     id: number | null;
        //     godina: number;
        // }
        prosek?: number,
        // "prijavljenIspit": null,
        // "studijskiProgram": null,
        // "pohadjanja": []
    }

}