package ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;


import ac.rs.singidunum.springBootApp.Features.Fakultet.FakultetDTO;
import ac.rs.singidunum.springBootApp.Features.Fakultet.FakultetDTO.FakultetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgramDTO.StudijskiProgramDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.Student;
import ac.rs.singidunum.springBootApp.Features.Student.StudentDTO;
import ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija.GodinaStudijaDTO;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;


@Component
public class StudijskiProgramMapper implements Mapper<StudijskiProgramDTORecord, StudijskiProgram> {

	@Override
	public StudijskiProgramDTORecord map(StudijskiProgram e) {
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
		StudijskiProgramDTORecord sDto = new StudijskiProgramDTORecord(
				e.getId(),
				e.getNaziv(),
				new FakultetDTORecord(
						e.getFakultet().getId(),
						e.getFakultet().getNaziv(),
						null,
						null,
						null, e.getFakultet().getOpis(), null
				),
				null, godinaDto, null
		);

		// Map predmeti
//		if(e.getPredmeti() != null) {
//			sDto.setPredmeti(
//					e.getPredmeti().stream()
//							.map(predmet -> new PredmetDTO(
//									predmet.getId(),
//									predmet.getNaziv(),
//									predmet.getEsbn(),
//									predmet.getObavezan(),
//									predmet.getBrojPredavanja(),
//									predmet.getBrojVezbi(),
//									predmet.getDrugiObliciNastave(),
//									predmet.getIstrazivackiRad(),
//									predmet.getOstaliCasovi()
//							))
//							.collect(Collectors.toSet())
//			);
//		}

		// Map studenti
//		if (e.getStudentiNaGodini() != null) {
//			sDto.setStudenti(
//					e.getStudentiNaGodini().stream()
//							.map(studentNaGodini -> {
//								Student s = studentNaGodini.getStudent();
//								return new StudentDTO(
//										s.getId(),
//										s.getJmbg(),
//										s.getTelefon(),
//										s.getStatusStudiranja(),
//										s.getStanjeNaRacunu(),
//										s.isPredmetiIzabrani()
//								);
//							})
//							.distinct()
//							.collect(Collectors.toList())
//			);
//		}

		return sDto;
	}

//	@Override
//	public List<StudijskiProgramDTORecord> map(List<StudijskiProgram> e) {
//		return e.stream().map(this::map).collect(Collectors.toList());
//	}
}
