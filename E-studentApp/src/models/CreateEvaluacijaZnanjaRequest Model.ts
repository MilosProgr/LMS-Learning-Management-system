export interface CreateEvaluacijaZnanjaRequest {
  prijavljeniIspitId?: number;
  tipEvaluacijeId?: number;
  realizacijaPredmetaId?: number;
  ostvareniBodovi?: number;


  instrumentFileId?: number;
  instrumentOpis?: string;
  instrumentUrl?: string;


  ishodId?: number;
  ishodOpis?: string;
  ishodPredmetId?: number;
}