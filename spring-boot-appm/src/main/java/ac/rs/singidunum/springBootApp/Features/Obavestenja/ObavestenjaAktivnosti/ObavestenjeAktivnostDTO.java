package ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti;

import java.time.LocalDateTime;

import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO;




public class ObavestenjeAktivnostDTO {
	private Long id;
	private LocalDateTime vremePostavljanja;
	private String sadrzaj;
	private String naslov;
	private RegistrovaniKorisnikDTO registrovaniKorisnik;
	private Boolean procitano;
	
	public ObavestenjeAktivnostDTO(Long id, LocalDateTime vremePostavljanja, String sadrzaj, String naslov,Boolean procitano) {
		super();
		this.id = id;
		this.vremePostavljanja = vremePostavljanja;
		this.sadrzaj = sadrzaj;
		this.naslov = naslov;
		this.procitano = procitano;
	}
	
	public ObavestenjeAktivnostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getVremePostavljanja() {
		return vremePostavljanja;
	}

	public void setVremePostavljanja(LocalDateTime vremePostavljanja) {
		this.vremePostavljanja = vremePostavljanja;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	
	public Boolean getProcitano() {
		return procitano;
	}

	public void setProcitano(Boolean procitano) {
		this.procitano = procitano;
	}

	public RegistrovaniKorisnikDTO getRegistrovaniKorisnik() {
		return registrovaniKorisnik;
	}


	public void setRegistrovaniKorisnik(RegistrovaniKorisnikDTO registrovaniKorisnik) {
		this.registrovaniKorisnik = registrovaniKorisnik;
	}
	
}
