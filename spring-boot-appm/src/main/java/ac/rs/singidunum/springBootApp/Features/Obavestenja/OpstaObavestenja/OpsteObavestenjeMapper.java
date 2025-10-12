package ac.rs.singidunum.springBootApp.Features.Obavestenja.OpstaObavestenja;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Obavestenja.OpstaObavestenja.OpsteObavestenjeDTO.OpsteObavestenjeDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class OpsteObavestenjeMapper implements Mapper<OpsteObavestenjeDTORecord, OpsteObavestenje>{

	@Override
	public OpsteObavestenjeDTORecord map(OpsteObavestenje obavestenje) {
		OpsteObavestenjeDTORecord obavestenjeDTO =
		new OpsteObavestenjeDTORecord(
				obavestenje.getId(),
				obavestenje.getNaslov(),
				obavestenje.getTekst(),
				obavestenje.getDatum(),
				obavestenje.getVreme()
				);
		return obavestenjeDTO;
	}

//	@Override
//	public List<OpsteObavestenjeDTORecord> map(List<OpsteObavestenje> obavestenja) {
//		return obavestenja.stream().map(this::map).collect(Collectors.toList());	
//	}
	
}

