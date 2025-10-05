package ac.rs.singidunum.springBootApp.Model.Udzbenici;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.Predmet;
import ac.rs.singidunum.springBootApp.Model.Student.GodinaStudija;
import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Udzbenik implements BaseEntity<Long>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private GodinaStudija godinaStudija;
	
	private String naziv;
	
	@ManyToOne
	private Predmet predmet;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "izdavanje_udzbenika_id")
//	@JsonIgnore
//	private IzdavanjeUdzbenika izdavanjeUdzbenika;


	public Udzbenik(Long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	public Udzbenik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GodinaStudija getGodinaStudija() {
		return godinaStudija;
	}

	public void setGodinaStudija(GodinaStudija godinaStudija) {
		this.godinaStudija = godinaStudija;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

//	public IzdavanjeUdzbenika getIzdavanjeUdzbenika() {
//		return izdavanjeUdzbenika;
//	}
//
//	public void setIzdavanjeUdzbenika(IzdavanjeUdzbenika izdavanjeUdzbenika) {
//		this.izdavanjeUdzbenika = izdavanjeUdzbenika;
//	}
	
	
	
	
	
}
