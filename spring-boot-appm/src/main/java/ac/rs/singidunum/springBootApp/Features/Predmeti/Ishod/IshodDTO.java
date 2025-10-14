package ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod;

import java.util.Set;

import ac.rs.singidunum.springBootApp.Features.Predmeti.NastavniMaterijal.NastavniMaterijalDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.NastavniMaterijal.NastavniMaterijalDTO.NastavniMaterijalDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;

public class IshodDTO {
	private Long id;
	
	private String opis;
	
	private boolean polozeno;
	
	private PredmetDTO predmet;
	
	private Set<NastavniMaterijalDTORecord> nastavniMaterijali;
	

	public IshodDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IshodDTO(Long id, String opis, boolean polozeno, PredmetDTO predmet) {
		super();
		this.id = id;
		this.opis = opis;
		this.polozeno = polozeno;
		this.predmet = predmet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	public boolean isPolozeno() {
		return polozeno;
	}

	public void setPolozeno(boolean polozeno) {
		this.polozeno = polozeno;
	}

	public PredmetDTO getPredmet() {
		return predmet;
	}

	public void setPredmet(PredmetDTO predmet) {
		this.predmet = predmet;
	}

	public Set<NastavniMaterijalDTORecord> getNastavniMaterijali() {
		return nastavniMaterijali;
	}

	public void setNastavniMaterijali(Set<NastavniMaterijalDTORecord> nastavniMaterijali) {
		this.nastavniMaterijali = nastavniMaterijali;
	}

	public record IshodDTORecord(
			 Long id,
			 String opis,
			 boolean polozeno,
			 PredmetDTO predmet,
			 Set<NastavniMaterijalDTORecord> nastavniMaterijali
			) {}
	
	
	
	
	
	
	
}
