package ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO.NastavnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Zvanje.NaucnaOblast.NaucnaOblastDTO.NaucnaOblastDTORecord;
import ac.rs.singidunum.springBootApp.Features.Zvanje.Zvanje.ZvanjeDTO.ZvanjeDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class NastavnikMapper implements Mapper<NastavnikDTORecord, Nastavnik> {

    @Override
    public NastavnikDTORecord map(Nastavnik e) {
        if (e == null) {
            return null;
        }

        // Mapiramo korisnika
        RegistrovaniKorisnikDTORecord korisnikDTO = null;
        if (e.getKorisnik() != null) {
            korisnikDTO = new RegistrovaniKorisnikDTORecord(
                    e.getKorisnik().getId(),
                    e.getKorisnik().getIme(),
                    e.getKorisnik().getPrezime(),
                    e.getKorisnik().getKorisnickoIme(),
                    e.getKorisnik().getLozinka(),
                    e.getKorisnik().getEmail(),
                    null,
                    null
            );
        }

        // Mapiramo zvanja
        var zvanjaDTO = e.getZvanja() != null
                ? e.getZvanja().stream()
                        .map(zvanje -> new ZvanjeDTORecord(
                                zvanje.getId(),
                                zvanje.getDatumIzbora(),
                                zvanje.getDatumPrestanka(),
                                null,
                                zvanje.getNaucnaOblast() != null ? zvanje.getNaucnaOblast().getId() : null,
                                zvanje.getTipZvanja() != null ? zvanje.getTipZvanja().getId() : null
                        ))
                        .collect(Collectors.toSet())
                : null;

        // Mapiramo naučne oblasti
        var naucneOblastiDTO = e.getNaucneOblasti() != null
                ? e.getNaucneOblasti().stream()
                        .map(naucnaOblast -> new NaucnaOblastDTORecord(
                                naucnaOblast.getId(),
                                naucnaOblast.getNaziv(),
                                null
                        ))
                        .collect(Collectors.toSet())
                : null;

        // Vraćamo record (nema settera!)
        return new NastavnikDTORecord(
                e.getId(),
                e.getBiografija(),
                e.getJmbg(),
                e.getTelefon(),
                e.getPoslovniMail(),
                e.getBrojSlobodnihDana(),
                e.getBrojIskoristenihDana(),
                korisnikDTO,
                zvanjaDTO,
                naucneOblastiDTO
        );
    }

//    @Override
//    public List<NastavnikDTORecord> map(List<Nastavnik> e) {
//        return e.stream().map(this::map).collect(Collectors.toList());
//    }
}
