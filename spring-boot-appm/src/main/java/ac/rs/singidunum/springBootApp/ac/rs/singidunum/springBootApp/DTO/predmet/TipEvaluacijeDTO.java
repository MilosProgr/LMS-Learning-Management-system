package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet;



public class TipEvaluacijeDTO {
	Long id;
	String naziv;
	
	
	public TipEvaluacijeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipEvaluacijeDTO(Long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
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


	
	
}
