package ac.rs.singidunum.springBootApp.Features.Mesto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Adresa.AdresaDTO.AdresaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Drzava.DrzavaDTO.DrzavaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Mesto.MestoDTO.MestoDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class MestoMapper implements Mapper<MestoDTORecord, Mesto> {

    @Override
    public MestoDTORecord map(Mesto e) {
        if (e == null) {
            return null;
        }

        // Map Drzava if present
        DrzavaDTORecord drzavaDTO = null;
        if (e.getDrzava() != null) {
            // Only include basic fields to avoid infinite recursion
            drzavaDTO = new DrzavaDTORecord(e.getDrzava().getId(), e.getDrzava().getNaziv(), null);
        }

        // Map Adrese if present
        List<AdresaDTORecord> adreseDTO = null;
        if (e.getAdrese() != null) {
            adreseDTO = e.getAdrese().stream()
                    .map(adresa -> new AdresaDTORecord(adresa.getId(), adresa.getUlica(), adresa.getBroj(), null))
                    .collect(Collectors.toList());
        }

        return new MestoDTORecord(e.getId(), e.getNaziv(), drzavaDTO);
    }

//    @Override
//    public List<MestoDTORecord> map(List<Mesto> e) {
//        if (e == null) {
//            return Collections.emptyList();
//        }
//        return e.stream().map(this::map).collect(Collectors.toList());
//    }
}
