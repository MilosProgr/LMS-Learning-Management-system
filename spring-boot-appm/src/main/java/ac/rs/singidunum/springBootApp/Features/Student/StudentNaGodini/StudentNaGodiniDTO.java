package ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ac.rs.singidunum.springBootApp.Features.Predmeti.PrijavljeniIspit.PrijavljeniIspitDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.PrijavljeniIspit.PrijavljeniIspitDTO.PrijavljeniIspitDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgramDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgramDTO.StudijskiProgramDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentDTO;
import ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija.GodinaStudijaDTO;
import ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija.GodinaStudijaDTO.GodinaStudijaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.PohadjanjePredmeta.PohadjanjePredmetaDTO;
import ac.rs.singidunum.springBootApp.Features.Student.PohadjanjePredmeta.PohadjanjePredmetaDTO.PohadjanjePredmetaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentDTO.StudentDTORecord;


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
	
	public record StudentNaGodiniDTORecord(
			 Long id,
			 LocalDateTime datumUpisa,
			 String brojIndeksa,
			 StudentDTORecord student,
			 GodinaStudijaDTORecord godinaStudija,
			 Double prosek,
			
			 List<PrijavljeniIspitDTORecord> prijavljenIspit,
			 StudijskiProgramDTORecord studijskiProgram,
			 List<PohadjanjePredmetaDTORecord> pohadjanja
			) {}
	
	
	
	
	


}
