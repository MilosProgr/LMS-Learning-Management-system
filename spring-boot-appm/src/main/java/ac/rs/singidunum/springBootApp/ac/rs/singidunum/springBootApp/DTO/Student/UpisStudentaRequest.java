package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Student;

public class UpisStudentaRequest {

 private Long korisnikId;

 private String jmbg;

 private String telefon;

 private String ulica;

 private String broj;

 // Mesto za ADRESU - može preko id-a ili naziva (ako nema, kreira se)
 private Long mestoId;
 private String mestoNaziv;

 // Država: ili id ili naziv
 private Long drzavaId;
 private String drzavaNaziv;

 // Mesto PREBIVALIŠTA - takođe dozvoljavamo id ili naziv (ako nema, kreira se)
 private Long mestoPrebivalistaId;
 private String mestoPrebivalistaNaziv;
 
 
	public Long getKorisnikId() {
		return korisnikId;
	}
	public void setKorisnikId(Long korisnikId) {
		this.korisnikId = korisnikId;
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
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public String getBroj() {
		return broj;
	}
	public void setBroj(String broj) {
		this.broj = broj;
	}
	public Long getMestoId() {
		return mestoId;
	}
	public void setMestoId(Long mestoId) {
		this.mestoId = mestoId;
	}
	public String getMestoNaziv() {
		return mestoNaziv;
	}
	public void setMestoNaziv(String mestoNaziv) {
		this.mestoNaziv = mestoNaziv;
	}
	public Long getDrzavaId() {
		return drzavaId;
	}
	public void setDrzavaId(Long drzavaId) {
		this.drzavaId = drzavaId;
	}
	
	public String getDrzavaNaziv() {
		return drzavaNaziv;
	}
	public void setDrzavaNaziv(String drzavaNaziv) {
		this.drzavaNaziv = drzavaNaziv;
	}
	public Long getMestoPrebivalistaId() {
		return mestoPrebivalistaId;
	}
	public void setMestoPrebivalistaId(Long mestoPrebivalistaId) {
		this.mestoPrebivalistaId = mestoPrebivalistaId;
	}
	public String getMestoPrebivalistaNaziv() {
		return mestoPrebivalistaNaziv;
	}
	public void setMestoPrebivalistaNaziv(String mestoPrebivalistaNaziv) {
		this.mestoPrebivalistaNaziv = mestoPrebivalistaNaziv;
	}
 
}



