package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.implementacija.Rezervacija;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Rezervacija.RezervacijaDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Rezervacija.Rezervacija;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RezervacijaMapper implements Mapper<RezervacijaDTO, Rezervacija> {

    @Override
    public RezervacijaDTO map(Rezervacija rezervacija) {
        if (rezervacija == null) {
            return null;
        }
        return new RezervacijaDTO(
                rezervacija.getId(),
                rezervacija.getOpis(),
                rezervacija.getDatumOd(),
                rezervacija.getDatumDo(),
                rezervacija.getNastavnik().getId(),
                rezervacija.getStatusOdmora()
        );
    }

    @Override
    public List<RezervacijaDTO> map(List<Rezervacija> e) {
        return e.stream().map(this::map).collect(Collectors.toList());
    }
}
