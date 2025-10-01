package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Nastavnici;

import java.time.LocalDate;

public class FakultetRequest {

	private Long id;                 // null = create; != null = update
    private String naziv;
    private String opis;
    private String telefon;

    private Long dekanId;            // obavezno
    private Long univerzitetId;      // obavezno

    // Adresa
    private String ulica;            // obavezno
    private String broj;             // obavezno

    // Drzava/Mesto (id ili naziv)
    private Long drzavaId;
    private String drzavaNaziv;
    private Long mestoId;
    private String mestoNaziv;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public Long getDekanId() {
		return dekanId;
	}
	public void setDekanId(Long dekanId) {
		this.dekanId = dekanId;
	}
	public Long getUniverzitetId() {
		return univerzitetId;
	}
	public void setUniverzitetId(Long univerzitetId) {
		this.univerzitetId = univerzitetId;
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
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}


    
}
