package ac.rs.singidunum.springBootApp.Features.Adresa;

import ac.rs.singidunum.springBootApp.Features.Mesto.Mesto;
import ac.rs.singidunum.springBootApp.Generics.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Adresa implements BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ulica;
	private String broj;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mesto_id")
	private Mesto mesto;
	
	
	
	public Adresa(Long id, String ulica, String broj) {
		super();
		this.id = id;
		this.ulica = ulica;
		this.broj = broj;
	}	
	public Adresa() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public String getBroj() {
		return broj;
	}
	public void setBroj(String broj) {
		this.broj = broj;
	}
	
	public Mesto getMesto() {
		return mesto;
	}
	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}
	
	
	
	
	
}
