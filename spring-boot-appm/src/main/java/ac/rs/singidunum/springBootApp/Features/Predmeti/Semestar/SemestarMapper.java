package ac.rs.singidunum.springBootApp.Features.Predmeti.Semestar;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;



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
