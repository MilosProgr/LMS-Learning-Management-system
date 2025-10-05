package ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ac.rs.singidunum.springBootApp.DTO.Predmet.SemestarDTO;
import ac.rs.singidunum.springBootApp.Features.Nastava.TerminNastave.TerminNastaveDTO;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji.NastavnikNaRealizacijiDTO;
import ac.rs.singidunum.springBootApp.Features.Polaganja.EvaluacijaZnanja.EvaluacijaZnanjaDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;

public class RealizacijaPredmetaDTO {
	Long id;
	
	private PredmetDTO predmet;
	
	//private PohadjanjePredmetaDTO pohadjanjePredmeta;
	
	private List<EvaluacijaZnanjaDTO> evaluacijaZnanja = new ArrayList<>();;
	
	private NastavnikNaRealizacijiDTO nastavnikNaRealizaciji;
	
	private List<TerminNastaveDTO> terminNastave = new ArrayList<>();;
	
//	private IspitniRokDTO rok;
    private Set<SemestarDTO> semestri = new HashSet<>();

	public RealizacijaPredmetaDTO(Long id, PredmetDTO predmet, NastavnikNaRealizacijiDTO nastavnikNaRealizacijiDTO) {
		super();
		this.id = id;
		this.predmet = predmet;
		this.nastavnikNaRealizaciji = nastavnikNaRealizacijiDTO;
	}

	public RealizacijaPredmetaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PredmetDTO getPredmet() {
		return predmet;
	}

	public void setPredmet(PredmetDTO predmet) {
		this.predmet = predmet;
	}

//	public PohadjanjePredmetaDTO getPohadjanjePredmeta() {
//		return pohadjanjePredmeta;
//	}
//
//	public void setPohadjanjePredmeta(PohadjanjePredmetaDTO pohadjanjePredmeta) {
//		this.pohadjanjePredmeta = pohadjanjePredmeta;
//	}

	public List<EvaluacijaZnanjaDTO> getEvaluacijaZnanja() {
		return evaluacijaZnanja;
	}

	public void setEvaluacijaZnanja(List<EvaluacijaZnanjaDTO> evaluacijaZnanja) {
		this.evaluacijaZnanja = evaluacijaZnanja;
	}


	public List<TerminNastaveDTO> getTerminNastave() {
		return terminNastave;
	}

	public void setTerminNastave(List<TerminNastaveDTO> terminNastave) {
		this.terminNastave = terminNastave;
	}

	public NastavnikNaRealizacijiDTO getNastavnikNaRealizaciji() {
		return nastavnikNaRealizaciji;
	}

	public void setNastavnikNaRealizaciji(NastavnikNaRealizacijiDTO nastavnikNaRealizaciji) {
		this.nastavnikNaRealizaciji = nastavnikNaRealizaciji;
	}

	public Set<SemestarDTO> getSemestri() {
		return semestri;
	}

	public void setSemestri(Set<SemestarDTO> semestri) {
		this.semestri = semestri;
	}

//	public IspitniRokDTO getRok() {
//		return rok;
//	}
//
//	public void setRok(IspitniRokDTO rok) {
//		this.rok = rok;
//	}

	
	
	
	
	
	
}
