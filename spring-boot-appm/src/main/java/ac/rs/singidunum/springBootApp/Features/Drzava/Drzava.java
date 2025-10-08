package ac.rs.singidunum.springBootApp.Features.Drzava;

import java.util.Set;

import ac.rs.singidunum.springBootApp.Features.Mesto.Mesto;
import ac.rs.singidunum.springBootApp.Generics.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Drzava implements BaseEntity<Long> {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private String naziv;
	
	@OneToMany(mappedBy = "drzava")
	private Set<Mesto> mesta;

	public Drzava(Long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	public Drzava() {
		super();
		// TODO Auto-generated constructor stub
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

	public Set<Mesto> getMesta() {
		return mesta;
	}

	public void setMesta(Set<Mesto> mesta) {
		this.mesta = mesta;
	}
	
	
	
	
	
	
}
