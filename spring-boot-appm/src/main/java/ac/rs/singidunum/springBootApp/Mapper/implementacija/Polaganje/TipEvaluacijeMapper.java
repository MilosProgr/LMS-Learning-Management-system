package ac.rs.singidunum.springBootApp.Mapper.implementacija.Polaganje;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.predmet.EvaluacijaZnanjaDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.TipEvaluacijeDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Predmet.TipEvaluacije;

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
