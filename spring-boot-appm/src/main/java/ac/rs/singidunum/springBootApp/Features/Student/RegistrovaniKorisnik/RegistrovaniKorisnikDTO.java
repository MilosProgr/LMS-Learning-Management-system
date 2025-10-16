package ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik;

import java.util.Set;


import ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti.ObavestenjeAktivnostDTO.ObavestenjeAktivnostiDTORecord;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermissionDTO.UserPermissionDTORecord;


public class RegistrovaniKorisnikDTO {
	
	

	public record RegistrovaniKorisnikDTORecord(
			 Long id,
			 String ime,
			 String prezime,
		     String korisnickoIme,
		     String lozinka,
		     String email,
		     Set<UserPermissionDTORecord> pravaPristupa,
		     Set<ObavestenjeAktivnostiDTORecord> obavestenjaAktivnosti
			) {
		
	}
	
}
