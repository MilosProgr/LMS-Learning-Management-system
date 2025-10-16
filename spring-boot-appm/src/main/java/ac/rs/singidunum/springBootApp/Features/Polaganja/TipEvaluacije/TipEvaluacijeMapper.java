package ac.rs.singidunum.springBootApp.Features.Polaganja.TipEvaluacije;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Polaganja.TipEvaluacije.TipEvaluacijeDTO.TipEvaluacijeDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class TipEvaluacijeMapper implements Mapper<TipEvaluacijeDTORecord, TipEvaluacije>{

	@Override
	public TipEvaluacijeDTORecord map(TipEvaluacije e) {
		if(e == null) {
			return null;
		}
		TipEvaluacijeDTORecord tEvDto = 
				new TipEvaluacijeDTORecord(e.getId(), e.getNaziv());
		
		
		return tEvDto;
	}

//	@Override
//	public List<TipEvaluacijeDTORecord> map(List<TipEvaluacije> e) {
//		// TODO Auto-generated method stub
//		return e.stream().map(this::map).collect(Collectors.toList());
//	}

}
