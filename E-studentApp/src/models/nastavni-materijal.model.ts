export interface NastavniMaterijal {
  id: number;
  naziv: string;
  kolicina: number;
  odobreno: boolean | null;
  podnosilac_zahteva_id: number;
  autorizator_id?: number | null;
}
