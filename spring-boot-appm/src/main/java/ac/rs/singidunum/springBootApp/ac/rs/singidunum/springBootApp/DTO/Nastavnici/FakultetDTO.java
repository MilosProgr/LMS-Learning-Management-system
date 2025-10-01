package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Nastavnici;

import java.util.ArrayList;
import java.util.List;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Adresa.AdresaDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet.StudijskiProgramDTO;

public class FakultetDTO {
	private Long id;
	private String naziv;
	private AdresaDTO adresa;
	private UniverzitetDTO univerzitet;
	
	private NastavnikDTO dekan;
	
	private String kontakt;
	private String opis;
	
	private List<StudijskiProgramDTO> programi = new ArrayList<>();
	
	public FakultetDTO(Long id, String naziv,String kontakt, String opis) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.kontakt = kontakt;
		this.opis = opis;
		
	}

	public FakultetDTO() {
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

	public AdresaDTO getAdresa() {
		return adresa;
	}

	public void setAdresa(AdresaDTO adresa) {
		this.adresa = adresa;
	}

	public UniverzitetDTO getUniverzitet() {
		return univerzitet;
	}

	public void setUniverzitet(UniverzitetDTO univerzitet) {
		this.univerzitet = univerzitet;
	}

	public List<StudijskiProgramDTO> getProgrami() {
		return programi;
	}

	public void setProgrami(List<StudijskiProgramDTO> programi) {
		this.programi = programi;
	}


	public String getKontakt() {
		return kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

	public NastavnikDTO getDekan() {
		return dekan;
	}

	public void setDekan(NastavnikDTO dekan) {
		this.dekan = dekan;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	

	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
