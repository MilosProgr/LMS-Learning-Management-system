package ac.rs.singidunum.springBootApp.DTO.Nastavnici;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import ac.rs.singidunum.springBootApp.DTO.Adresa.AdresaDTO;

public class UniverzitetDTO {
	private Long id;
	private String naziv;
	private String opis;
	private LocalDate datumOsnivanja;
	private List<FakultetDTO> fakultet;
	private AdresaDTO adresa;
	private NastavnikDTO rektor;
	public UniverzitetDTO(Long id, String naziv, LocalDate datumOsnivanja, String opis) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.datumOsnivanja = datumOsnivanja;
		this.opis = opis;
	}

	public UniverzitetDTO() {
		super();
	
		// TODO Auto-generated constructor stub
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

	public LocalDate getDatumOsnivanja() {
		return datumOsnivanja;
	}

	public void setDatumOsnivanja(LocalDate datumOsnivanja) {
		this.datumOsnivanja = datumOsnivanja;
	}

	public AdresaDTO getAdresa() {
		return adresa;
	}

	public void setAdresa(AdresaDTO adresa) {
		this.adresa = adresa;
	}

	public List<FakultetDTO> getFakultet() {
		return fakultet;
	}

	public void setFakultet(List<FakultetDTO> fakultet) {
		this.fakultet = fakultet;
	}

	public NastavnikDTO getRektor() {
		return rektor;
	}

	public void setRektor(NastavnikDTO rektor) {
		this.rektor = rektor;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	
	
	
	
	
	
	
	
	
	
}
