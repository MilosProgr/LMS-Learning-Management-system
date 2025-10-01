package ac.rs.singidunum.springBootApp.Mapper.implementacija.Adresa;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.Adresa.AdresaDTO;
import ac.rs.singidunum.springBootApp.DTO.Adresa.DrzavaDTO;
import ac.rs.singidunum.springBootApp.DTO.Adresa.MestoDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Adresa.Mesto;

@Component
public class MestoMapper implements Mapper<MestoDTO, Mesto> {

	@Override
	public MestoDTO map(Mesto e) {
		if(e == null) {
			return null;
		}
		MestoDTO mDto = new MestoDTO(e.getId(), e.getNaziv());
		
		if(e.getDrzava() != null) {
			mDto.setDrzava(
					new DrzavaDTO(e.getDrzava().getId(), e.getDrzava().getNaziv())
					);
		}
		
		if(e.getAdrese() != null) {
			mDto.setAdrese(e.getAdrese().stream().map(adrese -> new AdresaDTO(adrese.getId(), adrese.getUlica(), adrese.getBroj())).collect(Collectors.toList()));
		}
		
		
		return mDto;
	}

//	@Override
//	public List<MestoDTO> map(List<Mesto> e) {
//		return e.stream().map(this::map).collect(Collectors.toList());
//
//	}


}
