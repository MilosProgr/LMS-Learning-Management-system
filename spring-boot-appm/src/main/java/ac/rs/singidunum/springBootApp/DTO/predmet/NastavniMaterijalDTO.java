package ac.rs.singidunum.springBootApp.DTO.predmet;

import ac.rs.singidunum.springBootApp.DTO.Nastavnici.NastavnikDTO;
import ac.rs.singidunum.springBootApp.DTO.SluzbenikStudentske.SluzbenikStudentskeDTO;

public class NastavniMaterijalDTO {
	private Long id;
	private String naziv;
	private Boolean odobreno;
	private Integer kolicina;
	private NastavnikDTO autorizator;
	private SluzbenikStudentskeDTO podnosilacZahteva;


	public NastavniMaterijalDTO(Long id, String naziv,Boolean odobreno,Integer kolicina) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.odobreno = odobreno;
		this.kolicina = kolicina;
	}

	public NastavniMaterijalDTO() {
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

	public NastavnikDTO getAutorizator() {
		return autorizator;
	}

	public void setAutorizator(NastavnikDTO autorizator) {
		this.autorizator = autorizator;
	}

	public SluzbenikStudentskeDTO getPodnosilacZahteva() {
		return podnosilacZahteva;
	}

	public void setPodnosilacZahteva(SluzbenikStudentskeDTO podnosilacZahteva) {
		this.podnosilacZahteva = podnosilacZahteva;
	}

	public Integer getKolicina() {
		return kolicina;
	}

	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}
	
	
	


	
	
}
