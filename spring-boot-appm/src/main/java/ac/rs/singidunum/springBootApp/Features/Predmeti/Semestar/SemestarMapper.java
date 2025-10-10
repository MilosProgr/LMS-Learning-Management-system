package ac.rs.singidunum.springBootApp.Features.Predmeti.Semestar;

//import java.util.List;
//import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Predmeti.Semestar.SemestarDTO.SemestarDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;



@Component
public class SemestarMapper implements Mapper<SemestarDTORecord, Semestar> {

	@Override
	public SemestarDTORecord map(Semestar e) {
		if(e == null) {
			return null;
		}
		SemestarDTORecord sDto = 
				new SemestarDTORecord(e.getId(), e.getDatumPocetka(), e.getDatumKraja(),e.getTip());
		return sDto;
	}



}
