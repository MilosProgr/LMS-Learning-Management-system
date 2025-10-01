package ac.rs.singidunum.springBootApp.Model.SluzbenikStudentske;

import ac.rs.singidunum.springBootApp.Model.Student.RegistrovaniKorisnik;
import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class SluzbenikStudentske implements BaseEntity<Long>  {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String jmbg;
	
	private String telefon;
	
	@Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean nalogAktivan;
	
	@OneToOne
	private RegistrovaniKorisnik korisnik;
	
	public SluzbenikStudentske() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SluzbenikStudentske(Long id, String jmbg, String telefon, Boolean nalogAktivan) {
		super();
		this.id = id;
		this.jmbg = jmbg;
		this.telefon = telefon;
		this.nalogAktivan = nalogAktivan;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getNalogAktivan() {
		return nalogAktivan;
	}

	public void setNalogAktivan(Boolean nalogAktivan) {
		this.nalogAktivan = nalogAktivan;
	}

	public RegistrovaniKorisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(RegistrovaniKorisnik korisnik) {
		this.korisnik = korisnik;
	} 

}
