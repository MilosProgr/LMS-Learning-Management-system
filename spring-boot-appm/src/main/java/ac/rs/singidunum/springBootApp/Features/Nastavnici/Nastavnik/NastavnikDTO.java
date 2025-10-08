package ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik;

import java.util.Set;

import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.Features.Zvanje.NaucnaOblast.NaucnaOblastDTO;
import ac.rs.singidunum.springBootApp.Features.Zvanje.Zvanje.ZvanjeDTO;

//public class NastavnikDTO extends RegistrovaniKorisnikDTO {
public class NastavnikDTO {

	private Long id;
	private String biografija;
	private String jmbg;
	private String telefon;
	private String poslovniMail;
	private int brojSlobodnihDana;
	private int brojIskoristenihDana;
	private RegistrovaniKorisnikDTO korisnik;

	 private Set<ZvanjeDTO> zvanja;
	 private Set<NaucnaOblastDTO> naucneOblasti;

	public NastavnikDTO() {
		super();
	}
	
	public NastavnikDTO(Long id, String biografija, String jmbg, String telefon, String poslovniMail,
			int brojSlobodnihDana, int brojIskoristenihDana) {
		super();
		this.id = id;
		this.biografija = biografija;
		this.jmbg = jmbg;
		this.telefon = telefon;
		this.poslovniMail = poslovniMail;
		this.brojSlobodnihDana = brojSlobodnihDana;
		this.brojIskoristenihDana = brojIskoristenihDana;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<ZvanjeDTO> getZvanja() {
		return zvanja;
	}

	public void setZvanja(Set<ZvanjeDTO> zvanja) {
		this.zvanja = zvanja;
	}

	public Set<NaucnaOblastDTO> getNaucneOblasti() {
		return naucneOblasti;
	}

	public void setNaucneOblasti(Set<NaucnaOblastDTO> naucneOblasti) {
		this.naucneOblasti = naucneOblasti;
	}

	public String getBiografija() {
		return biografija;
	}

	public void setBiografija(String biografija) {
		this.biografija = biografija;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public int getBrojSlobodnihDana() {
		return brojSlobodnihDana;
	}

	public void setBrojSlobodnihDana(int brojSlobodnihDana) {
		this.brojSlobodnihDana = brojSlobodnihDana;
	}

	public int getBrojIskoristenihDana() {
		return brojIskoristenihDana;
	}

	public void setBrojIskoristenihDana(int brojIskoristenihDana) {
		this.brojIskoristenihDana = brojIskoristenihDana;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getPoslovniMail() {
		return poslovniMail;
	}

	public void setPoslovniMail(String poslovniMail) {
		this.poslovniMail = poslovniMail;
	}

	public RegistrovaniKorisnikDTO getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(RegistrovaniKorisnikDTO korisnik) {
		this.korisnik = korisnik;
	}
	
}
