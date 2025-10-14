package ac.rs.singidunum.springBootApp.Features.Rezervacija;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Rezervacija.RezervacijaDTO.RezervacijaDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RezervacijaMapper implements Mapper<RezervacijaDTORecord, Rezervacija> {

    @Override
    public RezervacijaDTORecord map(Rezervacija rezervacija) {
        if (rezervacija == null) {
            return null;
        }
        return new RezervacijaDTORecord(
                rezervacija.getId(),
                rezervacija.getOpis(),
                rezervacija.getDatumOd(),
                rezervacija.getDatumDo(),
                rezervacija.getNastavnik().getId(),
                rezervacija.getStatusOdmora()
        );
    }

//    @Override
//    public List<RezervacijaDTO> map(List<Rezervacija> e) {
//        return e.stream().map(this::map).collect(Collectors.toList());
//    }
}
