package ac.rs.singidunum.springBootApp.Features.Predmeti.Kurs;

import java.time.LocalDateTime;
import java.util.Set;


import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO.PredmetDTORecord;

public class KursDTO {
	
	

	
	public record KursDTORecord(
			 Long id,
			 String naziv,
			 Integer trajanje,
			 String oznaka,
			 LocalDateTime datumPocetka,
			 LocalDateTime datumKraja,
			
			
			 Set<PredmetDTORecord> predmeti
			) {}

	
	
	
}
