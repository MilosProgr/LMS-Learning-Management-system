package ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik;

import java.util.Set;

import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Zvanje.NaucnaOblast.NaucnaOblastDTO.NaucnaOblastDTORecord;
import ac.rs.singidunum.springBootApp.Features.Zvanje.Zvanje.ZvanjeDTO.ZvanjeDTORecord;

//public class NastavnikDTO extends RegistrovaniKorisnikDTO {
public class NastavnikDTO {

//	private Long id;
//	private String biografija;
//	private String jmbg;
//	private String telefon;
//	private String poslovniMail;
//	private int brojSlobodnihDana;
//	private int brojIskoristenihDana;
//	private RegistrovaniKorisnikDTORecord korisnik;
//
//	 private Set<ZvanjeDTORecord> zvanja;
//	 private Set<NaucnaOblastDTORecord> naucneOblasti;
//
//	public NastavnikDTO() {
//		super();
//	}
//	
//	public NastavnikDTO(Long id, String biografija, String jmbg, String telefon, String poslovniMail,
//			int brojSlobodnihDana, int brojIskoristenihDana) {
//		super();
//		this.id = id;
//		this.biografija = biografija;
//		this.jmbg = jmbg;
//		this.telefon = telefon;
//		this.poslovniMail = poslovniMail;
//		this.brojSlobodnihDana = brojSlobodnihDana;
//		this.brojIskoristenihDana = brojIskoristenihDana;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Set<ZvanjeDTORecord> getZvanja() {
//		return zvanja;
//	}
//
//	public void setZvanja(Set<ZvanjeDTORecord> zvanja) {
//		this.zvanja = zvanja;
//	}
//
//	public Set<NaucnaOblastDTORecord> getNaucneOblasti() {
//		return naucneOblasti;
//	}
//
//	public void setNaucneOblasti(Set<NaucnaOblastDTORecord> naucneOblasti) {
//		this.naucneOblasti = naucneOblasti;
//	}
//
//	public String getBiografija() {
//		return biografija;
//	}
//
//	public void setBiografija(String biografija) {
//		this.biografija = biografija;
//	}
//
//	public String getJmbg() {
//		return jmbg;
//	}
//
//	public void setJmbg(String jmbg) {
//		this.jmbg = jmbg;
//	}
//
//	public int getBrojSlobodnihDana() {
//		return brojSlobodnihDana;
//	}
//
//	public void setBrojSlobodnihDana(int brojSlobodnihDana) {
//		this.brojSlobodnihDana = brojSlobodnihDana;
//	}
//
//	public int getBrojIskoristenihDana() {
//		return brojIskoristenihDana;
//	}
//
//	public void setBrojIskoristenihDana(int brojIskoristenihDana) {
//		this.brojIskoristenihDana = brojIskoristenihDana;
//	}
//
//	public String getTelefon() {
//		return telefon;
//	}
//
//	public void setTelefon(String telefon) {
//		this.telefon = telefon;
//	}
//
//	public String getPoslovniMail() {
//		return poslovniMail;
//	}
//
//	public void setPoslovniMail(String poslovniMail) {
//		this.poslovniMail = poslovniMail;
//	}
//
//	public RegistrovaniKorisnikDTORecord getKorisnik() {
//		return korisnik;
//	}
//
//	public void setKorisnik(RegistrovaniKorisnikDTORecord korisnik) {
//		this.korisnik = korisnik;
//	}
	
	public record NastavnikDTORecord(
			 Long id,
			 String biografija,
			 String jmbg,
			 String telefon,
			 String poslovniMail,
			 int brojSlobodnihDana,
			 int brojIskoristenihDana,
			 RegistrovaniKorisnikDTORecord korisnik,

			  Set<ZvanjeDTORecord> zvanja,
			  Set<NaucnaOblastDTORecord> naucneOblasti
			) {}
	
}
