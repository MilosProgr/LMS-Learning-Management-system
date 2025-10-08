package ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik.IzdavanjeUdzbenika;



import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgram;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik.Udzbenik;
import ac.rs.singidunum.springBootApp.Features.SluzbenikStudentske.SluzbenikStudentske;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodini;
import ac.rs.singidunum.springBootApp.Generics.base.BaseEntity;
import jakarta.persistence.*;

@Entity
public class IzdavanjeUdzbenika implements BaseEntity<Long>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer kolicina;
	
	@ManyToOne
	private StudentNaGodini podnosilacZahteva;
	
	@ManyToOne
	private SluzbenikStudentske autorizator;

	@Column(name = "odobreno")
	private Boolean odobreno = null;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "udzbenik_id")  // this will create udzbenik_id column
	private Udzbenik udzbenik;

    @ManyToOne
    @JoinColumn(name = "studijski_program_id")
    private StudijskiProgram studijskiProgram;

	public IzdavanjeUdzbenika(Long id, Integer kolicina) {
		super();
		this.id = id;
		this.kolicina = kolicina;
	}

	public IzdavanjeUdzbenika() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getKolicina() {
		return kolicina;
	}

	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}

	public StudentNaGodini getPodnosilacZahteva() {
		return podnosilacZahteva;
	}

	public void setPodnosilacZahteva(StudentNaGodini podnosilacZahteva) {
		this.podnosilacZahteva = podnosilacZahteva;
	}

	public SluzbenikStudentske getAutorizator() {
		return autorizator;
	}

	public void setAutorizator(SluzbenikStudentske autorizator) {
		this.autorizator = autorizator;
	}

	public Boolean getOdobreno() {
		return odobreno;
	}

	public void setOdobreno(Boolean odobreno) {
		this.odobreno = odobreno;
	}

	public Udzbenik getUdzbenik() {
		return udzbenik;
	}

	public void setUdzbenik(Udzbenik udzbenik) {
		this.udzbenik = udzbenik;
	}

    public StudijskiProgram getStudijskiProgram() {
        return studijskiProgram;
    }

    public void setStudijskiProgram(StudijskiProgram studijskiProgram) {
        this.studijskiProgram = studijskiProgram;
    }
}
