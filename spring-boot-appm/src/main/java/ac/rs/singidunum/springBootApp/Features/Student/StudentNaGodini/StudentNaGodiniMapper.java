package ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;



import ac.rs.singidunum.springBootApp.Features.Predmeti.PrijavljeniIspit.PrijavljeniIspitDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgramDTO;
import ac.rs.singidunum.springBootApp.Features.Student.StudentDTO;
import ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija.GodinaStudijaDTO;
import ac.rs.singidunum.springBootApp.Features.Student.PohadjanjePredmeta.PohadjanjePredmetaDTO;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;


@Component
public class StudentNaGodiniMapper implements Mapper<StudentNaGodiniDTO, StudentNaGodini> {

	@Override
	public StudentNaGodiniDTO map(StudentNaGodini e) {
		if(e == null) {
			
			return null;
		}
		StudentNaGodiniDTO sGodiniDTO =
				new StudentNaGodiniDTO(e.getId(),
						e.getDatumUpisa(),
						e.getBrojIndeksa(),
						e.getProsek());
		if(e.getGodinaStudija()!=null) {
		sGodiniDTO.setGodinaStudija(
				new GodinaStudijaDTO(e.getGodinaStudija().getId(),
						e.getGodinaStudija().getGodina())
				);
		}
		if(e.getStudent()!=null) {
			
		
		sGodiniDTO.setStudent(
				new StudentDTO(
						e.getStudent().getId(),
						e.getStudent().getJmbg(),
						e.getStudent().getTelefon(),
						e.getStudent().getStatusStudiranja(),
						e.getStudent().getStanjeNaRacunu(),
						e.getStudent().isPredmetiIzabrani())
				);
		}
		
		if(e.getStudijskiProgram() != null) {
		sGodiniDTO.setStudijskiProgram(
				new StudijskiProgramDTO(e.getStudijskiProgram().getId(),
						e.getStudijskiProgram().getNaziv(), null)
				);
	}
		
		if(e.getPrijavljenIspit() != null) {
			sGodiniDTO.setPrijavljenIspit(
					e.getPrijavljenIspit().stream()
							.map(
									prijavljenIspit ->
											new PrijavljeniIspitDTO(prijavljenIspit.getId(),
													prijavljenIspit.isPrijavljen(),
													prijavljenIspit.getBrojPrijava())
							).collect(Collectors.toList())
			);
		}
		
		if (e.getPohadjanja() != null) {
			sGodiniDTO.setPohadjanja(
					e.getPohadjanja().stream().map(
							pohadjanja ->
							new PohadjanjePredmetaDTO(
									pohadjanja.getId(),
									pohadjanja.getKonacnaOcena())
							).collect(Collectors.toList())
					);
		}
		return sGodiniDTO;
	}

	@Override
	public List<StudentNaGodiniDTO> map(List<StudentNaGodini> e) {
		return e.stream().map(this::map).collect(Collectors.toList());
	}
	
	

}
