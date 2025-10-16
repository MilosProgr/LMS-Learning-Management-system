package ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission;

import java.util.Set;

import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.PermissionDTO.PermissionDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;

public class UserPermissionDTO {
	
    		
	public record UserPermissionDTORecord(
	        Long id,
	        PermissionDTORecord permission,
	        RegistrovaniKorisnikDTORecord korisnik,
	        Set<PermissionDTORecord> listaUloga
	) {}

}
