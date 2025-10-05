package ac.rs.singidunum.springBootApp.Features.Obavestenja.OpstaObavestenja;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OpsteObavestenje implements BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String naslov;

	@Column(columnDefinition = "TEXT")
	private String tekst;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // Regulisanje formata datuma
	private LocalDate datum;
	private LocalTime vreme; // 10:15:30 format vremena za LocalTime

	

	public OpsteObavestenje() {
		super();
	}

	public OpsteObavestenje(Long id, String naslov, String tekst, LocalDate datum, LocalTime vreme) {
		super();
		this.id = id;
		this.naslov = naslov;
		this.tekst = tekst;
		this.datum = datum;
		this.vreme = vreme;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public LocalTime getVreme() {
		return vreme;
	}

	public void setVreme(LocalTime vreme) {
		this.vreme = vreme;
	}
}
