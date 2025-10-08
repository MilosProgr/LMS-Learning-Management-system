package ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik;

import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija.GodinaStudijaDTO;

public class UdzbenikDTO {
	private Long id;
	private GodinaStudijaDTO godinaStudija;
	private String naziv;
	private PredmetDTO predmet;
//	private IzdavanjeUdzbenikaDTO izdavanjeUdzbenika;
	public UdzbenikDTO(Long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}
	public UdzbenikDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public GodinaStudijaDTO getGodinaStudija() {
		return godinaStudija;
	}
	public void setGodinaStudija(GodinaStudijaDTO godinaStudija) {
		this.godinaStudija = godinaStudija;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public PredmetDTO getPredmet() {
		return predmet;
	}
	public void setPredmet(PredmetDTO predmet) {
		this.predmet = predmet;
	}
//	public IzdavanjeUdzbenikaDTO getIzdavanjeUdzbenika() {
//		return izdavanjeUdzbenika;
//	}
//	public void setIzdavanjeUdzbenika(IzdavanjeUdzbenikaDTO izdavanjeUdzbenika) {
//		this.izdavanjeUdzbenika = izdavanjeUdzbenika;
//	}
	
	
	
	
	
	
}
