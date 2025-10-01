package ac.rs.singidunum.springBootApp.Mapper.implementacija.Predmet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.predmet.SemestarDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Predmet.Semestar;

@Component
public class SemestarMapper implements Mapper<SemestarDTO, Semestar> {

	@Override
	public SemestarDTO map(Semestar e) {
		if(e == null) {
			return null;
		}
		SemestarDTO sDto = 
				new SemestarDTO(e.getId(),e.getTip(), e.getDatumPocetka(), e.getDatumKraja());
		return sDto;
	}

	@Override
	public List<SemestarDTO> map(List<Semestar> e) {
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
