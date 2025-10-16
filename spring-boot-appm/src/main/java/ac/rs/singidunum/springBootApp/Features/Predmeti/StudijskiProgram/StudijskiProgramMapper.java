package ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Fakultet.FakultetDTO.FakultetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO.PredmetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgramDTO.StudijskiProgramDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.Student;
import ac.rs.singidunum.springBootApp.Features.Student.StudentDTO.StudentDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija.GodinaStudijaDTO.GodinaStudijaDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class StudijskiProgramMapper implements Mapper<StudijskiProgramDTORecord, StudijskiProgram> {

    @Override
    public StudijskiProgramDTORecord map(StudijskiProgram e) {
        if (e == null) return null;

        // Map predmeti
        Set<PredmetDTORecord> predmetiDTO = null;
        if (e.getPredmeti() != null && !e.getPredmeti().isEmpty()) {
            predmetiDTO = e.getPredmeti().stream()
                    .map(p -> new PredmetDTORecord(
                            p.getId(),
                            p.getNaziv(),
                            p.getEsbn(),
                            p.getObavezan(),
                            p.getBrojPredavanja(),
                            p.getBrojVezbi(),
                            p.getDrugiObliciNastave(),
                            p.getIstrazivackiRad(),
                            p.getOstaliCasovi(), null, null, null, null, null, null
                    ))
                    .collect(Collectors.toSet());
        }

        // Map studenti
        List<StudentDTORecord> studentiDTO = null;
        if (e.getStudentiNaGodini() != null && !e.getStudentiNaGodini().isEmpty()) {
            studentiDTO = e.getStudentiNaGodini().stream()
                    .map(upis -> {
                        Student s = upis.getStudent();
                        return new StudentDTORecord(
                                s.getId(),
                                s.getJmbg(),
                                s.getTelefon(),
                                s.getStatusStudiranja(),
                                s.isPredmetiIzabrani(),
                                s.getStanjeNaRacunu(), null, null, null, null, null
                        );
                    })
                    .distinct()
                    .collect(Collectors.toList());
        }

        // Map fakultet
        FakultetDTORecord fakultetDTO = e.getFakultet() != null
                ? new FakultetDTORecord(
                        e.getFakultet().getId(),
                        e.getFakultet().getNaziv(),
                        null, null, null, e.getFakultet().getOpis(), null
                )
                : null;

        // Map godina studija
        GodinaStudijaDTORecord godinaDTO = e.getGodinaStudija() != null
                ? new GodinaStudijaDTORecord(
                        e.getGodinaStudija().getId(),
                        e.getGodinaStudija().getGodina(), null, null
                )
                : null;

        return new StudijskiProgramDTORecord(
                e.getId(),
                e.getNaziv(),
                fakultetDTO,
                predmetiDTO,
                godinaDTO,
                studentiDTO
        );
    }

    @Override
    public List<StudijskiProgramDTORecord> map(List<StudijskiProgram> e) {
        if (e == null) return null;
        return e.stream().map(this::map).collect(Collectors.toList());
    }
}
