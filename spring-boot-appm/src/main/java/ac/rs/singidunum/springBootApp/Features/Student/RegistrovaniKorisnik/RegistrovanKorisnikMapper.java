package ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti.ObavestenjeAktivnostDTO;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.PermissionDTO.PermissionDTORecord;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermissionDTO.UserPermissionDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class RegistrovanKorisnikMapper implements Mapper<RegistrovaniKorisnikDTORecord, RegistrovaniKorisnik> {

    @Override
    public RegistrovaniKorisnikDTORecord map(RegistrovaniKorisnik korisnik) {
        if (korisnik == null) {
            return null;
        }

        // Mapiranje setova prava pristupa korisnika
        Set<UserPermissionDTORecord> pravaPristupaSet = (korisnik.getPravoPristupa() != null) ?
                korisnik.getPravoPristupa().stream()
                        .map(prava -> new UserPermissionDTORecord(
                                prava.getId(),
                                new PermissionDTORecord(
                                        prava.getPermission().getId(),
                                        prava.getPermission().getIme(),
                                        Set.of() // Može biti prazno, ili možeš rekurzivno mapirati prava
                                ),
                                null, // registrovani korisnik se ne mapira unutar prava pristupa da ne bi bilo kružnog reference
                                Set.of()
                        ))
                        .collect(Collectors.toSet())
                : Set.of();

        // Mapiranje setova obaveštenja aktivnosti
        Set<ObavestenjeAktivnostDTO> obavestenjaSet = (korisnik.getObavestenjaAktivnosti() != null) ?
                korisnik.getObavestenjaAktivnosti().stream()
                        .map(ob -> new ObavestenjeAktivnostDTO(
                                ob.getId(),
                                ob.getVremePostavljanja(),
                                ob.getSadrzaj(),
                                ob.getNaslov(),
                                ob.getProcitano()
                        ))
                        .collect(Collectors.toSet())
                : Set.of();

        // Kreiranje RegistrovaniKorisnikDTORecord
        return new RegistrovaniKorisnikDTORecord(
                korisnik.getId(),
                korisnik.getIme(),
                korisnik.getPrezime(),
                korisnik.getKorisnickoIme(),
                korisnik.getLozinka(),
                korisnik.getEmail(),
                pravaPristupaSet,
                obavestenjaSet
        );
    }

    @Override
    public List<RegistrovaniKorisnikDTORecord> map(List<RegistrovaniKorisnik> korisnici) {
        return korisnici.stream().map(this::map).collect(Collectors.toList());
    }

    // Mapiranje osnovnih podataka
    public RegistrovaniKorisnikOsnovniPodaciDTO mapToOsnovniDTO(RegistrovaniKorisnik korisnik) {
        if (korisnik == null) return null;

        RegistrovaniKorisnikOsnovniPodaciDTO dto = new RegistrovaniKorisnikOsnovniPodaciDTO(
                korisnik.getId(),
                korisnik.getIme(),
                korisnik.getPrezime(),
                korisnik.getKorisnickoIme(),
                korisnik.getEmail()
        );

        if (korisnik.getObavestenjaAktivnosti() != null) {
            dto.setObavestenjaAktivnosti(
                    korisnik.getObavestenjaAktivnosti().stream()
                            .map(ob -> new ObavestenjeAktivnostDTO(
                                    ob.getId(),
                                    ob.getVremePostavljanja(),
                                    ob.getSadrzaj(),
                                    ob.getNaslov(),
                                    ob.getProcitano()
                            ))
                            .collect(Collectors.toSet())
            );
        }

        return dto;
    }

    public List<RegistrovaniKorisnikOsnovniPodaciDTO> mapToOsnovniDTO(List<RegistrovaniKorisnik> korisnici) {
        return korisnici.stream().map(this::mapToOsnovniDTO).collect(Collectors.toList());
    }
}
