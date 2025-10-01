package ac.rs.singidunum.springBootApp.Mapper.implementacija.Zvanje;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.Zvanje.ZvanjeDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Zvanje.Zvanje;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ZvanjeMapper implements Mapper<ZvanjeDTO, Zvanje> {

    @Override
    public ZvanjeDTO map(Zvanje entity) {
        if (entity == null) {
            return null;
        }
        ZvanjeDTO dto = new ZvanjeDTO();
        dto.setId(entity.getId());
        dto.setDatumIzbora(entity.getDatumIzbora());
        dto.setDatumPrestanka(entity.getDatumPrestanka());
        if (entity.getNastavnik() != null) {
            dto.setNastavnikId(entity.getNastavnik().getId());
        }
        if (entity.getNaucnaOblast() != null) {
            dto.setNaucnaOblastId(entity.getNaucnaOblast().getId());
        }
        if (entity.getTipZvanja() != null) {
            dto.setTipZvanjaId(entity.getTipZvanja().getId());
        }
        return dto;
    }

    @Override
    public List<ZvanjeDTO> map(List<Zvanje> entities) {
        return entities.stream().map(this::map).collect(Collectors.toList());
    }
}
