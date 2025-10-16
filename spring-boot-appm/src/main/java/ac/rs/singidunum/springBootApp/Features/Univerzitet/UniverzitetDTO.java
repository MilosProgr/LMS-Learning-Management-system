package ac.rs.singidunum.springBootApp.Features.Univerzitet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import ac.rs.singidunum.springBootApp.Features.Adresa.AdresaDTO;
import ac.rs.singidunum.springBootApp.Features.Adresa.AdresaDTO.AdresaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Fakultet.FakultetDTO;
import ac.rs.singidunum.springBootApp.Features.Fakultet.FakultetDTO.FakultetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO.NastavnikDTORecord;

public class UniverzitetDTO {
	
	
	public record UniverzitetDTORecord(
			 Long id,
			 String naziv,
			 String opis,
			 LocalDate datumOsnivanja,
			 List<FakultetDTORecord> fakultet,
			 AdresaDTORecord adresa,
			 NastavnikDTORecord rektor
			) {}
	
	
	
	
	
	
	
	
	
	
}
