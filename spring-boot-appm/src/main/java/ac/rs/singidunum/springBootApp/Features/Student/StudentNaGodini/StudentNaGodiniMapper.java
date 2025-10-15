package ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Predmeti.PrijavljeniIspit.PrijavljeniIspitDTO.PrijavljeniIspitDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgramDTO.StudijskiProgramDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentDTO.StudentDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija.GodinaStudijaDTO.GodinaStudijaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.PohadjanjePredmeta.PohadjanjePredmetaDTO.PohadjanjePredmetaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO.StudentNaGodiniDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class StudentNaGodiniMapper implements Mapper<StudentNaGodiniDTORecord, StudentNaGodini> {

    @Override
    public StudentNaGodiniDTORecord map(StudentNaGodini e) {
        if (e == null) {
            return null;
        }

        // ✅ Mapiranje osnovnih veza
        GodinaStudijaDTORecord godinaStudija = null;
        if (e.getGodinaStudija() != null) {
            godinaStudija = new GodinaStudijaDTORecord(
                    e.getGodinaStudija().getId(),
                    e.getGodinaStudija().getGodina(), null, null
            );
        }

        StudentDTORecord student = null;
        if (e.getStudent() != null) {
            student = new StudentDTORecord(
                    e.getStudent().getId(),
                    e.getStudent().getJmbg(),
                    e.getStudent().getTelefon(),
                    e.getStudent().getStatusStudiranja(),
                    e.getStudent().isPredmetiIzabrani(),
                    e.getStudent().getStanjeNaRacunu(),
                    null,
                    null,
                    null,
                    null, 
                    null
            );
        }

        StudijskiProgramDTORecord studijskiProgram = null;
        if (e.getStudijskiProgram() != null) {
            studijskiProgram = new StudijskiProgramDTORecord(
                    e.getStudijskiProgram().getId(),
                    e.getStudijskiProgram().getNaziv(),
                    null, null, null, null
            );
        }

        // ✅ Kolekcije
        List<PrijavljeniIspitDTORecord> prijavljeniIspit = null;
        if (e.getPrijavljenIspit() != null) {
            prijavljeniIspit = e.getPrijavljenIspit().stream()
                    .map(pi -> new PrijavljeniIspitDTORecord(
                            pi.getId(),
                            pi.isPrijavljen(),
                            pi.getBrojPrijava(), null, null, null, null
                    ))
                    .collect(Collectors.toList());
        }

        List<PohadjanjePredmetaDTORecord> pohadjanja = null;
        if (e.getPohadjanja() != null) {
            pohadjanja = e.getPohadjanja().stream()
                    .map(p -> new PohadjanjePredmetaDTORecord(
                            p.getId(),
                            p.getKonacnaOcena(), null, null
                    ))
                    .collect(Collectors.toList());
        }

        // ✅ Kreiranje finalnog record DTO objekta
        return new StudentNaGodiniDTORecord(
                e.getId(),
                e.getDatumUpisa(),
                e.getBrojIndeksa(),
                student,
                godinaStudija,
                e.getProsek(),
                prijavljeniIspit,
                studijskiProgram,
                pohadjanja
        );
    }

//    @Override
//    public List<StudentNaGodiniDTORecord> map(List<StudentNaGodini> lista) {
//        return lista.stream()
//                .map(this::map)
//                .collect(Collectors.toList());
//    }
}
