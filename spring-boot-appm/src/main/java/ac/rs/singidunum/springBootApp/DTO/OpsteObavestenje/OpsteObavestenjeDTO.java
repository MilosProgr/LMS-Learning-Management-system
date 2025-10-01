package ac.rs.singidunum.springBootApp.DTO.OpsteObavestenje;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class OpsteObavestenjeDTO {

	private String naslov;
	private String tekst;
	private LocalDate datum;
	private LocalTime vreme;

	private Long id;

	public OpsteObavestenjeDTO(Long id, String naslov, String tekst, LocalDate datum, LocalTime vreme) {
		super();
		this.id = id;
		this.naslov = naslov;
		this.tekst = tekst;
		this.datum = datum;
		this.vreme = vreme;
	}

	public OpsteObavestenjeDTO() {
		super();
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

}
