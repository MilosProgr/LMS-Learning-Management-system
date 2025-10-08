package ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.Permission;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.PermissionDTO;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnik;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class UserPermissionMapper implements Mapper<UserPermissionDTO, UserPermission> {

	@Override
	public UserPermissionDTO map(UserPermission e) {
		if(e == null) {
			return null;
		}
		RegistrovaniKorisnik korisnik = e.getKorisnik();
		Permission permission = e.getPermission();

		UserPermissionDTO uPermDTO =
				new UserPermissionDTO(
						e.getId(),
						new PermissionDTO(
								permission.getId(),
								permission.getIme()
								),
						new RegistrovaniKorisnikDTO(
								korisnik.getId(),
								korisnik.getIme(),
								korisnik.getPrezime(),
								korisnik.getKorisnickoIme(),
								korisnik.getLozinka(),
								korisnik.getEmail()
								)
						);
		return uPermDTO;
	}

	@Override
	public List<UserPermissionDTO> map(List<UserPermission> e) {
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
