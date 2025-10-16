package ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti.ObavestenjeAktivnostDTO;
import ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti.ObavestenjeAktivnostDTO.ObavestenjeAktivnostiDTORecord;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.PermissionDTO.PermissionDTORecord;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermissionDTO.UserPermissionDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikOsnovniPodaciDTO.RegistrovaniKorisnikOsnovniPodaciDTORecord;
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
        Set<ObavestenjeAktivnostiDTORecord> obavestenjaSet = (korisnik.getObavestenjaAktivnosti() != null) ?
                korisnik.getObavestenjaAktivnosti().stream()
                        .map(ob -> new ObavestenjeAktivnostiDTORecord(
                                ob.getId(),
                                ob.getVremePostavljanja(),
                                ob.getSadrzaj(),
                                ob.getNaslov(),
                                ob.getProcitano(), null
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
    public RegistrovaniKorisnikOsnovniPodaciDTORecord mapToOsnovniDTO(RegistrovaniKorisnik korisnik) {
        if (korisnik == null) return null;

        Set<ObavestenjeAktivnostiDTORecord> obavestenja = null;

        if (korisnik.getObavestenjaAktivnosti() != null && !korisnik.getObavestenjaAktivnosti().isEmpty()) {
            obavestenja = korisnik.getObavestenjaAktivnosti().stream()
                    .map(ob -> new ObavestenjeAktivnostiDTORecord(
                            ob.getId(),
                            ob.getVremePostavljanja(),
                            ob.getSadrzaj(),
                            ob.getNaslov(),
                            ob.getProcitano(),
                            null // ako ima korisnik ili dodatne veze, dodaj ih ovde
                    ))
                    .collect(Collectors.toSet());
        }

        return new RegistrovaniKorisnikOsnovniPodaciDTORecord(
                korisnik.getId(),
                korisnik.getIme(),
                korisnik.getPrezime(),
                korisnik.getKorisnickoIme(),
                korisnik.getEmail(),
                obavestenja
        );
    }


    public List<RegistrovaniKorisnikOsnovniPodaciDTORecord> mapToOsnovniDTO(List<RegistrovaniKorisnik> korisnici) {
        return korisnici.stream().map(this::mapToOsnovniDTO).collect(Collectors.toList());
    }
}
