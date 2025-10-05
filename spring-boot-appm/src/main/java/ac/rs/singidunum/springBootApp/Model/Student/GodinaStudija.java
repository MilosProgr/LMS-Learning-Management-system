package ac.rs.singidunum.springBootApp.Model.Student;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.Predmet;
import ac.rs.singidunum.springBootApp.Model.Predmet.StudijskiProgram;
import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class GodinaStudija implements BaseEntity<Long>{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer godina;
    
    @OneToMany(mappedBy = "godinaStudija")
    private Set<StudentNaGodini> studentiNaGodini;
    
    @OneToMany(mappedBy = "godinaStudija")
    private List<Predmet> predmeti;

	public GodinaStudija() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GodinaStudija(Long id, Integer godina) {
		super();
		this.id = id;
		this.godina = godina;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getGodina() {
		return godina;
	}

	public void setGodina(Integer godina) {
		this.godina = godina;
	}

	
	public Set<StudentNaGodini> getStudentiNaGodini() {
		return studentiNaGodini;
	}

	public void setStudentiNaGodini(Set<StudentNaGodini> studentiNaGodini) {
		this.studentiNaGodini = studentiNaGodini;
	}

	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
    
    
    

}
