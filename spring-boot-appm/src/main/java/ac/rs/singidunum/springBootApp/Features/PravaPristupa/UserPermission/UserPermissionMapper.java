package ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission;



import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.Permission;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.PermissionDTO.PermissionDTORecord;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermissionDTO.UserPermissionDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnik;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class UserPermissionMapper implements Mapper<UserPermissionDTORecord, UserPermission> {

	@Override
	public UserPermissionDTORecord map(UserPermission e) {
		if(e == null) {
			return null;
		}
		RegistrovaniKorisnik korisnik = e.getKorisnik();
		Permission permission = e.getPermission();

		UserPermissionDTORecord uPermDTO =
				new UserPermissionDTORecord(
						e.getId(),
						new PermissionDTORecord(
								permission.getId(),
								permission.getIme(), null
								),
						new RegistrovaniKorisnikDTORecord(
								korisnik.getId(),
								korisnik.getIme(),
								korisnik.getPrezime(),
								korisnik.getKorisnickoIme(),
								korisnik.getLozinka(),
								korisnik.getEmail(), null, null
								), null
						);
		return uPermDTO;
	}

}
