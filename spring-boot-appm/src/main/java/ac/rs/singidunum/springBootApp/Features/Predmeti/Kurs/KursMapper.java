package ac.rs.singidunum.springBootApp.Features.Predmeti.Kurs;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Predmeti.Kurs.KursDTO.KursDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO.PredmetDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class KursMapper implements Mapper<KursDTORecord, Kurs> {

    @Override
    public KursDTORecord map(Kurs e) {
        if (e == null) {
            return null;
        }

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
                            p.getOstaliCasovi(), null, null, null, null, null,null
                    ))
                    .collect(Collectors.toSet());
        }

        return new KursDTORecord(
                e.getId(),
                e.getNaziv(),
                e.getTrajanje(),
                e.getOznaka(),
                e.getDatumPocetka(),
                e.getDatumKraja(),
                predmetiDTO
        );
    }

    @Override
    public List<KursDTORecord> map(List<Kurs> e) {
        return e.stream().map(this::map).collect(Collectors.toList());
    }
}
