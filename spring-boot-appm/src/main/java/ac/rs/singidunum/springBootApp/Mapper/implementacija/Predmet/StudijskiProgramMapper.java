package ac.rs.singidunum.springBootApp.Mapper.implementacija.Predmet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.Predmet.StudijskiProgramDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.GodinaStudijaDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.StudentDTO;
import ac.rs.singidunum.springBootApp.Features.Fakultet.FakultetDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Predmet.StudijskiProgram;
import ac.rs.singidunum.springBootApp.Model.Student.Student;

@Component
public class StudijskiProgramMapper implements Mapper<StudijskiProgramDTO, StudijskiProgram> {

	@Override
	public StudijskiProgramDTO map(StudijskiProgram e) {
		if(e == null) {
			return null;
		}

		// Map godinaStudija safely
		GodinaStudijaDTO godinaDto = null;
		if (e.getGodinaStudija() != null) {
			godinaDto = new GodinaStudijaDTO(
					e.getGodinaStudija().getId(),
					e.getGodinaStudija().getGodina()
			);
		}

		// Create DTO with godinaStudija
		StudijskiProgramDTO sDto = new StudijskiProgramDTO(
				e.getId(),
				e.getNaziv(),
				new FakultetDTO(
						e.getFakultet().getId(),
						e.getFakultet().getNaziv(),
						e.getFakultet().getTelefon(),
						e.getFakultet().getOpis()
				),
				godinaDto
		);

		// Map predmeti
		if(e.getPredmeti() != null) {
			sDto.setPredmeti(
					e.getPredmeti().stream()
							.map(predmet -> new PredmetDTO(
									predmet.getId(),
									predmet.getNaziv(),
									predmet.getEsbn(),
									predmet.getObavezan(),
									predmet.getBrojPredavanja(),
									predmet.getBrojVezbi(),
									predmet.getDrugiObliciNastave(),
									predmet.getIstrazivackiRad(),
									predmet.getOstaliCasovi()
							))
							.collect(Collectors.toSet())
			);
		}

		// Map studenti
		if (e.getStudentiNaGodini() != null) {
			sDto.setStudenti(
					e.getStudentiNaGodini().stream()
							.map(studentNaGodini -> {
								Student s = studentNaGodini.getStudent();
								return new StudentDTO(
										s.getId(),
										s.getJmbg(),
										s.getTelefon(),
										s.getStatusStudiranja(),
										s.getStanjeNaRacunu(),
										s.isPredmetiIzabrani()
								);
							})
							.distinct()
							.collect(Collectors.toList())
			);
		}

		return sDto;
	}

	@Override
	public List<StudijskiProgramDTO> map(List<StudijskiProgram> e) {
		return e.stream().map(this::map).collect(Collectors.toList());
	}
}
