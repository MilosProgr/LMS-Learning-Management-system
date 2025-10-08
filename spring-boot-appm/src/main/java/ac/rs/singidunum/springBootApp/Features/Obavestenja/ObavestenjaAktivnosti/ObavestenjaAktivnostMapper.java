package ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class ObavestenjaAktivnostMapper implements Mapper<ObavestenjeAktivnostDTO, ObavestenjeAktivnosti> {

	@Override
	public ObavestenjeAktivnostDTO map(ObavestenjeAktivnosti e) {
		if(e == null) {
			return null;
		}
		ObavestenjeAktivnostDTO oDto = 
				new ObavestenjeAktivnostDTO(e.getId(),
						e.getVremePostavljanja(),
						e.getSadrzaj(),
						e.getNaslov(),
						e.getProcitano()
						);
		
		if(e.getRegistrovaniKorisnik() != null) {
			oDto.setRegistrovaniKorisnik(
					new RegistrovaniKorisnikDTO(e.getRegistrovaniKorisnik().getId(),
							e.getRegistrovaniKorisnik().getIme(),
							e.getRegistrovaniKorisnik().getPrezime(),
							e.getRegistrovaniKorisnik().getKorisnickoIme(),
							e.getRegistrovaniKorisnik().getLozinka(), 
							e.getRegistrovaniKorisnik().getEmail())
					);
		}
		
		return oDto;
	}

	@Override
	public List<ObavestenjeAktivnostDTO> map(List<ObavestenjeAktivnosti> e) {
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
