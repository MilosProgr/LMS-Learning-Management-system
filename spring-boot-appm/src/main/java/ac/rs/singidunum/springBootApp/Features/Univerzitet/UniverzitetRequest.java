package ac.rs.singidunum.springBootApp.Features.Univerzitet;

import java.time.LocalDate;

public class UniverzitetRequest {
	  private Long id;                 // null za kreiranje, != null za izmenu
	    private String naziv;
	    private String opis;
	    private LocalDate datumOsnivanja;

	    private Long rektorId;           // obavezno (id nastavnika)

	    // Adresa
	    private String ulica;            // obavezno
	    private String broj;             // obavezno

	    // Mesto/Država -> može ID ili naziv
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
		public LocalDate getDatumOsnivanja() {
			return datumOsnivanja;
		}
		public void setDatumOsnivanja(LocalDate datumOsnivanja) {
			this.datumOsnivanja = datumOsnivanja;
		}
		public Long getRektorId() {
			return rektorId;
		}
		public void setRektorId(Long rektorId) {
			this.rektorId = rektorId;
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


	    
	}
