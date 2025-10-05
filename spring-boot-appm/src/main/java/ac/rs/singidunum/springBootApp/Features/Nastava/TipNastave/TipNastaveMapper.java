package ac.rs.singidunum.springBootApp.Features.Nastava.TipNastave;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Nastava.TerminNastave.TerminNastaveDTO;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji.NastavnikNaRealizacijiDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;

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
