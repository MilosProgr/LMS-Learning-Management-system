package ac.rs.singidunum.springBootApp.Features.Polaganja.TipEvaluacije;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Polaganja.EvaluacijaZnanja.EvaluacijaZnanjaDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;

@Component
public class TipEvaluacijeMapper implements Mapper<TipEvaluacijeDTO, TipEvaluacije>{

	@Override
	public TipEvaluacijeDTO map(TipEvaluacije e) {
		if(e == null) {
			return null;
		}
		TipEvaluacijeDTO tEvDto = 
				new TipEvaluacijeDTO(e.getId(), e.getNaziv());
		
		
		return tEvDto;
	}

	@Override
	public List<TipEvaluacijeDTO> map(List<TipEvaluacije> e) {
		// TODO Auto-generated method stub
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
