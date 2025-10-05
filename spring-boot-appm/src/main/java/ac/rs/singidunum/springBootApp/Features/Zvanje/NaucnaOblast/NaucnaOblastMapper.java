package ac.rs.singidunum.springBootApp.Features.Zvanje.NaucnaOblast;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NaucnaOblastMapper implements Mapper<NaucnaOblastDTO, NaucnaOblast> {

    @Override
    public NaucnaOblastDTO map(NaucnaOblast naucnaOblast) {
        if (naucnaOblast == null) {
            return null;
        }
        NaucnaOblastDTO dto = new NaucnaOblastDTO();
        dto.setId(naucnaOblast.getId());
        dto.setNaziv(naucnaOblast.getNaziv());

        dto.setNastavniciIds(
                naucnaOblast.getNastavnici().stream().map(
                        nastavnik -> nastavnik.getId()
                ).collect(Collectors.toSet())
        );

        return dto;
    }

    @Override
    public List<NaucnaOblastDTO> map(List<NaucnaOblast> entities) {
        return entities.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}