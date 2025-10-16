package ac.rs.singidunum.springBootApp.Features.Fakultet;

import java.util.List;

import ac.rs.singidunum.springBootApp.Features.Adresa.AdresaDTO.AdresaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO.NastavnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgramDTO.StudijskiProgramDTORecord;
import ac.rs.singidunum.springBootApp.Features.Univerzitet.UniverzitetDTO.UniverzitetDTORecord;

public class FakultetDTO {
	
	
	public record FakultetDTORecord(
			 Long id,
			 String naziv,
			 AdresaDTORecord adresa,
			 UniverzitetDTORecord univerzitet,
			
			 NastavnikDTORecord dekan,
			
			 String opis,
			
			 List<StudijskiProgramDTORecord> programi
			) {}
	
	

	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
