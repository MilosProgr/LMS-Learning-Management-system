package ac.rs.singidunum.springBootApp.DTO.predmet;

import java.time.LocalDateTime;

public class TerminNastaveDTO {
	Long id;
	LocalDateTime vremePocetka;
	LocalDateTime vremeKraja;
	
	private IshodDTO ishod;
	
	private TipNastaveDTO tipNasstave;
	
	private RealizacijaPredmetaDTO realizacijaPredmeta;

	public TerminNastaveDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TerminNastaveDTO(Long id, LocalDateTime vremePocetka, LocalDateTime vremeKraja,TipNastaveDTO tipNastave) {
		super();
		this.id = id;
		this.vremePocetka = vremePocetka;
		this.vremeKraja = vremeKraja;
		this.tipNasstave = tipNastave;
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

	public LocalDateTime getVremeKraja() {
		return vremeKraja;
	}

	public void setVremeKraja(LocalDateTime vremeKraja) {
		this.vremeKraja = vremeKraja;
	}

	public TipNastaveDTO getTipNasstave() {
		return tipNasstave;
	}

	public void setTipNasstave(TipNastaveDTO tipNasstave) {
		this.tipNasstave = tipNasstave;
	}

	public RealizacijaPredmetaDTO getRealizacijaPredmeta() {
		return realizacijaPredmeta;
	}

	public void setRealizacijaPredmeta(RealizacijaPredmetaDTO realizacijaPredmeta) {
		this.realizacijaPredmeta = realizacijaPredmeta;
	}

	public IshodDTO getIshod() {
		return ishod;
	}

	public void setIshod(IshodDTO ishod) {
		this.ishod = ishod;
	}
	
	
	
	
	
}
