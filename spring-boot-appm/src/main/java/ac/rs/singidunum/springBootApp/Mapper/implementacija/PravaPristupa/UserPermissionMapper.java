package ac.rs.singidunum.springBootApp.Mapper.implementacija.PravaPristupa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.PravaPristupa.PermissionDTO;
import ac.rs.singidunum.springBootApp.DTO.PravaPristupa.UserPermissionDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.PravaPristupa.Permission;
import ac.rs.singidunum.springBootApp.Model.PravaPristupa.UserPermission;
import ac.rs.singidunum.springBootApp.Model.Student.RegistrovaniKorisnik;

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
