package ac.rs.singidunum.springBootApp.Features.Predmeti.NastavniMaterijal;

import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO.NastavnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.SluzbenikStudentske.SluzbenikStudentskeDTO.SluzbenikStudentskeDTORecord;

public class NastavniMaterijalDTO {
	
	
	public record NastavniMaterijalDTORecord(
			Long id,
			String naziv,
			Boolean odobreno,
			Integer kolicina,
			NastavnikDTORecord autorizator,
			SluzbenikStudentskeDTORecord podnosilacZahteva
			) 
	{
		 
	}
	
	


	
	
}
