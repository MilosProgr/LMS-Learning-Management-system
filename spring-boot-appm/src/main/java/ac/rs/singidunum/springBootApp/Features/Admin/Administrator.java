package ac.rs.singidunum.springBootApp.Features.Admin;

import jakarta.persistence.*;
import java.util.Date;

import ac.rs.singidunum.springBootApp.Model.Student.RegistrovaniKorisnik;
import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;

@Entity
public class Administrator implements BaseEntity<Long> {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String jmbg;
    
    private String telefon;
    private String poslovniEmail;
    private Date datumkreiranjaNaloga;
    
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean nalogAktivan;
    
    @OneToOne
    private RegistrovaniKorisnik korisnik;

    public Administrator() {
    }

    public Administrator(Long id, String jmbg, String telefon, String poslovniEmail, Date datumkreiranjaNaloga, boolean nalogAktivan) {
        this.id = id;
        this.jmbg = jmbg;
        this.telefon = telefon;
        this.poslovniEmail = poslovniEmail;
        this.datumkreiranjaNaloga = datumkreiranjaNaloga;
        this.nalogAktivan = nalogAktivan;
    }

    // Getteri i setteri
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


	public RegistrovaniKorisnik getKorisnik() {
		return korisnik;
	}


	public void setKorisnik(RegistrovaniKorisnik korisnik) {
		this.korisnik = korisnik;
	}
    
    
}
