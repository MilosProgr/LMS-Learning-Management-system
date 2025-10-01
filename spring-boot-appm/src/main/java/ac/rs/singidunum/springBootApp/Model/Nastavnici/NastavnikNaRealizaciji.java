package ac.rs.singidunum.springBootApp.Model.Nastavnici;

import java.util.HashSet;
import java.util.Set;

import ac.rs.singidunum.springBootApp.Model.ObavestenjeAktivnosti.ObavestenjeAktivnosti;
import ac.rs.singidunum.springBootApp.Model.Predmet.RealizacijaPredmeta;
import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class NastavnikNaRealizaciji implements BaseEntity<Long>{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long brojCasova;

    @ManyToOne
    private Nastavnik nastavnik;

    @OneToMany(mappedBy = "nastavnikNaRealizaciji")
    private Set<RealizacijaPredmeta> realizacijaPredmeta = new HashSet<>();
    
    public NastavnikNaRealizaciji() {
    }

    public NastavnikNaRealizaciji(Long id, Long brojCasova) {
        this.id = id;
        this.brojCasova = brojCasova;
        
    }
    
    public NastavnikNaRealizaciji(Long id, Long brojCasova,Nastavnik nastavnik) {
        this.id = id;
        this.brojCasova = brojCasova;
        this.nastavnik = nastavnik;
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrojCasova() {
        return brojCasova;
    }

    public void setBrojCasova(Long brojCasova) {
        this.brojCasova = brojCasova;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

	public Set<RealizacijaPredmeta> getRealizacijaPredmeta() {
		return realizacijaPredmeta;
	}

	public void setRealizacijaPredmeta(Set<RealizacijaPredmeta> realizacijaPredmeta) {
		this.realizacijaPredmeta = realizacijaPredmeta;
	}

}
