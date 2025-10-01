package ac.rs.singidunum.springBootApp.DTO.Nastavnici;

import java.util.HashSet;
import java.util.Set;

import ac.rs.singidunum.springBootApp.DTO.ObavestenjaAktivnosti.ObavestenjeAktivnostDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.RealizacijaPredmetaDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.TipNastaveDTO;

public class NastavnikNaRealizacijiDTO {
	private Long id;
	private Long brojCasova;
	
	private NastavnikDTO nastavnik;
	
	private Set<RealizacijaPredmetaDTO> realizacijaPredmeta = new HashSet<>();
	
	public NastavnikNaRealizacijiDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NastavnikNaRealizacijiDTO(Long id, Long brojCasova) {
		super();
		this.id = id;
		this.brojCasova = brojCasova;
	}
	
	public NastavnikNaRealizacijiDTO(Long id, Long brojCasova,NastavnikDTO nastavnik) {
		super();
		this.id = id;
		this.brojCasova = brojCasova;
		this.nastavnik = nastavnik;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBrojCasova() {
		return brojCasova;
	}

	public void setBrojCasova(Long brojCasova) {
		this.brojCasova = brojCasova;
	}

	public NastavnikDTO getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(NastavnikDTO nastavnik) {
		this.nastavnik = nastavnik;
	}

	public Set<RealizacijaPredmetaDTO> getRealizacijaPredmeta() {
		return realizacijaPredmeta;
	}

	public void setRealizacijaPredmeta(Set<RealizacijaPredmetaDTO> realizacijaPredmeta) {
		this.realizacijaPredmeta = realizacijaPredmeta;
	}
	
	
	
	
	
	
	
}
