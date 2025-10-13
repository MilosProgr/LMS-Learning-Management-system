package ac.rs.singidunum.springBootApp.Features.Univerzitet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Adresa.AdresaDTO;
import ac.rs.singidunum.springBootApp.Features.Fakultet.FakultetDTO;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO.NastavnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class UniverzitetMapper implements Mapper<UniverzitetDTO, Univerzitet> {

	@Override
	public UniverzitetDTO map(Univerzitet e) {
		if (e == null) {
			return null;
		}
		UniverzitetDTO uDto = new UniverzitetDTO(e.getId(), e.getNaziv(), e.getDatumOsnivanja(), e.getOpis());

		if (e.getAdresa() != null) {
			uDto.setAdresa(new AdresaDTO(e.getAdresa().getId(), e.getAdresa().getUlica(), e.getAdresa().getBroj()));
		}

		if (e.getRektor() != null) {
			NastavnikDTORecord nastavnikDto = new NastavnikDTORecord(e.getRektor().getId(),
					e.getRektor().getBiografija(), e.getRektor().getJmbg(), e.getRektor().getTelefon(),
					e.getRektor().getPoslovniMail(), e.getRektor().getBrojSlobodnihDana(),
					e.getRektor().getBrojIskoristenihDana(), null, null, null);

			if (e.getRektor().getKorisnik() != null) {
				RegistrovaniKorisnikDTO korisnikDto = new RegistrovaniKorisnikDTO(e.getRektor().getKorisnik().getId(),
						e.getRektor().getKorisnik().getIme(), e.getRektor().getKorisnik().getPrezime(),
						e.getRektor().getKorisnik().getKorisnickoIme(), null, e.getRektor().getKorisnik().getEmail());
//	            nastavnikDto.setKorisnik(korisnikDto);
			}

//	        uDto.setRektor(nastavnikDto);
		}

		if (e.getFakulteti() != null) {
			uDto.setFakultet(e.getFakulteti().stream().map(fakulteti -> new FakultetDTO(fakulteti.getId(),
					fakulteti.getNaziv(), fakulteti.getTelefon(), fakulteti.getOpis())).collect(Collectors.toList()));
		}

		return uDto;
	}

	@Override
	public List<UniverzitetDTO> map(List<Univerzitet> e) {
		return e.stream().map(this::map).collect(Collectors.toList());

	}

}
