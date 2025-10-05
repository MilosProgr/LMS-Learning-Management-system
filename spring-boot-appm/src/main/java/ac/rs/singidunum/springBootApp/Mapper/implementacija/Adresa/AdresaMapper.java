package ac.rs.singidunum.springBootApp.Mapper.implementacija.Adresa;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.Adresa.AdresaDTO.AdresaDTORecord;
import ac.rs.singidunum.springBootApp.DTO.Adresa.MestoDTO.MestoDTORecord;
import ac.rs.singidunum.springBootApp.Features.Adresa;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;

@Component
public class AdresaMapper implements Mapper<AdresaDTORecord, Adresa> {

    @Override
    public AdresaDTORecord map(Adresa e) {
        if (e == null) {
            return null;
        }

        MestoDTORecord mestoDTO = null;
        if (e.getMesto() != null) {
            mestoDTO = new MestoDTORecord(e.getMesto().getId(), e.getMesto().getNaziv(), null);
        }

        return new AdresaDTORecord(e.getId(), e.getUlica(), e.getBroj(), mestoDTO);
    }

//    @Override
//    public List<AdresaDTORecord> map(List<Adresa> e) {
//        if (e == null) {
//            return Collections.emptyList();
//        }
//        return e.stream().map(this::map).collect(Collectors.toList());
//    }
}
