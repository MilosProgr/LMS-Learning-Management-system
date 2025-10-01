package ac.rs.singidunum.springBootApp.Model.ObavestenjeAktivnosti;

import java.time.LocalDateTime;

import ac.rs.singidunum.springBootApp.Model.Student.RegistrovaniKorisnik;
import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ObavestenjeAktivnosti implements BaseEntity<Long> {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime vremePostavljanja;
    private String sadrzaj;
    private String naslov;
    private Boolean procitano;
    
    @ManyToOne
    private RegistrovaniKorisnik registrovaniKorisnik;
    
    
	public ObavestenjeAktivnosti(Long id, LocalDateTime vremePostavljanja, String sadrzaj, String naslov,Boolean procitano) {
		super();
		this.id = id;
		this.vremePostavljanja = vremePostavljanja;
		this.sadrzaj = sadrzaj;
		this.naslov = naslov;
		this.procitano = procitano;
	}

	public ObavestenjeAktivnosti() {
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

	public RegistrovaniKorisnik getRegistrovaniKorisnik() {
		return registrovaniKorisnik;
	}

	public void setRegistrovaniKorisnik(RegistrovaniKorisnik registrovaniKorisnik) {
		this.registrovaniKorisnik = registrovaniKorisnik;
	}

	public Boolean getProcitano() {
		return procitano;
	}

	public void setProcitano(Boolean procitano) {
		this.procitano = procitano;
	}
	
	
	

	
	
	
	
    
    


}
