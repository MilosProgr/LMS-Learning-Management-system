package ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji;

import java.util.Set;

import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO.NastavnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta.RealizacijaPredmetaDTO.RealizacijaPredmetaDTORecord;

public class NastavnikNaRealizacijiDTO {
	
	
	public record NastavnikNaRealizacijiDTORecord(
			 Long id,
			 Long brojCasova,
			
			 NastavnikDTORecord nastavnik,
			
			 Set<RealizacijaPredmetaDTORecord> realizacijaPredmeta
			) {}
	
	
	
	
	
	
	
}
