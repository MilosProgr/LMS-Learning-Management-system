package ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik;

import jakarta.persistence.*;

import java.util.Set;

import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnik;
import ac.rs.singidunum.springBootApp.Features.Zvanje.NaucnaOblast.NaucnaOblast;
import ac.rs.singidunum.springBootApp.Features.Zvanje.Zvanje.Zvanje;
import ac.rs.singidunum.springBootApp.Generics.base.BaseEntity;

@Entity
public class Nastavnik implements BaseEntity<Long> {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String biografija;
	
	@Column(unique = true)
	private String jmbg;
	
	private String poslovniMail;
	private String telefon;
	private int brojSlobodnihDana;
	private int brojIskoristenihDana;
	
    @OneToOne
    private RegistrovaniKorisnik korisnik;

	@OneToMany(mappedBy = "nastavnik")
	private Set<Zvanje> zvanja;

	@ManyToMany
	@JoinTable(
			name = "nastavnik_naucna_oblast",
			joinColumns = @JoinColumn(name = "nastavnik_id"),
			inverseJoinColumns = @JoinColumn(name = "naucna_oblast_id")
	)
	private Set<NaucnaOblast> naucneOblasti;

	public Nastavnik() {
		super();
	}

	public Nastavnik(Long id,
			String biografija, String jmbg,
			int brojSlobodnihDana, int brojIskoristenihDana,
			String poslovniMail,String telefon) {
		this.biografija = biografija;
		this.jmbg = jmbg;
		this.brojSlobodnihDana = brojSlobodnihDana;
		this.brojIskoristenihDana = brojIskoristenihDana;
		this.telefon = telefon;
		this.poslovniMail = poslovniMail;
	}
	
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<Zvanje> getZvanja() {
		return zvanja;
	}

	public void setZvanja(Set<Zvanje> zvanja) {
		this.zvanja = zvanja;
	}

	public Set<NaucnaOblast> getNaucneOblasti() {
		return naucneOblasti;
	}

	public void setNaucneOblasti(Set<NaucnaOblast> naucneOblasti) {
		this.naucneOblasti = naucneOblasti;
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

	public String getPoslovniMail() {
		return poslovniMail;
	}

	public void setPoslovniMail(String poslovniMail) {
		this.poslovniMail = poslovniMail;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public RegistrovaniKorisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(RegistrovaniKorisnik korisnik) {
		this.korisnik = korisnik;
	}
	
}
