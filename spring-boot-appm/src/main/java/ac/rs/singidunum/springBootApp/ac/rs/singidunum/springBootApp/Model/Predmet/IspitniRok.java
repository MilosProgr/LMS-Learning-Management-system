package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class IspitniRok implements BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String naziv;
	private LocalDateTime pocetak;
	private LocalDateTime kraj;
	private Boolean redovan;
	
	@OneToMany(mappedBy = "ispitniRok")
	private List<PrijavljeniIspit> prijavljeniIspiti = new ArrayList<>();
	

	
	
	
	public IspitniRok(Long id, String naziv, LocalDateTime pocetak, LocalDateTime kraj,Boolean redovan) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.redovan = redovan;
	}

	public IspitniRok() {
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

	public LocalDateTime getPocetak() {
		return pocetak;
	}

	public void setPocetak(LocalDateTime pocetak) {
		this.pocetak = pocetak;
	}

	public LocalDateTime getKraj() {
		return kraj;
	}

	public void setKraj(LocalDateTime kraj) {
		this.kraj = kraj;
	}

	

	public Boolean getRedovan() {
		return redovan;
	}

	public void setRedovan(Boolean redovan) {
		this.redovan = redovan;
	}

	public List<PrijavljeniIspit> getPrijavljeniIspiti() {
		return prijavljeniIspiti;
	}

	public void setPrijavljeniIspiti(List<PrijavljeniIspit> prijavljeniIspiti) {
		this.prijavljeniIspiti = prijavljeniIspiti;
	}
	
	
	
	
	

	
	
	
	
	
	
	
}
