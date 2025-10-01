package ac.rs.singidunum.springBootApp.Model.Predmet;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;



@Entity
public class Kurs implements BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String naziv;
	private String oznaka;
	private Integer trajanje;
	private LocalDateTime datumPocetka;
	private LocalDateTime datumKraja;
	
	@ManyToMany(mappedBy = "kursevi")
	private Set<Predmet> predmeti = new HashSet<>();
	
	public Kurs(Long id, String naziv, Integer trajanje,String oznaka,LocalDateTime datumPocetka,LocalDateTime datumKraja) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.trajanje = trajanje;
		this.oznaka = oznaka;
		this.datumPocetka = datumPocetka;
		this.datumKraja = datumKraja;
	}

	public Kurs() {
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

	public Integer getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Integer trajanje) {
		this.trajanje = trajanje;
	}

	public Set<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(Set<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public LocalDateTime getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(LocalDateTime datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public LocalDateTime getDatumKraja() {
		return datumKraja;
	}

	public void setDatumKraja(LocalDateTime datumKraja) {
		this.datumKraja = datumKraja;
	}
	
	
	
	
	
	
	
	
	

}
