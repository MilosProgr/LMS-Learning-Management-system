package ac.rs.singidunum.springBootApp.Mapper.implementacija.Adresa;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.Adresa.DrzavaDTO.DrzavaDTORecord;
import ac.rs.singidunum.springBootApp.DTO.Adresa.MestoDTO.MestoDTORecord;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Adresa.Drzava;

@Component
public class DrzavaMapper implements Mapper<DrzavaDTORecord, Drzava> {

    @Override
    public DrzavaDTORecord map(Drzava e) {
        if (e == null) {
            return null;
        }

        Set<MestoDTORecord> mestaDTO = null;
        if (e.getMesta() != null) {
            mestaDTO = e.getMesta().stream()
                    .map(mesto -> new MestoDTORecord(mesto.getId(), mesto.getNaziv(), null))
                    .collect(Collectors.toSet());
        }

        return new DrzavaDTORecord(e.getId(), e.getNaziv(), mestaDTO);
    }

//    @Override
//    public List<DrzavaDTO> map(List<Drzava> e) {
//        return e.stream().map(this::map).collect(Collectors.toList());
//    }
}
