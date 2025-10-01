package ac.rs.singidunum.springBootApp.Mapper.implementacija.Adresa;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.Adresa.DrzavaDTO;
import ac.rs.singidunum.springBootApp.DTO.Adresa.MestoDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Adresa.Drzava;

@Component
public class DrzavaMapper implements Mapper<DrzavaDTO, Drzava> {

	@Override
	public DrzavaDTO map(Drzava e) {
		if(e == null) {
			return null;
		}
		DrzavaDTO d = 
				new DrzavaDTO(e.getId(), e.getNaziv());
		
		if (e.getMesta() != null) {
            d.setMesta(
                e.getMesta().stream()
                .map(mesto -> new MestoDTO(mesto.getId(), mesto.getNaziv()))
                .collect(Collectors.toSet())
            );
        }
        return d;
	}

//	@Override
//	public List<DrzavaDTO> map(List<Drzava> e) {
//		return e.stream().map(this::map).collect(Collectors.toList());
//	}

}
