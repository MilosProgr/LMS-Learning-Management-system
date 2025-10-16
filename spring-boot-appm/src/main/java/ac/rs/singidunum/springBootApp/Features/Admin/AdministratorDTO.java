package ac.rs.singidunum.springBootApp.Features.Admin;

import java.util.Date;

import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;


public class AdministratorDTO {
    
	
	public record AdministratorDTORecord(
			 Long id,
		     String jmbg,
		     String telefon,
		     String poslovniEmail,
		     Date datumkreiranjaNaloga,
		     boolean nalogAktivan,
		    

		     RegistrovaniKorisnikDTORecord korisnik
			) {}

}
