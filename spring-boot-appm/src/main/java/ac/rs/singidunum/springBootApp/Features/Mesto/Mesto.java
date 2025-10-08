package ac.rs.singidunum.springBootApp.Features.Mesto;

import java.util.ArrayList;
import java.util.List;

import ac.rs.singidunum.springBootApp.Features.Adresa.Adresa;
import ac.rs.singidunum.springBootApp.Features.Drzava.Drzava;
import ac.rs.singidunum.springBootApp.Generics.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Mesto implements BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String naziv;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drzava_id")
    private Drzava drzava;
//    
    @OneToMany(mappedBy = "mesto")
    private List<Adresa> adrese = new ArrayList<>();
    
    @Transient
    private Long drzavaId;
    
    
    
    
	public Mesto(Long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	public Mesto() {
		super();
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



	public List<Adresa> getAdrese() {
		return adrese;
	}

	public void setAdrese(List<Adresa> adrese) {
		this.adrese = adrese;
	}

	public Long getDrzavaId() {
		return drzavaId;
	}

	public void setDrzavaId(Long drzavaId) {
	    this.drzavaId = drzavaId;
	}

	public Drzava getDrzava() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}
	
	
	

	
	
	
	
	
	
	
	
	
    
    

}
