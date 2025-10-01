package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Nastavnici.Fakultet;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.GodinaStudija;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.Student;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.StudentNaGodini;
//import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.StudentNaGodini;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class StudijskiProgram implements BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String naziv;
	@ManyToOne
	@JoinColumn(name = "fakultet_id")
	private Fakultet fakultet;
	
	@ManyToMany(mappedBy = "studijskiProgrami")
	private Set<Predmet> predmeti = new HashSet<>();
	
	@ManyToOne
	private GodinaStudija godinaStudija;
	
	@OneToMany(mappedBy = "studijskiProgram")
	private List<StudentNaGodini> studentiNaGodini = new ArrayList<>();

	
	

	public StudijskiProgram(Long id, String naziv,  Fakultet fakultet) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.fakultet = fakultet;
	}
	
	public StudijskiProgram() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
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


	public Fakultet getFakultet() {
		return fakultet;
	}

	public void setFakultet(Fakultet fakultet) {
		this.fakultet = fakultet;
	}

	public Set<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(Set<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	public GodinaStudija getGodinaStudija() {
		return godinaStudija;
	}

	public void setGodinaStudija(GodinaStudija godinaStudija) {
		this.godinaStudija = godinaStudija;
	}

	public List<StudentNaGodini> getStudentiNaGodini() {
		return studentiNaGodini;
	}

	public void setStudentiNaGodini(List<StudentNaGodini> studentiNaGodini) {
		this.studentiNaGodini = studentiNaGodini;
	}




	
	


	
	
	
	
	
	
	
	
}
