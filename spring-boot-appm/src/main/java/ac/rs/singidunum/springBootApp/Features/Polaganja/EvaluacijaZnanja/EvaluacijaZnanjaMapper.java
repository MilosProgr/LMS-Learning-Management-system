package ac.rs.singidunum.springBootApp.Features.Polaganja.EvaluacijaZnanja;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Obavestenja.File.FileDTO.FileDTORecord;
import ac.rs.singidunum.springBootApp.Features.Polaganja.EvaluacijaZnanja.EvaluacijaZnanjaDTO.EvaluacijaZnanjaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Polaganja.TipEvaluacije.TipEvaluacijeDTO.TipEvaluacijeDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod.IshodDTO.IshodDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.PrijavljeniIspit.PrijavljeniIspitDTO.PrijavljeniIspitDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class EvaluacijaZnanjaMapper implements Mapper<EvaluacijaZnanjaDTORecord, EvaluacijaZnanja> {

	@Override
	public EvaluacijaZnanjaDTORecord map(EvaluacijaZnanja e) {
		if(e == null) {
			return null;
		}
		
		 IshodDTORecord ishod = null;
	        if (e.getIshod() != null) {
	            ishod = new IshodDTORecord(
	                    e.getIshod().getId(),
	                    e.getIshod().getOpis(),
	                    e.getIshod().isPolozeno(),
	                    null
	            );
	        }

	        FileDTORecord instrumentEvaluacije = null;
	        if (e.getInstrumentEvaluacije() != null) {
	            instrumentEvaluacije = new FileDTORecord(
	                    e.getInstrumentEvaluacije().getId(),
	                    e.getInstrumentEvaluacije().getOpis(),
	                    e.getInstrumentEvaluacije().getUrl()
	            );
	        }

	        TipEvaluacijeDTORecord tipEvaluacije = null;
	        if (e.getTipEvaluacije() != null) {
	            tipEvaluacije = new TipEvaluacijeDTORecord(
	                    e.getTipEvaluacije().getId(),
	                    e.getTipEvaluacije().getNaziv()
	            );
	        }

	        PrijavljeniIspitDTORecord prijavljeniIspit = null;
	        if (e.getPrijavljeniIspit() != null) {
	            prijavljeniIspit = new PrijavljeniIspitDTORecord(
	                    e.getPrijavljeniIspit().getId(),
	                    e.getPrijavljeniIspit().isPrijavljen(),
	                    e.getPrijavljeniIspit().getBrojPrijava(), 
	                    null, 
	                    null, 
	                    null, 
	                    null
	            );
	        }
//		
		EvaluacijaZnanjaDTORecord eDto = 
				new EvaluacijaZnanjaDTORecord(e.getId(),
						e.getVremePocetka(),
						e.getVremeZavrsetka(),
						e.getOstvareniBodovi(), 
						ishod,
			            instrumentEvaluacije,
			            tipEvaluacije,
			            prijavljeniIspit);
		return eDto;
	}



}
