package ac.rs.singidunum.springBootApp.Model.Predmet;

import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TipNastave implements BaseEntity<Long>{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String naziv;

    

	public TipNastave() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipNastave(Long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	public Long getId() {
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

	
    
    
}
