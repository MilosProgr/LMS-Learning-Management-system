package ac.rs.singidunum.springBootApp.Model.Asocijacija;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class predmet_Kurs implements Serializable {
	private Long predmet_id;
	private Long kurs_id;
	public predmet_Kurs(Long predmet_id, Long kurs_id) {
		super();
		this.predmet_id = predmet_id;
		this.kurs_id = kurs_id;
	}
	public predmet_Kurs() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getPredmet_id() {
		return predmet_id;
	}
	public void setPredmet_id(Long predmet_id) {
		this.predmet_id = predmet_id;
	}
	public Long getKurs_id() {
		return kurs_id;
	}
	public void setKurs_id(Long kurs_id) {
		this.kurs_id = kurs_id;
	}
	
	
}
