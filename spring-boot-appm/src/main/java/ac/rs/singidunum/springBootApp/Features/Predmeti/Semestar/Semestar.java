package ac.rs.singidunum.springBootApp.Features.Predmeti.Semestar;

import java.time.LocalDateTime;

import ac.rs.singidunum.springBootApp.Generics.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Semestar implements BaseEntity<Long>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime datumPocetka;
	private LocalDateTime datumKraja;
	private String tip;
	
	public Semestar(Long id, LocalDateTime datumPocetka, LocalDateTime datumKraja,String tip) {
		super();
		this.id = id;
		this.datumPocetka = datumPocetka;
		this.datumKraja = datumKraja;
		this.tip = tip;
	}

	public Semestar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
	
	
	
	
	
}
