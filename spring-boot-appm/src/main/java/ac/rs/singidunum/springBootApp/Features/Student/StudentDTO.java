package ac.rs.singidunum.springBootApp.Features.Student;
import java.util.List;

import ac.rs.singidunum.springBootApp.Features.Adresa.AdresaDTO;
import ac.rs.singidunum.springBootApp.Features.Drzava.DrzavaDTO;
import ac.rs.singidunum.springBootApp.Features.Mesto.MestoDTO;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO;

public class StudentDTO  {
//public class StudentDTO extends RegistrovaniKorisnikDTO {

	private Long id;
	private String jmbg;
	private String telefon;
	private boolean statusStudiranja;
	private boolean predmetiIzabrani;
	private Double stanjeNaRacunu = 0.0;
	
	private RegistrovaniKorisnikDTO korisnik;
	private MestoDTO mestoPrebivalista;
	private AdresaDTO adresa;
	private DrzavaDTO drzava;
	private List<StudentNaGodiniDTO> upisi;
//	private Set<PohadjanjePredmetaDTO> pohadanjePredmeta;
	
	public StudentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public StudentDTO(
			Long id,
			String jmbg, String telefon,boolean statusStudiranja, Double stanjeNaRacunu, boolean predmetiIzabrani) {
		this.id = id;
		this.jmbg = jmbg;
		this.telefon = telefon;
		this.statusStudiranja = statusStudiranja;
		this.stanjeNaRacunu = stanjeNaRacunu;
		this.predmetiIzabrani = predmetiIzabrani;
	}


	public String getJmbg() {
		return jmbg;
	}


	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}


	public String getTelefon() {
		return telefon;
	}


	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}


	public MestoDTO getMestoPrebivalista() {
		return mestoPrebivalista;
	}


	public void setMestoPrebivalista(MestoDTO mestoPrebivalista) {
		this.mestoPrebivalista = mestoPrebivalista;
	}


	public AdresaDTO getAdresa() {
		return adresa;
	}


	public void setAdresa(AdresaDTO adresa) {
		this.adresa = adresa;
	}


	public DrzavaDTO getDrzava() {
		return drzava;
	}


	public void setDrzava(DrzavaDTO drzava) {
		this.drzava = drzava;
	}


//	public Set<PohadjanjePredmetaDTO> getPohadanjePredmeta() {
//		return pohadanjePredmeta;
//	}
//
//
//	public void setPohadanjePredmeta(Set<PohadjanjePredmetaDTO> pohadanjePredmeta) {
//		this.pohadanjePredmeta = pohadanjePredmeta;
//	}

	public List<StudentNaGodiniDTO> getUpisi() {
		return upisi;
	}


	public void setUpisi(List<StudentNaGodiniDTO> upisi) {
		this.upisi = upisi;
	}


	public boolean getStatusStudiranja() {
		return statusStudiranja;
	}


	public void setStatusStudiranja(boolean statusStudiranja) {
		this.statusStudiranja = statusStudiranja;
	}
	

	public boolean isPredmetiIzabrani() {
		return predmetiIzabrani;
	}


	public void setPredmetiIzabrani(boolean predmetiIzabrani) {
		this.predmetiIzabrani = predmetiIzabrani;
	}


	public RegistrovaniKorisnikDTO getKorisnik() {
		return korisnik;
	}


	public void setKorisnik(RegistrovaniKorisnikDTO korisnik) {
		this.korisnik = korisnik;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Double getStanjeNaRacunu() {
		return stanjeNaRacunu;
	}


	public void setStanjeNaRacunu(Double stanjeNaRacunu) {
		this.stanjeNaRacunu = stanjeNaRacunu;
	}
	
	
	
}
