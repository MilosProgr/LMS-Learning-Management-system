package ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik;

import java.util.HashSet;
import java.util.Set;


import ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti.ObavestenjeAktivnostDTO;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermissionDTO;


public class RegistrovaniKorisnikDTO {
	
	private Long id;
	private String ime;
	private String prezime;
    private String korisnickoIme;
    private String lozinka;
    private String email;
    
    private Set<UserPermissionDTO> pravaPristupa = new HashSet<>();
    
    private Set<ObavestenjeAktivnostDTO> obavestenjaAktivnosti = new HashSet<>();
    
	public RegistrovaniKorisnikDTO(Long id, String ime, String prezime, String korisnickoIme, String lozinka, String email) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.email = email;
		
		
	}
	public RegistrovaniKorisnikDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
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
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<ObavestenjeAktivnostDTO> getObavestenjaAktivnosti() {
		return obavestenjaAktivnosti;
	}
	public void setObavestenjaAktivnosti(Set<ObavestenjeAktivnostDTO> obavestenjaAktivnosti) {
		this.obavestenjaAktivnosti = obavestenjaAktivnosti;
	}

	
}
