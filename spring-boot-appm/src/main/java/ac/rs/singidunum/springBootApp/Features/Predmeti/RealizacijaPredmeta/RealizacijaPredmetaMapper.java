package ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Nastava.TerminNastave.TerminNastaveDTO.TerminNastaveDTORecord;
import ac.rs.singidunum.springBootApp.Features.Nastava.TipNastave.TipNastaveDTO.TipNastaveDTORecord;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji.NastavnikNaRealizacijiDTO.NastavnikNaRealizacijiDTORecord;
import ac.rs.singidunum.springBootApp.Features.Polaganja.EvaluacijaZnanja.EvaluacijaZnanjaDTO.EvaluacijaZnanjaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod.IshodDTO.IshodDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO.PredmetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta.RealizacijaPredmetaDTO.RealizacijaPredmetaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Semestar.SemestarDTO.SemestarDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class RealizacijaPredmetaMapper implements Mapper<RealizacijaPredmetaDTORecord, RealizacijaPredmeta> {

    @Override
    public RealizacijaPredmetaDTORecord map(RealizacijaPredmeta e) {
        if (e == null) return null;

        // ✅ Predmet
        PredmetDTORecord predmet = e.getPredmet() != null
                ? new PredmetDTORecord(
                        e.getPredmet().getId(),
                        e.getPredmet().getNaziv(),
                        e.getPredmet().getEsbn(),
                        e.getPredmet().getObavezan(),
                        e.getPredmet().getBrojPredavanja(),
                        e.getPredmet().getBrojVezbi(),
                        e.getPredmet().getDrugiObliciNastave(),
                        e.getPredmet().getIstrazivackiRad(),
                        e.getPredmet().getOstaliCasovi(),
                        null,
                        null,
                        null,
                        null,
                        null
                )
                : null;

        // ✅ Nastavnik na realizaciji
        NastavnikNaRealizacijiDTORecord nastavnik = e.getNastavnikNaRealizaciji() != null
                ? new NastavnikNaRealizacijiDTORecord(
                        e.getNastavnikNaRealizaciji().getId(),
                        e.getNastavnikNaRealizaciji().getBrojCasova(), null, null
                )
                : null;

        // ✅ Evaluacije znanja
        List<EvaluacijaZnanjaDTORecord> evaluacije = e.getEvaluacijaZnanja() != null
                ? e.getEvaluacijaZnanja().stream()
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
                    .collect(Collectors.toList())
                : null;

        // ✅ Termini nastave
        List<TerminNastaveDTORecord> termini = e.getTerminNastave() != null
                ? e.getTerminNastave().stream()
                    .map(t -> new TerminNastaveDTORecord(
                            t.getId(),
                            t.getVremePocetka(),
                            t.getVremeZavrsetka(),
                            t.getIshod() != null ? new IshodDTORecord(
                                    t.getIshod().getId(),
                                    t.getIshod().getOpis(),
                                    t.getIshod().isPolozeno(),
                                    null, null
                            ) : null,
                            t.getTipNastave() != null ? new TipNastaveDTORecord(
                                    t.getTipNastave().getId(),
                                    t.getTipNastave().getNaziv()
                            ) : null,
                            null // realizacijaPredmeta referenca može biti null da se izbegne cikličnost
                    ))
                    .collect(Collectors.toList())
                : null;

        // ✅ Semestri
        Set<SemestarDTORecord> semestri = e.getSemestri() != null
                ? e.getSemestri().stream()
                    .map(s -> new SemestarDTORecord(
                            s.getId(),
                            s.getDatumPocetka(),
                            s.getDatumKraja(),
                            s.getTip()
                    ))
                    .collect(Collectors.toSet())
                : null;

        // ✅ Glavni DTO record
        return new RealizacijaPredmetaDTORecord(
                e.getId(),
                predmet,
                evaluacije,
                nastavnik,
                termini,
                semestri
        );
    }

    @Override
    public List<RealizacijaPredmetaDTORecord> map(List<RealizacijaPredmeta> e) {
        return e.stream().map(this::map).collect(Collectors.toList());
    }

    public Set<RealizacijaPredmetaDTORecord> map(Set<RealizacijaPredmeta> e) {
        return e.stream().map(this::map).collect(Collectors.toSet());
    }
}
