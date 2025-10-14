package ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik;

import java.util.Set;

import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Zvanje.NaucnaOblast.NaucnaOblastDTO.NaucnaOblastDTORecord;
import ac.rs.singidunum.springBootApp.Features.Zvanje.Zvanje.ZvanjeDTO.ZvanjeDTORecord;

//public class NastavnikDTO extends RegistrovaniKorisnikDTO {
public class NastavnikDTO {
	
	public record NastavnikDTORecord(
			 Long id,
			 String biografija,
			 String jmbg,
			 String telefon,
			 String poslovniMail,
			 int brojSlobodnihDana,
			 int brojIskoristenihDana,
			 RegistrovaniKorisnikDTORecord korisnik,

			 Set<ZvanjeDTORecord> zvanja,
			 Set<NaucnaOblastDTORecord> naucneOblasti
			) {}
	
}
