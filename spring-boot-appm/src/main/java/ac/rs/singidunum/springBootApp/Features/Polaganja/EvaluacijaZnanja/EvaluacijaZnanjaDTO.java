package ac.rs.singidunum.springBootApp.Features.Polaganja.EvaluacijaZnanja;

import java.time.LocalDateTime;

import ac.rs.singidunum.springBootApp.Features.Obavestenja.File.FileDTO;
import ac.rs.singidunum.springBootApp.Features.Obavestenja.File.FileDTO.FileDTORecord;
import ac.rs.singidunum.springBootApp.Features.Polaganja.TipEvaluacije.TipEvaluacijeDTO;
import ac.rs.singidunum.springBootApp.Features.Polaganja.TipEvaluacije.TipEvaluacijeDTO.TipEvaluacijeDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod.IshodDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod.IshodDTO.IshodDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.PrijavljeniIspit.PrijavljeniIspitDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.PrijavljeniIspit.PrijavljeniIspitDTO.PrijavljeniIspitDTORecord;

public class EvaluacijaZnanjaDTO {
	private Long id;
	
	private LocalDateTime vremePocetka;
	private LocalDateTime vremeZavrsetka;
	private Long ostvareniBodovi;
	
	private IshodDTO ishod;
	private FileDTORecord instrumentEvaluacije;
	
	private TipEvaluacijeDTO tipEvaluacije;
	
	//private PolaganjeDTO polaganje;
	
	private PrijavljeniIspitDTO prijavljenIspit;

	public EvaluacijaZnanjaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EvaluacijaZnanjaDTO(Long id, LocalDateTime vremePocetka, LocalDateTime vremeZavrsetka, Long ostvareniBodovi) {
		super();
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

	public TipEvaluacijeDTO getTipEvaluacije() {
		return tipEvaluacije;
	}

	public void setTipEvaluacije(TipEvaluacijeDTO tipEvaluacije) {
		this.tipEvaluacije = tipEvaluacije;
	}

//	public PolaganjeDTO getPolaganje() {
//		return polaganje;
//	}
//
//	public void setPolaganje(PolaganjeDTO polaganje) {
//		this.polaganje = polaganje;
//	}

	public PrijavljeniIspitDTO getPrijavljeniIspit() {
		return prijavljenIspit;
	}

	public void setPrijavljeniIspit(PrijavljeniIspitDTO prijavljenIspit) {
		this.prijavljenIspit = prijavljenIspit;
	}

	public IshodDTO getIshod() {
		return ishod;
	}

	public void setIshod(IshodDTO ishod) {
		this.ishod = ishod;
	}

	public FileDTORecord getInstrumentEvaluacije() {
		return instrumentEvaluacije;
	}

	public void setInstrumentEvaluacije(FileDTORecord instrumentEvaluacije) {
		this.instrumentEvaluacije = instrumentEvaluacije;
	}
	
	public record EvaluacijaZnanjaDTORecord(
			 Long id,
			
			 LocalDateTime vremePocetka,
			 LocalDateTime vremeZavrsetka,
			 Long ostvareniBodovi,
			
			 IshodDTORecord ishod,
			 FileDTORecord instrumentEvaluacije,
			
			 TipEvaluacijeDTORecord tipEvaluacije,
						
			 PrijavljeniIspitDTORecord prijavljenIspit
			) {}
	
	
	
	
}
