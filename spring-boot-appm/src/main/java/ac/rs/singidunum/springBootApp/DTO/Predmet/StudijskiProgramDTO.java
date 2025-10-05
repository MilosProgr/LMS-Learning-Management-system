package ac.rs.singidunum.springBootApp.DTO.Predmet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ac.rs.singidunum.springBootApp.DTO.Student.GodinaStudijaDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.StudentDTO;
import ac.rs.singidunum.springBootApp.Features.Fakultet.FakultetDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;

public class StudijskiProgramDTO {
	
	private Long id;
	
	private String naziv;
	
	private FakultetDTO fakultet;
	
	private Set<PredmetDTO> predmeti = new HashSet<>();
	
	private GodinaStudijaDTO godinaStudija;
	
	private List<StudentDTO> studenti = new ArrayList<>();

	public StudijskiProgramDTO(Long id, String naziv,  FakultetDTO fakultet, GodinaStudijaDTO godinaStudija) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.fakultet = fakultet;
		this.godinaStudija = godinaStudija;
	}

	public StudijskiProgramDTO(Long id, String naziv, GodinaStudijaDTO godinaStudija) {
		this.id = id;
		this.naziv = naziv;
		this.godinaStudija = godinaStudija;
	}

	// 2-parameter constructor (just id and naziv)
	public StudijskiProgramDTO(Long id, String naziv) {
		this.id = id;
		this.naziv = naziv;
	}

	public StudijskiProgramDTO() {
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

	public FakultetDTO getFakultet() {
		return fakultet;
	}

	public void setFakultet(FakultetDTO fakultet) {
		this.fakultet = fakultet;
	}

	public Set<PredmetDTO> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(Set<PredmetDTO> predmeti) {
		this.predmeti = predmeti;
	}

	public GodinaStudijaDTO getGodinaStudija() {
		return godinaStudija;
	}

	public void setGodinaStudija(GodinaStudijaDTO godinaStudija) {
		this.godinaStudija = godinaStudija;
	}

	public List<StudentDTO> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<StudentDTO> studenti) {
		this.studenti = studenti;
	}
	
	


	
	
	
	
	
	
	
	
	
}
