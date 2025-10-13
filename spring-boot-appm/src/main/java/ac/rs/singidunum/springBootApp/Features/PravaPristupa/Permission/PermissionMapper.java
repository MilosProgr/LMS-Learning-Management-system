package ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.PermissionDTO.PermissionDTORecord;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermissionDTO;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermissionDTO.UserPermissionDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class PermissionMapper implements Mapper<PermissionDTORecord, Permission> {

	@Override
    public PermissionDTORecord map(Permission e) {
        if (e == null) {
            return null;
        }

        // mapiranje UserPermissionDTO iz Permission entiteta
        Set<UserPermissionDTORecord> pravaPristupaSet = e.getPravaPristupa().stream()
                .map(pravaPristupa -> new UserPermissionDTORecord(
                        pravaPristupa.getId(),
                        null, // permission unutar UserPermissionDTO može ostati null ili mapirati po potrebi
                        new RegistrovaniKorisnikDTORecord(
                                pravaPristupa.getKorisnik().getId(),
                                pravaPristupa.getKorisnik().getIme(),
                                pravaPristupa.getKorisnik().getPrezime(),
                                pravaPristupa.getKorisnik().getKorisnickoIme(),
                                pravaPristupa.getKorisnik().getLozinka(),
                                pravaPristupa.getKorisnik().getEmail(),
                                null,
                                Set.of()
                        ),
                        Set.of() // listaUloga prazna
                ))
                .collect(Collectors.toSet());

        // kreiranje PermissionDTO record-a
        return new PermissionDTORecord(
                e.getId(),
                e.getIme(),
                pravaPristupaSet
        );
    }

//    @Override
//    public List<PermissionDTO> map(List<Permission> e) {
//        return e.stream().map(this::map).collect(Collectors.toList());
//    }
	
}
