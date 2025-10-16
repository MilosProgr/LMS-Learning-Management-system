package ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik;

import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO.PredmetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija.GodinaStudijaDTO.GodinaStudijaDTORecord;

public class UdzbenikDTO {
	
	
	public record UdzbenikDTORecord(
			 Long id,
			 GodinaStudijaDTORecord godinaStudija,
			 String naziv,
			 PredmetDTORecord predmet
			) {}
	
	
	
}
