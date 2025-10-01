package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet;

import java.time.LocalDateTime;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class EvaluacijaZnanja implements BaseEntity<Long> {
	//Nastavnik pravi evaluaciju znanja,svaki pokusaj polaganja ko,ispit ili itd
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private LocalDateTime vremePocetka;
    private LocalDateTime vremeZavrsetka;
    //bodovi 
    private Long ostvareniBodovi;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "ishod_id", nullable = false)
    private Ishod ishod;
    
    
    //Ovo je test ili prakticni zadatak na kolokvijumu
    @ManyToOne(optional = true)
    //File je tip instrumenta evaluacije
    private File instrumentEvaluacije;

    @ManyToOne(optional = true)
    private RealizacijaPredmeta realizacijaPredmeta;

    //Kolokvijum ispit projektni zadatak
    @ManyToOne
    private TipEvaluacije tipEvaluacije;

//    @OneToOne(mappedBy = "evaluacijaZnanja")
//    private Polaganje polaganje;

    @ManyToOne(optional = true)
    @JoinColumn(name = "prijavljeni_ispit_id", nullable = true)
    private PrijavljeniIspit prijavljeniIspit;

    
    public EvaluacijaZnanja() {
    }

    public EvaluacijaZnanja(Long id, LocalDateTime vremePocetka, LocalDateTime vremeZavrsetka, Long ostvareniBodovi) {
        this.id = id;
        this.vremePocetka = vremePocetka;
        this.vremeZavrsetka = vremeZavrsetka;
        this.ostvareniBodovi = ostvareniBodovi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(LocalDateTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public LocalDateTime getVremeZavrsetka() {
        return vremeZavrsetka;
    }

    public void setVremeZavrsetka(LocalDateTime vremeZavrsetka) {
        this.vremeZavrsetka = vremeZavrsetka;
    }

    public Long getOstvareniBodovi() {
        return ostvareniBodovi;
    }

    public void setOstvareniBodovi(Long ostvareniBodovi) {
        this.ostvareniBodovi = ostvareniBodovi;
    }

    public RealizacijaPredmeta getRealizacijaPredmeta() {
        return realizacijaPredmeta;
    }

    public void setRealizacijaPredmeta(RealizacijaPredmeta realizacijaPredmeta) {
        this.realizacijaPredmeta = realizacijaPredmeta;
    }

    public TipEvaluacije getTipEvaluacije() {
        return tipEvaluacije;
    }

    public void setTipEvaluacije(TipEvaluacije tipEvaluacije) {
        this.tipEvaluacije = tipEvaluacije;
    }

//    public Polaganje getPolaganje() {
//        return polaganje;
//    }
//
//    public void setPolaganje(Polaganje polaganje) {
//        this.polaganje = polaganje;
//    }

	public Ishod getIshod() {
		return ishod;
	}

	public PrijavljeniIspit getPrijavljeniIspit() {
		return prijavljeniIspit;
	}

	public void setPrijavljeniIspit(PrijavljeniIspit prijavljeniIspit) {
		this.prijavljeniIspit = prijavljeniIspit;
	}

	public void setIshod(Ishod ishod) {
		this.ishod = ishod;
	}

	public File getInstrumentEvaluacije() {
		return instrumentEvaluacije;
	}

	public void setInstrumentEvaluacije(File instrumentEvaluacije) {
		this.instrumentEvaluacije = instrumentEvaluacije;
	}
	
	
    
    

}
