package ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission;

import java.util.HashSet;
import java.util.Set;

import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.PermissionDTO;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.PermissionDTO.PermissionDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;

public class UserPermissionDTO {
	private Long id;

    private PermissionDTORecord permission;

    private RegistrovaniKorisnikDTORecord korisnik;
    
    private Set<PermissionDTORecord> listaUloga = new HashSet<>();

	public UserPermissionDTO(Long id, PermissionDTORecord permission, RegistrovaniKorisnikDTORecord korisnik) {
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

	public PermissionDTORecord getPermission() {
		return permission;
	}

	public void setPermission(PermissionDTORecord permission) {
		this.permission = permission;
	}

	public RegistrovaniKorisnikDTORecord getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(RegistrovaniKorisnikDTORecord korisnik) {
		this.korisnik = korisnik;
	}

	public Set<PermissionDTORecord> getListaUloga() {
		return listaUloga;
	}

	public void setListaUloga(Set<PermissionDTORecord> listaUloga) {
		this.listaUloga = listaUloga;
	}
    		
	public record UserPermissionDTORecord(
	        Long id,
	        PermissionDTORecord permission,
	        RegistrovaniKorisnikDTORecord korisnik,
	        Set<PermissionDTORecord> listaUloga
	) {}

}
