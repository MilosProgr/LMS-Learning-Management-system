package ac.rs.singidunum.springBootApp.Model.Student;


import ac.rs.singidunum.springBootApp.Model.Predmet.Predmet;
import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PohadjanjePredmeta implements BaseEntity<Long>{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @ManyToOne(optional = false)
    @JoinColumn(name = "student_na_godini_id", nullable = false)
    private StudentNaGodini studentNaGodini;
	
    @ManyToOne
    private Predmet predmet;
    
	@Column(nullable = true)
    private Long konacnaOcena;

    public PohadjanjePredmeta() {
    }

    public PohadjanjePredmeta(Long id, Long konacnaOcena) {
        this.id = id;
        this.konacnaOcena = konacnaOcena;
    }

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

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

	public StudentNaGodini getStudentNaGodini() {
		return studentNaGodini;
	}

	public void setStudentNaGodini(StudentNaGodini studentNaGodini) {
		this.studentNaGodini = studentNaGodini;
	}

    
	

	
    
    
}
