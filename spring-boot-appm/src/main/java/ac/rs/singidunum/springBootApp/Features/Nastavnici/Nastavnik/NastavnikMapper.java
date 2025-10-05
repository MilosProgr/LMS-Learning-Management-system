package ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.Student.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.Permission;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.PermissionRepository;
import ac.rs.singidunum.springBootApp.Features.Zvanje.NaucnaOblast.NaucnaOblastDTO;
import ac.rs.singidunum.springBootApp.Features.Zvanje.Zvanje.ZvanjeDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;

@Component
public class NastavnikMapper implements Mapper<NastavnikDTO, Nastavnik> {

	@Override
	public NastavnikDTO map(Nastavnik e) {
		if(e == null) {
			return null;
		}

		NastavnikDTO nastavnikDTO =
				new NastavnikDTO(
						e.getId(), 
						e.getBiografija(),
						e.getJmbg(),
						e.getTelefon(),
						e.getPoslovniMail(),
						e.getBrojSlobodnihDana(),
						e.getBrojIskoristenihDana()
						);
		
		if(e.getNaucneOblasti()!= null) {
				nastavnikDTO.setNaucneOblasti(
			e.getNaucneOblasti().stream().map(
					naucnaOblast -> new NaucnaOblastDTO(naucnaOblast.getId(), naucnaOblast.getNaziv(), null)
			).collect(Collectors.toSet())
		);
		}
		if(e.getZvanja()!=null) {
		nastavnikDTO.setZvanja(
				e.getZvanja().stream().map(
						zvanje -> new ZvanjeDTO(zvanje.getId(), zvanje.getDatumIzbora(), zvanje.getDatumPrestanka(), null, zvanje.getNaucnaOblast().getId(), zvanje.getTipZvanja().getId())
				).collect(Collectors.toSet())
		);
		
		if(e.getKorisnik() != null) {
			nastavnikDTO.setKorisnik(
					new RegistrovaniKorisnikDTO(e.getKorisnik().getId(),
							e.getKorisnik().getIme(),
							e.getKorisnik().getPrezime(),
							e.getKorisnik().getKorisnickoIme(),
							e.getKorisnik().getLozinka(),
							e.getKorisnik().getEmail())
					);
		}
		}
		return nastavnikDTO;
	}

	@Override
	public List<NastavnikDTO> map(List<Nastavnik> e) {
		return e.stream().map(this::map).collect(Collectors.toList());
	}
}
