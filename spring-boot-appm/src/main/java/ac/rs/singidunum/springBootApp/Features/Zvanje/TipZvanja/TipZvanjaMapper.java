package ac.rs.singidunum.springBootApp.Features.Zvanje.TipZvanja;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Zvanje.Zvanje.Zvanje;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TipZvanjaMapper implements Mapper<TipZvanjaDTO, TipZvanja> {

    @Override
    public TipZvanjaDTO map(TipZvanja tipZvanja) {
        if(tipZvanja == null) {
            return null;
        }
        TipZvanjaDTO dto = new TipZvanjaDTO();
        dto.setId(tipZvanja.getId());
        dto.setNaziv(tipZvanja.getNaziv());

        if (tipZvanja.getZvanja() != null) {
            Set<Long> zvanjaIds = tipZvanja.getZvanja().stream()
                    .map(Zvanje::getId)
                    .collect(Collectors.toSet());
            dto.setZvanjaIds(zvanjaIds);
        }
        return dto;
    }

    @Override
    public List<TipZvanjaDTO> map(List<TipZvanja> entities) {
        return entities.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
