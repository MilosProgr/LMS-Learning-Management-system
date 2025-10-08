package ac.rs.singidunum.springBootApp.Features.Student.PohadjanjePredmeta;


import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO;

public class PohadjanjePredmetaDTO {
	private Long id;
	private Long konacnaOcena;
	
	private PredmetDTO predmet;
	private StudentNaGodiniDTO studentNaGodini;
	
	public PohadjanjePredmetaDTO() {
		super();
	}

	public PohadjanjePredmetaDTO(Long id, Long konacnaOcena) {
		super();
		this.id = id;
		this.konacnaOcena = konacnaOcena;
	}
	
//	public PohadjanjePredmetaDTO(Long id, Long konacnaOcena,RealizacijaPredmetaDTO realizacijaPredmeta) {
//		super();
//		this.id = id;
//		this.konacnaOcena = konacnaOcena;
//		this.realizacijaPredmeta = realizacijaPredmeta;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getKonacnaOcena() {
		return konacnaOcena;
	}

	public void setKonacnaOcena(Long konacnaOcena) {
		this.konacnaOcena = konacnaOcena;
	}

	public PredmetDTO getPredmet() {
		return predmet;
	}

	public void setPredmet(PredmetDTO predmet) {
		this.predmet = predmet;
	}

	public StudentNaGodiniDTO getStudentNaGodini() {
		return studentNaGodini;
	}

	public void setStudentNaGodini(StudentNaGodiniDTO studentNaGodini) {
		this.studentNaGodini = studentNaGodini;
	}
	
	
//	public RealizacijaPredmetaDTO getRealizacijaPredmeta() {
//		return realizacijaPredmeta;
//	}
//
//	public void setRealizacijaPredmeta(RealizacijaPredmetaDTO realizacijaPredmeta) {
//		this.realizacijaPredmeta = realizacijaPredmeta;
//	}
	

	
	
	
	
	
}
