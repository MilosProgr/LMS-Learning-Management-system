package ac.rs.singidunum.springBootApp.Features.Student.PohadjanjePredmeta;


import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO.PredmetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO.StudentNaGodiniDTORecord;

public class PohadjanjePredmetaDTO {

	public record PohadjanjePredmetaDTORecord(
			 Long id,
			 Long konacnaOcena,
			
			 PredmetDTORecord predmet,
			 StudentNaGodiniDTORecord studentNaGodini
			) {}
//	public RealizacijaPredmetaDTO getRealizacijaPredmeta() {
//		return realizacijaPredmeta;
//	}
//
//	public void setRealizacijaPredmeta(RealizacijaPredmetaDTO realizacijaPredmeta) {
//		this.realizacijaPredmeta = realizacijaPredmeta;
//	}
	

	
	
	
	
	
}
