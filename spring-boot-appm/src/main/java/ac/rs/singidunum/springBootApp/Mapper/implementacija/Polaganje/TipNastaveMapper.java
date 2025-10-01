package ac.rs.singidunum.springBootApp.Mapper.implementacija.Polaganje;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.Nastavnici.NastavnikNaRealizacijiDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.TerminNastaveDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.TipNastaveDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Predmet.TipNastave;

@Component
public class TipNastaveMapper implements Mapper<TipNastaveDTO, TipNastave> {

	@Override
	public TipNastaveDTO map(TipNastave e) {
		if(e == null) {
			return null;
		}
		TipNastaveDTO tipNastaveDto = 
				new TipNastaveDTO(e.getId(), e.getNaziv());
		
		
		return tipNastaveDto;
	}

	@Override
	public List<TipNastaveDTO> map(List<TipNastave> e) {
		// TODO Auto-generated method stub
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
