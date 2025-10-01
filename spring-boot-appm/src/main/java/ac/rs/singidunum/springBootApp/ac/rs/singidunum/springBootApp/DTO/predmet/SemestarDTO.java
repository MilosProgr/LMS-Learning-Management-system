package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet;

import java.time.LocalDateTime;

public class SemestarDTO {
	private Long id;
	private LocalDateTime datumPocetka;
	private LocalDateTime datumKraja;
	//zimski ili letnji
	private String tip;
	
	public SemestarDTO(Long id, String tip,LocalDateTime datumPocetka, 
			LocalDateTime datumKraja) {
		super();
		this.id = id;
		this.datumPocetka = datumPocetka;
		this.datumKraja = datumKraja;
		this.tip = tip;
	}

	public SemestarDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(LocalDateTime datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public LocalDateTime getDatumKraja() {
		return datumKraja;
	}

	public void setDatumKraja(LocalDateTime datumKraja) {
		this.datumKraja = datumKraja;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
	
	
	
	
	
	
	
}
