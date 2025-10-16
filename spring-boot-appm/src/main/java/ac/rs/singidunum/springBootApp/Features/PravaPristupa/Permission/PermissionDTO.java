package ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission;

import java.util.Set;

import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermissionDTO.UserPermissionDTORecord;


public class PermissionDTO {
	
	
	
	public record PermissionDTORecord(
			 Long id,
		     String ime,
		     Set<UserPermissionDTORecord> pravaPristupa
			) {}
    
}
