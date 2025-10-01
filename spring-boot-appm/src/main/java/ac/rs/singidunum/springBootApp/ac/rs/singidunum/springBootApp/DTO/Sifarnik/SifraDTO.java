package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Sifarnik;


public class SifraDTO {
	private Long id;
	private String tekst;
	
	
	public SifraDTO(Long id, String tekst) {
		super();
		this.id = id;
		this.tekst = tekst;
	}
	public SifraDTO() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTekst() {
		return tekst;
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	
	
	
}
