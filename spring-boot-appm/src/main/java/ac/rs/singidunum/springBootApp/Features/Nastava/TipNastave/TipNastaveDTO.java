package ac.rs.singidunum.springBootApp.Features.Nastava.TipNastave;


public class TipNastaveDTO {
//	Long id;
//	String naziv;
//	
//	
//
//	public TipNastaveDTO() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public TipNastaveDTO(Long id, String naziv) {
//		super();
//		this.id = id;
//		this.naziv = naziv;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getNaziv() {
//		return naziv;
//	}
//
//	public void setNaziv(String naziv) {
//		this.naziv = naziv;
//	}

	
	public record TipNastaveDTORecord(Long id,String naziv) {}
	
	
	
	
}
