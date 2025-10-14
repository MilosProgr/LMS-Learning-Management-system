package ac.rs.singidunum.springBootApp.Features.Predmeti.PrijavljeniIspit;

import java.util.ArrayList;
import java.util.List;

import ac.rs.singidunum.springBootApp.Features.Polaganja.EvaluacijaZnanja.EvaluacijaZnanjaDTO;
import ac.rs.singidunum.springBootApp.Features.Polaganja.EvaluacijaZnanja.EvaluacijaZnanjaDTO.EvaluacijaZnanjaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.IspitniRok.IspitniRokDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO;

public class PrijavljeniIspitDTO {
	private Long id;
	
	private Boolean prijavljen;
	
	private Integer brojPrijava;
	
	 private List<EvaluacijaZnanjaDTO> evaluacijeZnanja = new ArrayList<>();
	
	private StudentNaGodiniDTO StudentNaGodini;
	
	private PredmetDTO predmet;
	
	private IspitniRokDTO ispitniRok;
	
	
	public PrijavljeniIspitDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PrijavljeniIspitDTO(Long id, boolean prijavljen,Integer brojPrijava) {
		super();
		this.id = id;
		this.prijavljen = prijavljen;
		this.brojPrijava = brojPrijava;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isPrijavljen() {
		return prijavljen;
	}

	public void setPrijavljen(boolean prijavljen) {
		this.prijavljen = prijavljen;
	}

	public List<EvaluacijaZnanjaDTO> getEvaluacijeZnanja() {
		return evaluacijeZnanja;
	}

	public void setEvaluacijeZnanja(List<EvaluacijaZnanjaDTO> evaluacijeZnanja) {
		this.evaluacijeZnanja = evaluacijeZnanja;
	}

	public StudentNaGodiniDTO getStudentNaGodini() {
		return StudentNaGodini;
	}

	public void setStudentNaGodini(StudentNaGodiniDTO StudentNaGodini) {
		this.StudentNaGodini = StudentNaGodini;
	}

	public Boolean getPrijavljen() {
		return prijavljen;
	}

	public void setPrijavljen(Boolean prijavljen) {
		this.prijavljen = prijavljen;
	}

	public Integer getBrojPrijava() {
		return brojPrijava;
	}

	public void setBrojPrijava(Integer brojPrijava) {
		this.brojPrijava = brojPrijava;
	}

	public PredmetDTO getPredmet() {
		return predmet;
	}

	public void setPredmet(PredmetDTO predmet) {
		this.predmet = predmet;
	}

	public IspitniRokDTO getIspitniRok() {
		return ispitniRok;
	}

	public void setIspitniRok(IspitniRokDTO ispitniRok) {
		this.ispitniRok = ispitniRok;
	}
	
	
	public record PrijavljeniIspitDTORecord(
			 Long id,	
			 Boolean prijavljen,		
			 Integer brojPrijava,		
			 List<EvaluacijaZnanjaDTORecord> evaluacijeZnanja,	
			 StudentNaGodiniDTO StudentNaGodini,
			 PredmetDTO predmet,
			 IspitniRokDTO ispitniRok
			) {}
	
	
	
	
	
}
