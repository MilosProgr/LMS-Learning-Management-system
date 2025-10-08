package ac.rs.singidunum.springBootApp.Features.Polaganja.EvaluacijaZnanja;

public class CreateEvaluacijaZnanjaRequest {
	
 private Long prijavljeniIspitId;     
 private Long tipEvaluacijeId;        
 private Long realizacijaPredmetaId;  
 private Long ostvareniBodovi;        


 private String ishodOpis;          
 private Long ishodId;                
 
 private Long instrumentFileId;       
 private String instrumentOpis;     
 private String instrumentUrl;
 
 private Long ishodPredmetId;
 private Boolean ishodPolozeno;
 
 
	public Long getPrijavljeniIspitId() {
		return prijavljeniIspitId;
	}
	public void setPrijavljeniIspitId(Long prijavljeniIspitId) {
		this.prijavljeniIspitId = prijavljeniIspitId;
	}
	public Long getTipEvaluacijeId() {
		return tipEvaluacijeId;
	}
	public void setTipEvaluacijeId(Long tipEvaluacijeId) {
		this.tipEvaluacijeId = tipEvaluacijeId;
	}
	public Long getRealizacijaPredmetaId() {
		return realizacijaPredmetaId;
	}
	public void setRealizacijaPredmetaId(Long realizacijaPredmetaId) {
		this.realizacijaPredmetaId = realizacijaPredmetaId;
	}
	public Long getOstvareniBodovi() {
		return ostvareniBodovi;
	}
	public void setOstvareniBodovi(Long ostvareniBodovi) {
		this.ostvareniBodovi = ostvareniBodovi;
	}
	public String getIshodOpis() {
		return ishodOpis;
	}
	public void setIshodOpis(String ishodOpis) {
		this.ishodOpis = ishodOpis;
	}
	public Long getIshodId() {
		return ishodId;
	}
	public void setIshodId(Long ishodId) {
		this.ishodId = ishodId;
	}
	public Long getInstrumentFileId() {
		return instrumentFileId;
	}
	public void setInstrumentFileId(Long instrumentFileId) {
		this.instrumentFileId = instrumentFileId;
	}
	public String getInstrumentOpis() {
		return instrumentOpis;
	}
	public void setInstrumentOpis(String instrumentOpis) {
		this.instrumentOpis = instrumentOpis;
	}
	public String getInstrumentUrl() {
		return instrumentUrl;
	}
	public void setInstrumentUrl(String instrumentUrl) {
		this.instrumentUrl = instrumentUrl;
	}
	public Long getIshodPredmetId() {
		return ishodPredmetId;
	}
	public void setIshodPredmetId(Long ishodPredmetId) {
		this.ishodPredmetId = ishodPredmetId;
	}
	public Boolean getIshodPolozeno() {
		return ishodPolozeno;
	}
	public void setIshodPolozeno(Boolean ishodPolozeno) {
		this.ishodPolozeno = ishodPolozeno;
	}      

	
 
 
 
}

