package ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO.NastavnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji.NastavnikNaRealizacijiDTO.NastavnikNaRealizacijiDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO.PredmetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta.RealizacijaPredmetaDTO.RealizacijaPredmetaDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class NastavnikNaRealizacijiMapper implements Mapper<NastavnikNaRealizacijiDTORecord, NastavnikNaRealizaciji> {

    @Override
    public NastavnikNaRealizacijiDTORecord map(NastavnikNaRealizaciji e) {
        if (e == null) return null;

        // ✅ Nastavnik
        NastavnikDTORecord nastavnik = e.getNastavnik() != null
                ? new NastavnikDTORecord(
                        e.getNastavnik().getId(),
                        e.getNastavnik().getBiografija(),
                        e.getNastavnik().getJmbg(),
                        e.getNastavnik().getTelefon(),
                        e.getNastavnik().getPoslovniMail(),
                        e.getNastavnik().getBrojSlobodnihDana(),
                        e.getNastavnik().getBrojIskoristenihDana(),
                        null,
                        null,
                        null
                )
                : null;

        // ✅ Realizacija predmeta (bez evaluacije i termina da se izbegne cikličnost)
        Set<RealizacijaPredmetaDTORecord> realizacijaPredmeta = e.getRealizacijaPredmeta() != null
                ? e.getRealizacijaPredmeta().stream()
                    .map(r -> {
                        PredmetDTORecord predmet = r.getPredmet() != null
                                ? new PredmetDTORecord(
                                        r.getPredmet().getId(),
                                        r.getPredmet().getNaziv(),
                                        r.getPredmet().getEsbn(),
                                        r.getPredmet().getObavezan(),
                                        r.getPredmet().getBrojPredavanja(),
                                        r.getPredmet().getBrojVezbi(),
                                        r.getPredmet().getDrugiObliciNastave(),
                                        r.getPredmet().getIstrazivackiRad(),
                                        r.getPredmet().getOstaliCasovi(),
                                        null,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null
                                )
                                : null;
                        return new RealizacijaPredmetaDTORecord(
                                r.getId(),
                                predmet,
                                null, // evaluacijaZnanja
                                null, // nastavnikNaRealizaciji
                                null, // terminNastave
                                null  // semestri
                        );
                    })
                    .collect(Collectors.toSet())
                : null;

        // ✅ Glavni DTO record
        return new NastavnikNaRealizacijiDTORecord(
                e.getId(),
                e.getBrojCasova(),
                nastavnik,
                realizacijaPredmeta
        );
    }
}
