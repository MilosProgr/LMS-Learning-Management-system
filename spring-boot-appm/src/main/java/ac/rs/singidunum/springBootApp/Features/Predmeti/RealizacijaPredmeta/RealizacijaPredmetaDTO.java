package ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ac.rs.singidunum.springBootApp.Features.Nastava.TerminNastave.TerminNastaveDTO;
import ac.rs.singidunum.springBootApp.Features.Nastava.TerminNastave.TerminNastaveDTO.TerminNastaveDTORecord;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji.NastavnikNaRealizacijiDTO;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji.NastavnikNaRealizacijiDTO.NastavnikNaRealizacijiDTORecord;
import ac.rs.singidunum.springBootApp.Features.Polaganja.EvaluacijaZnanja.EvaluacijaZnanjaDTO;
import ac.rs.singidunum.springBootApp.Features.Polaganja.EvaluacijaZnanja.EvaluacijaZnanjaDTO.EvaluacijaZnanjaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO.PredmetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Semestar.SemestarDTO.SemestarDTORecord;

public class RealizacijaPredmetaDTO {

	
	public record RealizacijaPredmetaDTORecord(
			Long id,
			
			PredmetDTORecord predmet,
			
			//private PohadjanjePredmetaDTO pohadjanjePredmeta;
			
			 List<EvaluacijaZnanjaDTORecord> evaluacijaZnanja,
			
			 NastavnikNaRealizacijiDTORecord nastavnikNaRealizaciji,
			
			 List<TerminNastaveDTORecord> terminNastave,
			
//			private IspitniRokDTO rok;
		     Set<SemestarDTORecord> semestri
			) {}

	
	
	
	
	
	
}
