package ac.rs.singidunum.springBootApp.Features.Predmeti.Kurs;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.client.HttpClientErrorException.Forbidden;

import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;

public class KursDTO {
	private Long id;
	private String naziv;
	private Integer trajanje;
	private String oznaka;
	private LocalDateTime datumPocetka;
	private LocalDateTime datumKraja;
	
	
	private Set<PredmetDTO> predmeti = new HashSet<>();

	public KursDTO(Long id, String naziv, Integer trajanje,String oznaka,LocalDateTime datumPocetka,LocalDateTime datumkraja) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.trajanje = trajanje;
		this.oznaka = oznaka;
		this.datumPocetka = datumPocetka;
		this.datumKraja = datumKraja;
	}

	public KursDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Integer getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Integer trajanje) {
		this.trajanje = trajanje;
	}

	public Set<PredmetDTO> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(Set<PredmetDTO> predmeti) {
		this.predmeti = predmeti;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public LocalDateTime getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(LocalDateTime datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public LocalDateTime getDatumKraja() {
		return datumKraja;
	}

	public void setDatumKraja(LocalDateTime datumKraja) {
		this.datumKraja = datumKraja;
	}
	
	

	
	
	
}
