package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Sifarnik;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Sifra implements BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tekst;
	
    @Override
    public Long getId() {
        return this.id;
    }

	public Sifra() {
		super();
	}

	public Sifra(Long id, String tekst) {
		super();
		this.id = id;
		this.tekst = tekst;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}