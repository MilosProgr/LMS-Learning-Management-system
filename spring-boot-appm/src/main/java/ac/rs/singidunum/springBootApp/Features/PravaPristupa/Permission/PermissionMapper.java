package ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermissionDTO;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class PermissionMapper implements Mapper<PermissionDTO, Permission> {

	@Override
	public PermissionDTO map(Permission e) {
		if(e == null) {
			return null;
		}
		PermissionDTO permissionDTO =
				new PermissionDTO(e.getId(),
						e.getIme());
		permissionDTO.setPravaPristupa(
				e.getPravaPristupa().stream()
				.map(pravaPristupa ->
				new UserPermissionDTO(pravaPristupa.getId(),
						null, 
						new RegistrovaniKorisnikDTO(
								pravaPristupa.getKorisnik().getId(),
								pravaPristupa.getKorisnik().getIme(),
								pravaPristupa.getKorisnik().getPrezime(),
								pravaPristupa.getKorisnik().getKorisnickoIme(),
								pravaPristupa.getKorisnik().getLozinka(),
								pravaPristupa.getKorisnik().getEmail()
								)
								
						)
				).collect(Collectors.toSet())
				);
		return permissionDTO;
	}

	@Override
	public List<PermissionDTO> map(List<Permission> e) {
		return e.stream().map(this::map).collect(Collectors.toList());
	}
	
}
