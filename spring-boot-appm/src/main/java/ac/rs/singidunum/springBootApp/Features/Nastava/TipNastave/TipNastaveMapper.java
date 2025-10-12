package ac.rs.singidunum.springBootApp.Features.Nastava.TipNastave;

//import java.util.List;
//import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Nastava.TipNastave.TipNastaveDTO.TipNastaveDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class TipNastaveMapper implements Mapper<TipNastaveDTORecord, TipNastave> {

	@Override
	public TipNastaveDTORecord map(TipNastave e) {
		if(e == null) {
			return null;
		}
		TipNastaveDTORecord tipNastaveDto = 
				new TipNastaveDTORecord(e.getId(), e.getNaziv());
		
		
		return tipNastaveDto;
	}

	
}
