package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.implementacija.Predmet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet.IshodDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet.NastavniMaterijalDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.Ishod;

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
