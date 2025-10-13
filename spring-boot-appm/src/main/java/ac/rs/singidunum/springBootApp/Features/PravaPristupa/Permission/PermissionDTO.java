package ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission;

import java.util.HashSet;
import java.util.Set;

import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermissionDTO;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermissionDTO.UserPermissionDTORecord;


public class PermissionDTO {
	
	private Long id;
	private Set<UserPermissionDTORecord> pravaPristupa = new HashSet<>();
	private String ime;
	
	public PermissionDTO(String ime) {
        this.ime = ime;
    }

    public PermissionDTO(Long id,  String ime) {
        this.id = id;
        this.ime = ime;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<UserPermissionDTORecord> getPravaPristupa() {
		return pravaPristupa;
	}

	public void setPravaPristupa(Set<UserPermissionDTORecord> pravaPristupa) {
		this.pravaPristupa = pravaPristupa;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}
	
	public record PermissionDTORecord(
			 Long id,
		     String ime,
		     Set<UserPermissionDTORecord> pravaPristupa
			) {}
    
}
