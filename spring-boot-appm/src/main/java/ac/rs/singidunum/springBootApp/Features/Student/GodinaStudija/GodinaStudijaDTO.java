package ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija;
//GodinaStudija moze imati vise studente na godini

import java.util.List;
import java.util.Set;

import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO.PredmetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO.StudentNaGodiniDTORecord;

public class GodinaStudijaDTO {
	
	
	public record GodinaStudijaDTORecord(
			 Long id,
			 Integer godina,
			
			
			 Set<StudentNaGodiniDTORecord> studentiNaGodini,
			
			 List<PredmetDTORecord> predmeti
			) {}
	
	
	
	
}
