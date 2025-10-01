export interface UpisStudentaRequest {

  korisnikId: number;
  jmbg: string;
  telefon: string;
  ulica: string;
  broj: string;

  // Država: ID ili naziv (jedno od ova dva)
  drzavaId?: number | null;
  drzavaNaziv?: string | null;

  // mesto za ADRESU (jedno od ova dva)
  mestoId?: number | null;
  mestoNaziv?: string | null;

  // mesto PREBIVALIŠTA (jedno od ova dva)
  mestoPrebivalistaId?: number | null;
  mestoPrebivalistaNaziv?: string | null;
}