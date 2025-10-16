package ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ac.rs.singidunum.springBootApp.Features.Predmeti.PrijavljeniIspit.PrijavljeniIspitDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.PrijavljeniIspit.PrijavljeniIspitDTO.PrijavljeniIspitDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgramDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgramDTO.StudijskiProgramDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentDTO;
import ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija.GodinaStudijaDTO;
import ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija.GodinaStudijaDTO.GodinaStudijaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.PohadjanjePredmeta.PohadjanjePredmetaDTO;
import ac.rs.singidunum.springBootApp.Features.Student.PohadjanjePredmeta.PohadjanjePredmetaDTO.PohadjanjePredmetaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentDTO.StudentDTORecord;


public class StudentNaGodiniDTO {
	
	public record StudentNaGodiniDTORecord(
			 Long id,
			 LocalDateTime datumUpisa,
			 String brojIndeksa,
			 StudentDTORecord student,
			 GodinaStudijaDTORecord godinaStudija,
			 Double prosek,
			
			 List<PrijavljeniIspitDTORecord> prijavljenIspit,
			 StudijskiProgramDTORecord studijskiProgram,
			 List<PohadjanjePredmetaDTORecord> pohadjanja
			) {}
	
	
	
	
	


}
