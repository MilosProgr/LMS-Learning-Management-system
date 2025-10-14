package ac.rs.singidunum.springBootApp.Features.Zvanje.TipZvanja;

import org.springframework.stereotype.Component;
import ac.rs.singidunum.springBootApp.Features.Zvanje.Zvanje.Zvanje;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Features.Zvanje.TipZvanja.TipZvanjaDTO.TipZvanjaDTORecord;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TipZvanjaMapper implements Mapper<TipZvanjaDTORecord, TipZvanja> {

    @Override
    public TipZvanjaDTORecord map(TipZvanja tipZvanja) {
        if (tipZvanja == null) {
            return null;
        }

        Set<Long> zvanjaIds = null;
        if (tipZvanja.getZvanja() != null) {
            zvanjaIds = tipZvanja.getZvanja().stream()
                    .map(Zvanje::getId)
                    .collect(Collectors.toSet());
        }

        return new TipZvanjaDTORecord(
                tipZvanja.getId(),
                tipZvanja.getNaziv(),
                zvanjaIds
        );
    }

    @Override
    public List<TipZvanjaDTORecord> map(List<TipZvanja> entities) {
        return entities.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
