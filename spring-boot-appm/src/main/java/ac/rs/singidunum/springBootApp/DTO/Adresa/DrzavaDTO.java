package ac.rs.singidunum.springBootApp.DTO.Adresa;

import java.util.HashSet;
import java.util.Set;

public class DrzavaDTO {
	private Long id;
	private String naziv;
	
	private Set<MestoDTO>  mesta = new HashSet<>();

	public DrzavaDTO(Long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}
	
	public DrzavaDTO(String naziv) {
		super();
		this.naziv = naziv;
		
	}
	
	

	public DrzavaDTO() {
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

	public Set<MestoDTO> getMesta() {
		return mesta;
	}

	public void setMesta(Set<MestoDTO> mesta) {
		this.mesta = mesta;
	}
	
	
}
