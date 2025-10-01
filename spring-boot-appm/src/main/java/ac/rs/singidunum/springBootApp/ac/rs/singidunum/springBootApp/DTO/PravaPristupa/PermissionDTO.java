package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.PravaPristupa;

import java.util.HashSet;
import java.util.Set;


public class PermissionDTO {
	
	private Long id;
	private Set<UserPermissionDTO> pravaPristupa = new HashSet<>();
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

	public Set<UserPermissionDTO> getPravaPristupa() {
		return pravaPristupa;
	}

	public void setPravaPristupa(Set<UserPermissionDTO> pravaPristupa) {
		this.pravaPristupa = pravaPristupa;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}
    
}
