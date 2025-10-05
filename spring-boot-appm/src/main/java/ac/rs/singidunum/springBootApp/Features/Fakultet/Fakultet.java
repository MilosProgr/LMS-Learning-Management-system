package ac.rs.singidunum.springBootApp.Features.Fakultet;



import java.util.ArrayList;
import java.util.List;

import ac.rs.singidunum.springBootApp.Features.Adresa.Adresa;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.Nastavnik;
import ac.rs.singidunum.springBootApp.Features.Univerzitet.Univerzitet;
import ac.rs.singidunum.springBootApp.Model.Predmet.StudijskiProgram;
import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Fakultet implements BaseEntity<Long> {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String naziv;
    @ManyToOne
    private Adresa adresa;
    @ManyToOne(fetch = FetchType.LAZY)
    private Univerzitet univerzitet;
    @OneToOne
    private Nastavnik dekan;
 
    private String telefon;
    
	@Column(columnDefinition = "TEXT")
    private String opis;
    
    @OneToMany(mappedBy = "fakultet",cascade = CascadeType.ALL)
    private List<StudijskiProgram> studijskiProgrami;


	
	public Fakultet(Long id, String naziv,String telefon) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.telefon = telefon;
	}

	public Fakultet() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	public Univerzitet getUniverzitet() {
		return univerzitet;
	}

	public void setUniverzitet(Univerzitet univerzitet) {
		this.univerzitet = univerzitet;
	}

	public List<StudijskiProgram> getProgrami() {
		return studijskiProgrami;
	}

	public void setProgrami(List<StudijskiProgram> programi) {
		this.studijskiProgrami = programi;
	}

	public Nastavnik getDekan() {
		return dekan;
	}

	public void setDekan(Nastavnik dekan) {
		this.dekan = dekan;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public List<StudijskiProgram> getStudijskiProgrami() {
		return studijskiProgrami;
	}

	public void setStudijskiProgrami(List<StudijskiProgram> studijskiProgrami) {
		this.studijskiProgrami = studijskiProgrami;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	

	

	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
}
