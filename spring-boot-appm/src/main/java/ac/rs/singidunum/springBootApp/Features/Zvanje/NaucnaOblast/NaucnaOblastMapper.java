package ac.rs.singidunum.springBootApp.Features.Zvanje.NaucnaOblast;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.Nastavnik;
import ac.rs.singidunum.springBootApp.Features.Zvanje.NaucnaOblast.NaucnaOblastDTO.NaucnaOblastDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NaucnaOblastMapper implements Mapper<NaucnaOblastDTORecord, NaucnaOblast> {

    @Override
    public NaucnaOblastDTORecord map(NaucnaOblast naucnaOblast) {
        if (naucnaOblast == null) {
            return null;
        }
//        NaucnaOblastDTORecord dto = new NaucnaOblastDTORecord();
//        dto.setId(naucnaOblast.getId());
//        dto.setNaziv(naucnaOblast.getNaziv());
//
//        dto.setNastavniciIds(
//                naucnaOblast.getNastavnici().stream().map(
//                        nastavnik -> nastavnik.getId()
//                ).collect(Collectors.toSet())
//        );
        // Pretvaranje liste nastavnika u set ID-jeva
        Set<Long> nastavniciIds = null;
        if (naucnaOblast.getNastavnici() != null) {
            nastavniciIds = naucnaOblast.getNastavnici()
                    .stream()
                    .map(Nastavnik::getId)
                    .collect(Collectors.toSet());
        }

        return new NaucnaOblastDTORecord(
                naucnaOblast.getId(),
                naucnaOblast.getNaziv(),
                nastavniciIds
        );
    }

//    @Override
//    public List<NaucnaOblastDTORecord> map(List<NaucnaOblast> entities) {
//        return entities.stream()
//                .map(this::map)
//                .collect(Collectors.toList());
//    }
}