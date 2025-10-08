package ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik;

import java.util.HashSet;
import java.util.Set;


import ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti.ObavestenjeAktivnosti;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermission;

import ac.rs.singidunum.springBootApp.Generics.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;

@Entity
public class RegistrovaniKorisnik implements BaseEntity<Long> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;
    private String email;
    
    public RegistrovaniKorisnik(Long id, String ime, String prezime, String korisnickoIme, String lozinka,
			String email) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.email = email;
	}
    
//    @OneToOne(mappedBy = "korisnik", cascade = CascadeType.ALL)
//    private Student student;
//
//    @OneToOne(mappedBy = "korisnik", cascade = CascadeType.ALL)
//    private Nastavnik nastavnik;
//
//    @OneToOne(mappedBy = "korisnik", cascade = CascadeType.ALL)
//    private Administrator administrator;
//    
//    @OneToOne(mappedBy = "korisnik", cascade = CascadeType.ALL)
//    private SluzbenikStudentske sluzbenik;
    
    @OneToMany(mappedBy = "registrovaniKorisnik")
    private Set<ObavestenjeAktivnosti> obavestenjaAktivnosti = new HashSet<>();
    
    @OneToMany(mappedBy = "korisnik", orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<UserPermission> pravoPristupa = new HashSet<>();


	public RegistrovaniKorisnik() {
		super();
	}

	
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	// Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


	public Set<ObavestenjeAktivnosti> getObavestenjaAktivnosti() {
		return obavestenjaAktivnosti;
	}


	public void setObavestenjaAktivnosti(Set<ObavestenjeAktivnosti> obavestenjaAktivnosti) {
		this.obavestenjaAktivnosti = obavestenjaAktivnosti;
	}


	public Set<UserPermission> getPravoPristupa() {
		return pravoPristupa;
	}


	public void setPravoPristupa(Set<UserPermission> pravoPristupa) {
		this.pravoPristupa = pravoPristupa;
	}


//	public Student getStudent() {
//		return student;
//	}
//
//
//	public void setStudent(Student student) {
//		this.student = student;
//	}
//
//
//	public Nastavnik getNastavnik() {
//		return nastavnik;
//	}
//
//
//	public void setNastavnik(Nastavnik nastavnik) {
//		this.nastavnik = nastavnik;
//	}
//
//
//	public Administrator getAdministrator() {
//		return administrator;
//	}
//
//
//	public void setAdministrator(Administrator administrator) {
//		this.administrator = administrator;
//	}
//
//
//	public SluzbenikStudentske getSluzbenik() {
//		return sluzbenik;
//	}
//
//
//	public void setSluzbenik(SluzbenikStudentske sluzbenik) {
//		this.sluzbenik = sluzbenik;
//	}


    
}
