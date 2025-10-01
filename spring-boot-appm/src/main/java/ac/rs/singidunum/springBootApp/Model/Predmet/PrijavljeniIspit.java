package ac.rs.singidunum.springBootApp.Model.Predmet;

import java.util.ArrayList;
import java.util.List;

import ac.rs.singidunum.springBootApp.Model.Student.StudentNaGodini;
import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class PrijavljeniIspit implements BaseEntity<Long> {
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

	 @Column(columnDefinition = "TINYINT(0)")
	 private Boolean prijavljen;

	 @Column(columnDefinition = "INT DEFAULT 0")
	 private Integer brojPrijava = 0;
	 
	 //Pokusaji polaganja prijavljenog ispita u jednom roku
	 @OneToMany(mappedBy = "prijavljeniIspit", fetch = FetchType.LAZY)
	 private List<EvaluacijaZnanja> evaluacijeZnanja = new ArrayList<>();
    
	 @ManyToOne
	 private StudentNaGodini studentNaGodini;
	 
	 @ManyToOne
	 private Predmet predmet;
	 
	 @ManyToOne
	 private IspitniRok ispitniRok;
	 
	 
	
	 public PrijavljeniIspit() {
		super();
		// TODO Auto-generated constructor stub
	}


	 public PrijavljeniIspit(Long id, boolean prijavljen,Integer brojprijava) {
		super();
		this.id = id;
		this.prijavljen = prijavljen;
		this.brojPrijava = brojprijava;
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


	 public List<EvaluacijaZnanja> getEvaluacijeZnanja() {
		return evaluacijeZnanja;
	}


	public void setEvaluacijeZnanja(List<EvaluacijaZnanja> evaluacijeZnanja) {
		this.evaluacijeZnanja = evaluacijeZnanja;
	}


	public StudentNaGodini getStudentNaGodini() {
		 return studentNaGodini;
	 }

	 public void setStudentNaGodini(StudentNaGodini studentNaGodini) {
		 this.studentNaGodini = studentNaGodini;
	 }


	public Integer getBrojPrijava() {
		return brojPrijava;
	}


	public void setBrojPrijava(Integer brojPrijava) {
		this.brojPrijava = brojPrijava;
	}


	public Boolean getPrijavljen() {
		return prijavljen;
	}


	public void setPrijavljen(Boolean prijavljen) {
		this.prijavljen = prijavljen;
	}


	public Predmet getPredmet() {
		return predmet;
	}


	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}


	public IspitniRok getIspitniRok() {
		return ispitniRok;
	}


	public void setIspitniRok(IspitniRok ispitniRok) {
		this.ispitniRok = ispitniRok;
	}
	
	public void addEvaluacija(EvaluacijaZnanja e) {
	    if (e == null) return;
	    if (!this.evaluacijeZnanja.contains(e)) {
	        this.evaluacijeZnanja.add(e);
	    }
	    e.setPrijavljeniIspit(this);
	}

	public void removeEvaluacija(EvaluacijaZnanja e) {
	    if (e == null) return;
	    if (this.evaluacijeZnanja.remove(e)) {
	        e.setPrijavljeniIspit(null);
	    }
	}
	
	 
	 
}
