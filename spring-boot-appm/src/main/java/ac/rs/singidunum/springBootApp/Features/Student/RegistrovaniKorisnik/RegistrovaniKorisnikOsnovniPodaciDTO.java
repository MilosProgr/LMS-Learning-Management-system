package ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik;

import java.util.HashSet;
import java.util.Set;

import ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti.ObavestenjeAktivnostDTO;
import ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti.ObavestenjeAktivnostDTO.ObavestenjeAktivnostiDTORecord;

public class RegistrovaniKorisnikOsnovniPodaciDTO {
	
	private Long id;
	private String ime;
	private String prezime;
    private String korisnickoIme;
    private String email;
    
    private Set<ObavestenjeAktivnostDTO> obavestenjaAktivnosti = new HashSet<>();

	public RegistrovaniKorisnikOsnovniPodaciDTO(Long id, String ime, String prezime, String korisnickoIme,
			String email) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
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
    
	public record RegistrovaniKorisnikOsnovniPodaciDTORecord(
			 Long id,
			 String ime,
			 String prezime,
		     String korisnickoIme,
		     String email,
		    
		     Set<ObavestenjeAktivnostiDTORecord> obavestenjaAktivnosti
			) {}
	
    
}
