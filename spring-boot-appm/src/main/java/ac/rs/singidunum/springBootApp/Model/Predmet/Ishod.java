package ac.rs.singidunum.springBootApp.Model.Predmet;


import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Ishod  implements BaseEntity<Long>{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(columnDefinition = "TEXT")
    private String opis;
	
	private boolean polozeno;
	
    @ManyToOne
    private Predmet predmet;
    
	public Ishod(Long id, String opis, boolean polozeno, Predmet predmet) {
		super();
		this.id = id;
		this.opis = opis;
		this.predmet = predmet;
		this.polozeno = polozeno;
	}


	public Ishod() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getOpis() {
		return opis;
	}


	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	
	public boolean isPolozeno() {
		return polozeno;
	}


	public void setPolozeno(boolean polozeno) {
		this.polozeno = polozeno;
	}


	public Predmet getPredmet() {
		return predmet;
	}


	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}


	


	

}
