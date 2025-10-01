package ac.rs.singidunum.springBootApp.Mapper.implementacija.OpsteObavestenje;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.OpsteObavestenje.OpsteObavestenjeDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.OpsteObavestenje.OpsteObavestenje;
@Component
public class OpsteObavestenjeMapper implements Mapper<OpsteObavestenjeDTO, OpsteObavestenje>{

	@Override
	public OpsteObavestenjeDTO map(OpsteObavestenje obavestenje) {
		OpsteObavestenjeDTO obavestenjeDTO =
		new OpsteObavestenjeDTO(
				obavestenje.getId(),
				obavestenje.getNaslov(),
				obavestenje.getTekst(),
				obavestenje.getDatum(),
				obavestenje.getVreme()
				);
		return obavestenjeDTO;
	}

	@Override
	public List<OpsteObavestenjeDTO> map(List<OpsteObavestenje> obavestenja) {
		return obavestenja.stream().map(this::map).collect(Collectors.toList());	
	}
	
}

