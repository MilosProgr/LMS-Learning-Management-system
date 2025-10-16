package ac.rs.singidunum.springBootApp.Features.Predmeti.PrijavljeniIspit;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Polaganja.EvaluacijaZnanja.EvaluacijaZnanjaDTO.EvaluacijaZnanjaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.IspitniRok.IspitniRokDTO.IspitniRokDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO.PredmetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.PrijavljeniIspit.PrijavljeniIspitDTO.PrijavljeniIspitDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO.StudentNaGodiniDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class PrijavljeniIspitMapper implements Mapper<PrijavljeniIspitDTORecord, PrijavljeniIspit> {

    @Override
    public PrijavljeniIspitDTORecord map(PrijavljeniIspit e) {
        if (e == null) {
            return null;
        }

        // ✅ Mapiranje EvaluacijaZnanja
        List<EvaluacijaZnanjaDTORecord> evaluacijeZnanja = null;
        if (e.getEvaluacijeZnanja() != null && !e.getEvaluacijeZnanja().isEmpty()) {
            evaluacijeZnanja = e.getEvaluacijeZnanja().stream()
                    .map(ev -> new EvaluacijaZnanjaDTORecord(
                            ev.getId(),
                            ev.getVremePocetka(),
                            ev.getVremeZavrsetka(),
                            ev.getOstvareniBodovi(),
                            null,
                            null,
                            null,
                            null
                    ))
                    .collect(Collectors.toList());
        }

        // ✅ Mapiranje StudentNaGodini
        StudentNaGodiniDTORecord studentNaGodini = null;
        if (e.getStudentNaGodini() != null) {
            studentNaGodini = new StudentNaGodiniDTORecord(
                    e.getStudentNaGodini().getId(),
                    e.getStudentNaGodini().getDatumUpisa(),
                    e.getStudentNaGodini().getBrojIndeksa(),
                    null,
                    null,
                    e.getStudentNaGodini().getProsek(),
                    null,
                    null,
                    null
            );
        }

        // ✅ Mapiranje Predmet
        PredmetDTORecord predmet = null;
        if (e.getPredmet() != null) {
            predmet = new PredmetDTORecord(
                    e.getPredmet().getId(),
                    e.getPredmet().getNaziv(),
                    e.getPredmet().getEsbn(),
                    e.getPredmet().getObavezan(),
                    e.getPredmet().getBrojPredavanja(),
                    e.getPredmet().getBrojVezbi(),
                    e.getPredmet().getDrugiObliciNastave(),
                    e.getPredmet().getIstrazivackiRad(),
                    e.getPredmet().getOstaliCasovi(), null, null, null, null, null, null
            );
        }

        // ✅ Mapiranje IspitniRok
        IspitniRokDTORecord ispitniRok = null;
        if (e.getIspitniRok() != null) {
            ispitniRok = new IspitniRokDTORecord(
                    e.getIspitniRok().getId(),
                    e.getIspitniRok().getNaziv(),
                    e.getIspitniRok().getPocetak(),
                    e.getIspitniRok().getKraj(),
                    e.getIspitniRok().getRedovan(), null
            );
        }

        // ✅ Kreiranje finalnog record DTO objekta
        return new PrijavljeniIspitDTORecord(
                e.getId(),
                e.isPrijavljen(),
                e.getBrojPrijava(),
                evaluacijeZnanja,
                studentNaGodini,
                predmet,
                ispitniRok
        );
    }

//    @Override
//    public List<PrijavljeniIspitDTORecord> map(List<PrijavljeniIspit> lista) {
//        return lista.stream()
//                .map(this::map)
//                .collect(Collectors.toList());
//    }
}
