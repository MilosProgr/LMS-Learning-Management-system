package ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.Predmet.NastavniMaterijalDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;

@Component
public class IshodMapper implements Mapper<IshodDTO, Ishod> {

	@Override
	public IshodDTO map(Ishod e) {
		if(e == null) {
			return null;
		}
		IshodDTO ishodDto = 
				new IshodDTO(e.getId(), e.getOpis(), e.isPolozeno(), new PredmetDTO(e.getPredmet().getId(),
						e.getPredmet().getNaziv(), e.getPredmet().getEsbn(), e.getPredmet().getObavezan(),
						e.getPredmet().getBrojPredavanja(), e.getPredmet().getBrojVezbi(), e.getPredmet().getDrugiObliciNastave(),
						e.getPredmet().getIstrazivackiRad(), e.getPredmet().getOstaliCasovi()));
		
		
		return ishodDto;
	}

	@Override
	public List<IshodDTO> map(List<Ishod> e) {
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
