package ac.rs.singidunum.springBootApp.Features.SluzbenikStudentske;

import ac.rs.singidunum.springBootApp.DTO.Student.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.Model.Student.RegistrovaniKorisnik;

public class SluzbenikStudentskeDTO  {
	
//public class SluzbenikStudentskeDTO extends RegistrovaniKorisnikDTO {

	private Long id;
	private String jmbg;
	private String telefon;
	private Boolean nalogAktivan = true;
	private RegistrovaniKorisnikDTO korisnik;

	public SluzbenikStudentskeDTO(Long id, String jmbg, String telefon, Boolean nalogAktivan) {
		super();
		this.id = id;
		this.jmbg = jmbg;
		this.telefon = telefon;
		this.nalogAktivan = nalogAktivan;
	}

	public SluzbenikStudentskeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public Boolean getNalogAktivan() {
		return nalogAktivan;
	}

	public void setNalogAktivan(Boolean nalogAktivan) {
		this.nalogAktivan = nalogAktivan;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RegistrovaniKorisnikDTO getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(RegistrovaniKorisnikDTO korisnik) {
		this.korisnik = korisnik;
	}
	
	
}
