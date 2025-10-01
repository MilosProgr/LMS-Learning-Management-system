package ac.rs.singidunum.springBootApp.DTO.Adresa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;

public class MestoDTO {
	private Long id;
	
	private String naziv;
	
	private DrzavaDTO drzava;
	
	
	//private Long idDrzava;
	
	private List <AdresaDTO> adrese = new ArrayList<>();
	

	public MestoDTO(Long id, String naziv, DrzavaDTO drzava) {
		//super();
		this.id = id;
		this.naziv = naziv;
		this.drzava = drzava;
		
	}
	public MestoDTO(Long id, String naziv) {
		//super();
		this.id = id;
		this.naziv = naziv;
		
	}

	public MestoDTO() {
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

	public DrzavaDTO getDrzava() {
		return drzava;
	}

	public void setDrzava(DrzavaDTO drzava) {
		this.drzava = drzava;
	}

//	public Long getIdDrzava() {
//		return idDrzava;
//	}
//
//	public void setIdDrzava(Long idDrzava) {
//		this.idDrzava = idDrzava;
//	}

	public List<AdresaDTO> getAdrese() {
		return adrese;
	}

	public void setAdrese(List<AdresaDTO> adrese) {
		this.adrese = adrese;
	}
	
	
	
	
	
	
}
