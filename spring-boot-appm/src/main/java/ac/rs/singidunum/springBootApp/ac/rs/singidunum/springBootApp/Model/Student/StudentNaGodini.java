package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.PrijavljeniIspit;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.StudijskiProgram;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

//STUDENT NA GODINI JE UPISAN STUDENT KOJEM SE SETUJE INDEKS
@Entity
public class StudentNaGodini implements BaseEntity<Long>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private LocalDateTime datumUpisa;
    private String brojIndeksa;
    private Double prosek = 0.0;
    
    @ManyToOne
    private Student student;
    
	@OneToMany(mappedBy = "studentNaGodini")
	private List<PrijavljeniIspit> prijavljenIspit;
	
	@ManyToOne
	private StudijskiProgram studijskiProgram;
	
    @ManyToOne
    private GodinaStudija godinaStudija;
    
    @OneToMany(mappedBy = "studentNaGodini", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PohadjanjePredmeta> pohadjanja = new ArrayList<>();

	
	public StudentNaGodini() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentNaGodini(Long id, LocalDateTime datumUpisa, String brojIndeksa,Double prosek) {
		super();
		this.id = id;
		this.datumUpisa = datumUpisa;
		this.brojIndeksa = brojIndeksa;
		this.prosek = prosek;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDatumUpisa() {
		return datumUpisa;
	}

	public void setDatumUpisa(LocalDateTime datumUpisa) {
		this.datumUpisa = datumUpisa;
	}

	public String getBrojIndeksa() {
		return brojIndeksa;
	}

	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public GodinaStudija getGodinaStudija() {
		return godinaStudija;
	}

	public void setGodinaStudija(GodinaStudija godinaStudija) {
		this.godinaStudija = godinaStudija;
	}
    public List<PrijavljeniIspit> getPrijavljenIspit() {
        return prijavljenIspit;
    }

    public void setPrijavljenIspit(List<PrijavljeniIspit> prijavljenIspit) {
        this.prijavljenIspit = prijavljenIspit;
    }
    
	public StudijskiProgram getStudijskiProgram() {
		return studijskiProgram;
	}

	public void setStudijskiProgram(StudijskiProgram studijskiProgram) {
		this.studijskiProgram = studijskiProgram;
	}

	public Double getProsek() {
		return prosek;
	}

	public void setProsek(Double prosek) {
		this.prosek = prosek;
	}

	public List<PohadjanjePredmeta> getPohadjanja() {
		return pohadjanja;
	}

	public void setPohadjanja(List<PohadjanjePredmeta> pohadjanja) {
		this.pohadjanja = pohadjanja;
	}
	
	

}
