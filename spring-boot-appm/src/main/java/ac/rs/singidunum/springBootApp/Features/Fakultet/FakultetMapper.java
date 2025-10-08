package ac.rs.singidunum.springBootApp.Features.Fakultet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Adresa.AdresaDTO;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgramDTO;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.Features.Univerzitet.UniverzitetDTO;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class FakultetMapper implements Mapper<FakultetDTO, Fakultet> {

	@Override
	public FakultetDTO map(Fakultet e) {
		FakultetDTO fakultetDTO =
				new FakultetDTO(
						e.getId(),
						e.getNaziv(),
						e.getTelefon(),
						e.getOpis()
						);
		
		if(e.getAdresa() != null) {
			
			fakultetDTO.setAdresa(
					new AdresaDTO(e.getAdresa().getId(),
							e.getAdresa().getUlica(),
							e.getAdresa().getBroj()
							)
					);
		}
		//ovo je da dobijemo univerzitet za jedan fakultet,vrati ce objekat
		if(e.getUniverzitet() != null) {
			
			fakultetDTO.setUniverzitet(
					
					new UniverzitetDTO(
							e.getUniverzitet().getId(),
							e.getUniverzitet().getNaziv(),
							e.getUniverzitet().getDatumOsnivanja(),
							e.getUniverzitet().getOpis()
							
							)
					);
		}
		if(e.getStudijskiProgrami() != null) {
			
			fakultetDTO.setProgrami(
					e.getProgrami().stream()
					.map(programi ->
					new StudijskiProgramDTO(programi.getId(),
							programi.getNaziv(), 
							null)
							).collect(Collectors.toList())
					
					);
		}
        if (e.getDekan() != null) {
            NastavnikDTO dekanDto = new NastavnikDTO(
                e.getDekan().getId(),
                e.getDekan().getBiografija(),
                e.getDekan().getJmbg(),
                e.getDekan().getTelefon(),
                e.getDekan().getPoslovniMail(),
                e.getDekan().getBrojSlobodnihDana(),
                e.getDekan().getBrojIskoristenihDana()
            );

            if (e.getDekan().getKorisnik() != null) {
                RegistrovaniKorisnikDTO korisnikDto = new RegistrovaniKorisnikDTO(
                    e.getDekan().getKorisnik().getId(),
                    e.getDekan().getKorisnik().getIme(),
                    e.getDekan().getKorisnik().getPrezime(),
                    e.getDekan().getKorisnik().getKorisnickoIme(),
                    null,
                    e.getDekan().getKorisnik().getEmail()
                );
                dekanDto.setKorisnik(korisnikDto);
            }

            fakultetDTO.setDekan(dekanDto);
        }
        
        return fakultetDTO;
    }

	@Override
	public List<FakultetDTO> map(List<Fakultet> e) {
		return e.stream().map(this::map).collect(Collectors.toList());

	}


}
