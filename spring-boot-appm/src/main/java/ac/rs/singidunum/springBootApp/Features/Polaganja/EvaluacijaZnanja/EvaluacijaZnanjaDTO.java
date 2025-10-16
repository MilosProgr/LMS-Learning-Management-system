package ac.rs.singidunum.springBootApp.Features.Polaganja.EvaluacijaZnanja;

import java.time.LocalDateTime;

import ac.rs.singidunum.springBootApp.Features.Obavestenja.File.FileDTO;
import ac.rs.singidunum.springBootApp.Features.Obavestenja.File.FileDTO.FileDTORecord;
import ac.rs.singidunum.springBootApp.Features.Polaganja.TipEvaluacije.TipEvaluacijeDTO;
import ac.rs.singidunum.springBootApp.Features.Polaganja.TipEvaluacije.TipEvaluacijeDTO.TipEvaluacijeDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod.IshodDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod.IshodDTO.IshodDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.PrijavljeniIspit.PrijavljeniIspitDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.PrijavljeniIspit.PrijavljeniIspitDTO.PrijavljeniIspitDTORecord;

public class EvaluacijaZnanjaDTO {
	
	public record EvaluacijaZnanjaDTORecord(
			 Long id,
			
			 LocalDateTime vremePocetka,
			 LocalDateTime vremeZavrsetka,
			 Long ostvareniBodovi,
			
			 IshodDTORecord ishod,
			 FileDTORecord instrumentEvaluacije,
			
			 TipEvaluacijeDTORecord tipEvaluacije,
						
			 PrijavljeniIspitDTORecord prijavljenIspit
			) {}
	
	
	
	
}
