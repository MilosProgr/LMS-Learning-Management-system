package ac.rs.singidunum.springBootApp.Model.Student;

import java.util.ArrayList;
import java.util.List;

import ac.rs.singidunum.springBootApp.Features.Adresa.Adresa;
import ac.rs.singidunum.springBootApp.Features.Drzava.Drzava;
import ac.rs.singidunum.springBootApp.Features.Mesto.Mesto;
import ac.rs.singidunum.springBootApp.Model.Predmet.StudijskiProgram;
import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

//STUDENT JE NE UPISAN STUDENT BEZ INDEKSA
@Entity
public class Student implements BaseEntity<Long>{
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String jmbg;
	
	private String telefon;
	
	@Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean statusStudiranja;
	
	@Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean predmetiIzabrani;
	
    private Double stanjeNaRacunu = 0.0;
	
	@OneToOne
	private RegistrovaniKorisnik korisnik;
	
	@ManyToOne
	private Mesto mestoPrebivalista;
	
	@ManyToOne
	private Adresa adresa;
	
	@ManyToOne
	private Drzava drzava;
	
    @OneToMany(mappedBy = "student")
    private List<StudentNaGodini> upisi = new ArrayList<>();
	
	public Student(Long id,
			String jmbg, String telefon,boolean statusStudiranja, Double stanjeNaRacunu, boolean predmetiIzabrani) {
		this.jmbg = jmbg;
		this.telefon = telefon;
		this.statusStudiranja = statusStudiranja;
		this.stanjeNaRacunu = stanjeNaRacunu;
		this.predmetiIzabrani = predmetiIzabrani;
	}

	
    public Student() {
        super();
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

	public List<StudentNaGodini> getUpisi() {
		return upisi;
	}

	public void setUpisi(List<StudentNaGodini> upisi) {
		this.upisi = upisi;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String kontakt) {
		this.telefon = kontakt;
	}

	public Mesto getMestoPrebivalista() {
		return mestoPrebivalista;
	}

	public void setMestoPrebivalista(Mesto mestoPrebivalista) {
		this.mestoPrebivalista = mestoPrebivalista;
	}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	public Drzava getDrzava() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
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


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RegistrovaniKorisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(RegistrovaniKorisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Double getStanjeNaRacunu() {
		return stanjeNaRacunu;
	}

	public void setStanjeNaRacunu(Double stanjeNaRacunu) {
		this.stanjeNaRacunu = stanjeNaRacunu;
	}
	
	
	
}
