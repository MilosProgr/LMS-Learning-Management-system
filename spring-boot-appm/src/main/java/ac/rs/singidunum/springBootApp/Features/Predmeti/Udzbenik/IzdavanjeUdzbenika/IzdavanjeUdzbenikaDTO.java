package ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik.IzdavanjeUdzbenika;


import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgramDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik.UdzbenikDTO;
import ac.rs.singidunum.springBootApp.Features.SluzbenikStudentske.SluzbenikStudentskeDTO;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO;

public class IzdavanjeUdzbenikaDTO {
	private Long id;
	private Integer kolicina;
	private StudentNaGodiniDTO podnosilacZahteva;
	private SluzbenikStudentskeDTO autorizator;
	private UdzbenikDTO udzbenik;
	private Boolean odobreno;
	private Long podnosilacZahtevaId;
	private StudijskiProgramDTO studijskiProgram;


	public IzdavanjeUdzbenikaDTO(Long id, Integer kolicina) {
		super();
		this.id = id;
		this.kolicina = kolicina;
	}

	public Boolean getOdobreno() {
		return odobreno;
	}

	public void setOdobreno(Boolean odobreno) {
		this.odobreno = odobreno;
	}

	public IzdavanjeUdzbenikaDTO() {
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


	public StudentNaGodiniDTO getPodnosilacZahteva() {
		return podnosilacZahteva;
	}


	public void setPodnosilacZahteva(StudentNaGodiniDTO podnosilacZahteva) {
		this.podnosilacZahteva = podnosilacZahteva;
	}


	public SluzbenikStudentskeDTO getAutorizator() {
		return autorizator;
	}


	public void setAutorizator(SluzbenikStudentskeDTO autorizator) {
		this.autorizator = autorizator;
	}

	public UdzbenikDTO getUdzbenik() {
		return udzbenik;
	}

	public void setUdzbenik(UdzbenikDTO udzbenik) {
		this.udzbenik = udzbenik;
	}

	public Long getPodnosilacZahtevaId() {
		return podnosilacZahtevaId;
	}

	public void setPodnosilacZahtevaId(Long podnosilacZahtevaId) {
		this.podnosilacZahtevaId = podnosilacZahtevaId;
	}

	public StudijskiProgramDTO getStudijskiProgram() {
		return studijskiProgram;
	}

	public void setStudijskiProgram(StudijskiProgramDTO studijskiProgram) {
		this.studijskiProgram = studijskiProgram;
	}
}
