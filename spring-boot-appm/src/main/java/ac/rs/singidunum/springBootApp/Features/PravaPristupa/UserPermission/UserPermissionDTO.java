package ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission;

import java.util.HashSet;
import java.util.Set;

import ac.rs.singidunum.springBootApp.DTO.Student.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.PermissionDTO;

public class UserPermissionDTO {
	private Long id;

    private PermissionDTO permission;

    private RegistrovaniKorisnikDTO korisnik;
    
    private Set<PermissionDTO> listaUloga = new HashSet<>();

	public UserPermissionDTO(Long id, PermissionDTO permission, RegistrovaniKorisnikDTO korisnik) {
		super();
		this.id = id;
		this.permission = permission;
		this.korisnik = korisnik;
	}

	public UserPermissionDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PermissionDTO getPermission() {
		return permission;
	}

	public void setPermission(PermissionDTO permission) {
		this.permission = permission;
	}

	public RegistrovaniKorisnikDTO getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(RegistrovaniKorisnikDTO korisnik) {
		this.korisnik = korisnik;
	}

	public Set<PermissionDTO> getListaUloga() {
		return listaUloga;
	}

	public void setListaUloga(Set<PermissionDTO> listaUloga) {
		this.listaUloga = listaUloga;
	}
    		

}
