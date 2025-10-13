package ac.rs.singidunum.springBootApp.Features.Zvanje.Zvanje;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Zvanje.Zvanje.ZvanjeDTO.ZvanjeDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

//import java.util.List;
//import java.util.stream.Collectors;

@Component
public class ZvanjeMapper implements Mapper<ZvanjeDTORecord, Zvanje> {

    @Override
    public ZvanjeDTORecord map(Zvanje entity) {
        if (entity == null) {
            return null;
        }
//        ZvanjeDTOeRecord dto = new ZvanjeDTOeRecord();
//        dto.setId(entity.getId());
//        dto.setDatumIzbora(entity.getDatumIzbora());
//        dto.setDatumPrestanka(entity.getDatumPrestanka());
//        if (entity.getNastavnik() != null) {
//            dto.setNastavnikId(entity.getNastavnik().getId());
//        }
//        if (entity.getNaucnaOblast() != null) {
//            dto.setNaucnaOblastId(entity.getNaucnaOblast().getId());
//        }
//        if (entity.getTipZvanja() != null) {
//            dto.setTipZvanjaId(entity.getTipZvanja().getId());
//        }
        return new ZvanjeDTO.ZvanjeDTORecord(
                entity.getId(),
                entity.getDatumIzbora(),
                entity.getDatumPrestanka(),
                entity.getNastavnik() != null ? entity.getNastavnik().getId() : null,
                entity.getNaucnaOblast() != null ? entity.getNaucnaOblast().getId() : null,
                entity.getTipZvanja() != null ? entity.getTipZvanja().getId() : null
            );
    }

//    @Override
//    public List<ZvanjeDTOeRecord> map(List<Zvanje> entities) {
//        return entities.stream().map(this::map).collect(Collectors.toList());
//    }
}
