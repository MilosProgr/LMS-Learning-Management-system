package ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti;

import java.time.LocalDateTime;

import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;


public class ObavestenjeAktivnostDTO {
	
	
	public record ObavestenjeAktivnostiDTORecord(
			Long id,
	        LocalDateTime vremePostavljanja,
	        String sadrzaj,
	        String naslov,
	        Boolean procitano,
	        RegistrovaniKorisnikDTORecord registrovaniKorisnik
			) {}
	
}
