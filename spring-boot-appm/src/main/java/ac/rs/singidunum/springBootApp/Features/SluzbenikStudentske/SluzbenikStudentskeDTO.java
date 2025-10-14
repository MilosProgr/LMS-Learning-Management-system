package ac.rs.singidunum.springBootApp.Features.SluzbenikStudentske;

import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;

public class SluzbenikStudentskeDTO  {
	
//public class SluzbenikStudentskeDTO extends RegistrovaniKorisnikDTO {

	
	
	public record SluzbenikStudentskeDTORecord(
			 Long id,
			 String jmbg,
			 String telefon,
			 Boolean nalogAktivan,
			 RegistrovaniKorisnikDTORecord korisnik
			) {}
	
	
}
