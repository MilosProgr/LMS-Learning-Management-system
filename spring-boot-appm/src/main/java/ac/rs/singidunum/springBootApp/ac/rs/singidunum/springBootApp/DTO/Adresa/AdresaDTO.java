package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Adresa;


public class AdresaDTO {
	private Long id;
	private String ulica;
	private String broj;
	private MestoDTO mesto;
	//private Long mestoId;
	public AdresaDTO(Long id, String ulica, String broj) {
		super();
		this.id = id;
		this.ulica = ulica;
		this.broj = broj;
	}
	
	public AdresaDTO(Long id) {
		this.id = id;
	}
	
	public AdresaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public String getBroj() {
		return broj;
	}
	public void setBroj(String broj) {
		this.broj = broj;
	}

	public MestoDTO getMesto() {
		return mesto;
	}

	public void setMesto(MestoDTO mesto) {
		this.mesto = mesto;
	}
	
	
	
	
	
	
}
