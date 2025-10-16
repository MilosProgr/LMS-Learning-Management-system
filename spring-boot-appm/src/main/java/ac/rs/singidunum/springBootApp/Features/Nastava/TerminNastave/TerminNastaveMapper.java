package ac.rs.singidunum.springBootApp.Features.Nastava.TerminNastave;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Nastava.TerminNastave.TerminNastaveDTO.TerminNastaveDTORecord;
import ac.rs.singidunum.springBootApp.Features.Nastava.TipNastave.TipNastaveDTO.TipNastaveDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod.IshodDTO.IshodDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO.PredmetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta.RealizacijaPredmetaDTO.RealizacijaPredmetaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji.NastavnikNaRealizacijiDTO.NastavnikNaRealizacijiDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class TerminNastaveMapper implements Mapper<TerminNastaveDTORecord, TerminNastave> {

    @Override
    public TerminNastaveDTORecord map(TerminNastave e) {
        if (e == null) return null;

        // ✅ Mapiranje TipNastave
        TipNastaveDTORecord tipNastave = e.getTipNastave() != null
                ? new TipNastaveDTORecord(
                        e.getTipNastave().getId(),
                        e.getTipNastave().getNaziv()
                )
                : null;

        // ✅ Mapiranje Ishoda
        IshodDTORecord ishod = e.getIshod() != null
                ? new IshodDTORecord(
                        e.getIshod().getId(),
                        e.getIshod().getOpis(),
                        e.getIshod().isPolozeno(),
                        null
                )
                : null;

        // ✅ Mapiranje Realizacije Predmeta (uključuje predmet i nastavnika)
        RealizacijaPredmetaDTORecord realizacijaPredmeta = null;
        if (e.getRealizacijaPredmeta() != null) {
            var rp = e.getRealizacijaPredmeta();

            PredmetDTORecord predmet = rp.getPredmet() != null
                    ? new PredmetDTORecord(
                            rp.getPredmet().getId(),
                            rp.getPredmet().getNaziv(),
                            rp.getPredmet().getEsbn(),
                            rp.getPredmet().getObavezan(),
                            rp.getPredmet().getBrojPredavanja(),
                            rp.getPredmet().getBrojVezbi(),
                            rp.getPredmet().getDrugiObliciNastave(),
                            rp.getPredmet().getIstrazivackiRad(),
                            rp.getPredmet().getOstaliCasovi(),
                            null, null, null, null, null,null
                    )
                    : null;

            NastavnikNaRealizacijiDTORecord nastavnik = rp.getNastavnikNaRealizaciji() != null
                    ? new NastavnikNaRealizacijiDTORecord(
                            rp.getNastavnikNaRealizaciji().getId(),
                            rp.getNastavnikNaRealizaciji().getBrojCasova(), null, null
                    )
                    : null;

//            realizacijaPredmeta = new RealizacijaPredmetaDTORecord(
//                    rp.getId(),
//                    rp.getDatumPocetka(),
//                    rp.getDatumZavrsetka(),
//                    predmet,
//                    nastavnik,
//                    null
           // );
        }

        // ✅ Glavni DTO record
        return new TerminNastaveDTORecord(
                e.getId(),
                e.getVremePocetka(),
                e.getVremeZavrsetka(),
                ishod,
                tipNastave,
                null
        );
    }

    @Override
    public List<TerminNastaveDTORecord> map(List<TerminNastave> e) {
        return e.stream().map(this::map).collect(Collectors.toList());
    }
}
