package ac.rs.singidunum.springBootApp.Model.Predmet;



import ac.rs.singidunum.springBootApp.Model.Nastavnici.Nastavnik;
import ac.rs.singidunum.springBootApp.Model.SluzbenikStudentske.SluzbenikStudentske;
import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class NastavniMaterijal implements BaseEntity<Long>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String naziv;
	
	private Boolean odobreno;
	
	private Integer kolicina;
	
	@ManyToOne
	private Nastavnik autorizator;
	
	@ManyToOne
	private SluzbenikStudentske podnosilacZahteva;
	
	

	public NastavniMaterijal(Long id, String naziv,Boolean odobreno,Integer kolicina) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.odobreno = odobreno;
		this.kolicina = kolicina;
	}

	public NastavniMaterijal() {
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

	public Boolean getOdobreno() {
		return odobreno;
	}

	public void setOdobreno(Boolean odobreno) {
		this.odobreno = odobreno;
	}

	public Nastavnik getAutorizator() {
		return autorizator;
	}

	public void setAutorizator(Nastavnik autorizator) {
		this.autorizator = autorizator;
	}

	public SluzbenikStudentske getPodnosilacZahteva() {
		return podnosilacZahteva;
	}

	public void setPodnosilacZahteva(SluzbenikStudentske podnosilacZahteva) {
		this.podnosilacZahteva = podnosilacZahteva;
	}

	public Integer getKolicina() {
		return kolicina;
	}

	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}

	
	


	
	
	


	
	
	
	
}
