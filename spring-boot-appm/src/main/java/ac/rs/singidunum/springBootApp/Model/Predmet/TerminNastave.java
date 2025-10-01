package ac.rs.singidunum.springBootApp.Model.Predmet;

import java.time.LocalDateTime;

import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class TerminNastave implements BaseEntity<Long>{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDateTime vremePocetka;
    LocalDateTime vremeZavrsetka;
    
    @OneToOne
    private Ishod ishod;


    @ManyToOne
    private TipNastave tipNastave;

    @ManyToOne
    private RealizacijaPredmeta realizacijaPredmeta;

	public TerminNastave() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TerminNastave(Long id, LocalDateTime vremePocetka, LocalDateTime vremeZavrsetka) {
		super();
		this.id = id;
		this.vremePocetka = vremePocetka;
		this.vremeZavrsetka = vremeZavrsetka;
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

	public TipNastave getTipNastave() {
		return tipNastave;
	}

	public void setTipNastave(TipNastave tipNastave) {
		this.tipNastave = tipNastave;
	}

	public RealizacijaPredmeta getRealizacijaPredmeta() {
		return realizacijaPredmeta;
	}

	public void setRealizacijaPredmeta(RealizacijaPredmeta realizacijaPredmeta) {
		this.realizacijaPredmeta = realizacijaPredmeta;
	}

	public Ishod getIshod() {
		return ishod;
	}

	public void setIshod(Ishod ishod) {
		this.ishod = ishod;
	}
	
	
    
    
    
    
}
