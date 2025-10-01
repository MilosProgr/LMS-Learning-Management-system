package ac.rs.singidunum.springBootApp.Model.Predmet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ac.rs.singidunum.springBootApp.Model.Nastavnici.NastavnikNaRealizaciji;
import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

@Entity
public class RealizacijaPredmeta implements BaseEntity<Long>{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
	@JoinColumn(name = "predmet_id")
	private Predmet predmet;

    //upitno
//    @OneToOne(mappedBy = "realizacijaPredmeta")
//    private PohadjanjePredmeta pohadjanjePredmeta;

    @OneToMany(mappedBy = "realizacijaPredmeta")
    private List<EvaluacijaZnanja> evaluacijaZnanja  = new ArrayList<>();

    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private NastavnikNaRealizaciji nastavnikNaRealizaciji;


    @OneToMany(mappedBy = "realizacijaPredmeta")
    private List<TerminNastave> terminNastave = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
        name = "realizacija_predmeta_semestar",
        joinColumns = { @JoinColumn(name = "realizacija_predmeta_id", referencedColumnName = "id") },
        inverseJoinColumns = { @JoinColumn(name = "semestar_id", referencedColumnName = "id") }
    )
    private Set<Semestar> semestri = new HashSet<>();
    
//    @ManyToOne(optional = true)
//    private IspitniRok rok;
    
    

    public RealizacijaPredmeta(Long id, Predmet predmet,
			NastavnikNaRealizaciji nastavnikNaRealizaciji) {
		super();
		this.id = id;
		this.predmet = predmet;
//		this.pohadjanjePredmeta = pohadjanjePredmeta;
		this.nastavnikNaRealizaciji = nastavnikNaRealizaciji;
	}

	public List<EvaluacijaZnanja> getEvaluacijaZnanja() {
        return evaluacijaZnanja;
    }

    public void setEvaluacijaZnanja(List<EvaluacijaZnanja> evaluacijaZnanja) {
        this.evaluacijaZnanja = evaluacijaZnanja;
    }

    public RealizacijaPredmeta() {
    }

    public RealizacijaPredmeta(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }


    public NastavnikNaRealizaciji getNastavnikNaRealizaciji() {
        return nastavnikNaRealizaciji;
    }

    public void setNastavnikNaRealizaciji(NastavnikNaRealizaciji nastavnikNaRealizaciji) {
        this.nastavnikNaRealizaciji = nastavnikNaRealizaciji;
    }

    public List<TerminNastave> getTerminNastave() {
        return terminNastave;
    }

    public void setTerminNastave(List<TerminNastave> terminNastave) {
        this.terminNastave = terminNastave;
    }

	public Set<Semestar> getSemestri() {
		return semestri;
	}

	public void setSemestri(Set<Semestar> semestri) {
		this.semestri = semestri;
	}

//	public IspitniRok getRok() {
//		return rok;
//	}
//
//	public void setRok(IspitniRok rok) {
//		this.rok = rok;
//	}
//    
    
    
}
