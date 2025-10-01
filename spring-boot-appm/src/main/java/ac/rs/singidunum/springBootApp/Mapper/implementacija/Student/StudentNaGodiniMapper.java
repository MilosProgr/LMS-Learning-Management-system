package ac.rs.singidunum.springBootApp.Mapper.implementacija.Student;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.Nastavnici.NastavnikDTO;
import ac.rs.singidunum.springBootApp.DTO.Nastavnici.NastavnikNaRealizacijiDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.GodinaStudijaDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.PohadjanjePredmetaDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.PrijavljeniIspitDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.StudentDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.StudentNaGodiniDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.RealizacijaPredmetaDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.StudijskiProgramDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Student.StudentNaGodini;

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
