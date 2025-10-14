package ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija;
//GodinaStudija moze imati vise studente na godini

import java.util.List;
import java.util.Set;

import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO.PredmetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO;

public class GodinaStudijaDTO {
	private Long id;
	private Integer godina;
	
	
	private Set<StudentNaGodiniDTO> studentiNaGodini;
	
	private List<PredmetDTO> predmeti;

	public GodinaStudijaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GodinaStudijaDTO(Long id, Integer godina) {
		super();
		this.id = id;
		this.godina = godina;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getGodina() {
		return godina;
	}

	public void setGodina(Integer godina) {
		this.godina = godina;
	}

	

	public Set<StudentNaGodiniDTO> getStudentiNaGodini() {
		return studentiNaGodini;
	}

	public void setStudentiNaGodini(Set<StudentNaGodiniDTO> studentiNaGodini) {
		this.studentiNaGodini = studentiNaGodini;
	}

	public List<PredmetDTO> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<PredmetDTO> predmeti) {
		this.predmeti = predmeti;
	}
	
	
	public record GodinaStudijaDTORecord(
			 Long id,
			 Integer godina,
			
			
			 Set<StudentNaGodiniDTO> studentiNaGodini,
			
			 List<PredmetDTORecord> predmeti
			) {}
	
	
	
	
}
