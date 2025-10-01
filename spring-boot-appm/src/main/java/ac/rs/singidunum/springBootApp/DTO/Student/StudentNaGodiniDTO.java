package ac.rs.singidunum.springBootApp.DTO.Student;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ac.rs.singidunum.springBootApp.DTO.predmet.StudijskiProgramDTO;
import ac.rs.singidunum.springBootApp.Model.Predmet.PrijavljeniIspit;
import ac.rs.singidunum.springBootApp.Model.Student.PohadjanjePredmeta;

public class StudentNaGodiniDTO {
	private Long id;
	private LocalDateTime datumUpisa;
	private String brojIndeksa;
	private StudentDTO student;
	private GodinaStudijaDTO godinaStudija;
	private Double prosek = 0.0;
	
	private List<PrijavljeniIspitDTO> prijavljenIspit;
	private StudijskiProgramDTO studijskiProgram;
	private List<PohadjanjePredmetaDTO> pohadjanja = new ArrayList<>();
	
	public StudentNaGodiniDTO() {
		super();
	}

	public StudentNaGodiniDTO(Long id, LocalDateTime datumUpisa, 
			String brojIndeksa,Double prosek) {
		super();
		this.id = id;
		this.datumUpisa = datumUpisa;
		this.brojIndeksa = brojIndeksa;
		this.prosek = prosek;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDatumUpisa() {
		return datumUpisa;
	}

	public void setDatumUpisa(LocalDateTime datumUpisa) {
		this.datumUpisa = datumUpisa;
	}

	public String getBrojIndeksa() {
		return brojIndeksa;
	}

	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

	public GodinaStudijaDTO getGodinaStudija() {
		return godinaStudija;
	}

	public void setGodinaStudija(GodinaStudijaDTO godinaStudija) {
		this.godinaStudija = godinaStudija;
	}

	public List<PrijavljeniIspitDTO> getPrijavljenIspit() {
		return prijavljenIspit;
	}

	public void setPrijavljenIspit(List<PrijavljeniIspitDTO> prijavljenIspit) {
		this.prijavljenIspit = prijavljenIspit;
	}

	public Double getProsek() {
		return prosek;
	}

	public void setProsek(Double prosek) {
		this.prosek = prosek;
	}

	public StudijskiProgramDTO getStudijskiProgram() {
		return studijskiProgram;
	}

	public void setStudijskiProgram(StudijskiProgramDTO studijskiProgram) {
		this.studijskiProgram = studijskiProgram;
	}

	public List<PohadjanjePredmetaDTO> getPohadjanja() {
		return pohadjanja;
	}

	public void setPohadjanja(List<PohadjanjePredmetaDTO> pohadjanja) {
		this.pohadjanja = pohadjanja;
	}
	
	
	
	
	
	


}
