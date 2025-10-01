package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.AdminDTO;

import java.util.Date;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Student.RegistrovaniKorisnikDTO;

public class AdministratorDTO {
    private Long id;
    private String jmbg;
    private String telefon;
    private String poslovniEmail;
    private Date datumkreiranjaNaloga;
    private boolean nalogAktivan;
    

    private RegistrovaniKorisnikDTO korisnik;
    
	public AdministratorDTO(Long id, String jmbg, String telefon, String poslovniEmail, Date datumkreiranjaNaloga, boolean nalogAktivan) {
		super();
		this.id = id;
		this.jmbg = jmbg;
		this.telefon = telefon;
		this.poslovniEmail = poslovniEmail;
		this.datumkreiranjaNaloga = datumkreiranjaNaloga;
		this.nalogAktivan = nalogAktivan;
	}
	public AdministratorDTO() {
		super();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPoslovniEmail() {
		return poslovniEmail;
	}
	public void setPoslovniEmail(String poslovniEmail) {
		this.poslovniEmail = poslovniEmail;
	}
	public Date getDatumkreiranjaNaloga() {
		return datumkreiranjaNaloga;
	}
	public void setDatumkreiranjaNaloga(Date datumkreiranjaNaloga) {
		this.datumkreiranjaNaloga = datumkreiranjaNaloga;
	}
	public boolean isNalogAktivan() {
		return nalogAktivan;
	}
	public void setNalogAktivan(boolean nalogAktivan) {
		this.nalogAktivan = nalogAktivan;
	}
	
	public RegistrovaniKorisnikDTO getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(RegistrovaniKorisnikDTO korisnik) {
		this.korisnik = korisnik;
	}

}
