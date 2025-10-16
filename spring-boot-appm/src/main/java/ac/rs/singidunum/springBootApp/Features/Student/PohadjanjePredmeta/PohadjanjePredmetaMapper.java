package ac.rs.singidunum.springBootApp.Features.Student.PohadjanjePredmeta;



import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO.PredmetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.PohadjanjePredmeta.PohadjanjePredmetaDTO.PohadjanjePredmetaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO.StudentNaGodiniDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class PohadjanjePredmetaMapper implements Mapper<PohadjanjePredmetaDTORecord, PohadjanjePredmeta> {

    @Override
    public PohadjanjePredmetaDTORecord map(PohadjanjePredmeta e) {
        if (e == null) return null;

        // Map predmet
        PredmetDTORecord predmetDTO = e.getPredmet() != null
                ? new PredmetDTORecord(
                        e.getPredmet().getId(),
                        e.getPredmet().getNaziv(),
                        e.getPredmet().getEsbn(),
                        e.getPredmet().getObavezan(),
                        e.getPredmet().getBrojPredavanja(),
                        e.getPredmet().getBrojVezbi(),
                        e.getPredmet().getDrugiObliciNastave(),
                        e.getPredmet().getIstrazivackiRad(),
                        e.getPredmet().getOstaliCasovi(), null, null, null, null, null, null
                )
                : null;

        // Map studentNaGodini
        StudentNaGodiniDTORecord studentDTO = e.getStudentNaGodini() != null
                ? new StudentNaGodiniDTORecord(
                        e.getStudentNaGodini().getId(),
                        e.getStudentNaGodini().getDatumUpisa(),
                        e.getStudentNaGodini().getBrojIndeksa(),
                        null, null, e.getStudentNaGodini().getProsek(), null, null, null
                )
                : null;

        // Kreiraj record direktno
        return new PohadjanjePredmetaDTORecord(
                e.getId(),
                e.getKonacnaOcena(),
                predmetDTO,
                studentDTO
        );
    }

//    @Override
//    public List<PohadjanjePredmetaDTORecord> map(List<PohadjanjePredmeta> e) {
//        if (e == null) return null;
//        return e.stream().map(this::map).collect(Collectors.toList());
//    }
}
