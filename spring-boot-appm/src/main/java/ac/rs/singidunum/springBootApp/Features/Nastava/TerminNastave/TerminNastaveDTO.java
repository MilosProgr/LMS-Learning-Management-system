package ac.rs.singidunum.springBootApp.Features.Nastava.TerminNastave;

import java.time.LocalDateTime;

import ac.rs.singidunum.springBootApp.Features.Nastava.TipNastave.TipNastaveDTO.TipNastaveDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod.IshodDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod.IshodDTO.IshodDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta.RealizacijaPredmetaDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta.RealizacijaPredmetaDTO.RealizacijaPredmetaDTORecord;

public class TerminNastaveDTO {

	
	public record TerminNastaveDTORecord(
			Long id,
			LocalDateTime vremePocetka,
			LocalDateTime vremeKraja,		
			IshodDTORecord ishod,		
			TipNastaveDTORecord tipNasstave,	
			RealizacijaPredmetaDTORecord realizacijaPredmeta
			) {
		
	}
	
	
	
	
	
}
