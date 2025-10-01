package ac.rs.singidunum.springBootApp.Mapper.implementacija.Polaganje;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.ObavestenjaAktivnosti.FileDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.PrijavljeniIspitDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.EvaluacijaZnanjaDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.IshodDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.TipEvaluacijeDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Predmet.EvaluacijaZnanja;

@Component
public class EvaluacijaZnanjaMapper implements Mapper<EvaluacijaZnanjaDTO, EvaluacijaZnanja> {

	@Override
	public EvaluacijaZnanjaDTO map(EvaluacijaZnanja e) {
		if(e == null) {
			return null;
		}
		EvaluacijaZnanjaDTO eDto = 
				new EvaluacijaZnanjaDTO(e.getId(),
						e.getVremePocetka(),
						e.getVremeZavrsetka(),
						e.getOstvareniBodovi());
		if(e.getTipEvaluacije() != null) {
			
			eDto.setTipEvaluacije(
					new TipEvaluacijeDTO(e.getTipEvaluacije().getId(),
							e.getTipEvaluacije().getNaziv())
					);
		}
		
//		if(e.getPolaganje() != null) {
//			
//			eDto.setPolaganje(
//					new PolaganjeDTO(e.getPolaganje().getId(),
//							e.getPolaganje().getBodovi(),
//							e.getPolaganje().getNapomena())
//					);
//		}
		if(e.getPrijavljeniIspit() != null) {
			
			eDto.setPrijavljeniIspit(
					new PrijavljeniIspitDTO(e.getPrijavljeniIspit().getId(),
							e.getPrijavljeniIspit().isPrijavljen(),
							e.getPrijavljeniIspit().getBrojPrijava())
					);
		}
		
		if(e.getIshod() != null) {
			
			eDto.setIshod(new IshodDTO(e.getIshod().getId(), e.getIshod().getOpis(), e.getIshod().isPolozeno(), null));
		}
		
		if(e.getInstrumentEvaluacije() != null) {
			
			eDto.setInstrumentEvaluacije(new FileDTO(e.getInstrumentEvaluacije().getId(), e.getInstrumentEvaluacije().getOpis(), e.getInstrumentEvaluacije().getUrl()));
		}
		
		return eDto;
	}

	@Override
	public List<EvaluacijaZnanjaDTO> map(List<EvaluacijaZnanja> e) {
		// TODO Auto-generated method stub
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
