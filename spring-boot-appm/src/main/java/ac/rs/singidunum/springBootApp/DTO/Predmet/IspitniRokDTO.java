package ac.rs.singidunum.springBootApp.DTO.Predmet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ac.rs.singidunum.springBootApp.DTO.Student.PrijavljeniIspitDTO;

public class IspitniRokDTO {
	private Long id;
	private String naziv;
	private LocalDateTime pocetak;
	private LocalDateTime kraj;
	private Boolean redovan;
	
	private List<PrijavljeniIspitDTO> prijavljeniIspiti = new ArrayList<>();
	
	
	public IspitniRokDTO(Long id, String naziv, LocalDateTime pocetak, LocalDateTime kraj,Boolean redovan) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.redovan = redovan;
	}

	public IspitniRokDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public LocalDateTime getPocetak() {
		return pocetak;
	}

	public void setPocetak(LocalDateTime pocetak) {
		this.pocetak = pocetak;
	}

	public LocalDateTime getKraj() {
		return kraj;
	}

	public void setKraj(LocalDateTime kraj) {
		this.kraj = kraj;
	}

	

	public Boolean getRedovan() {
		return redovan;
	}

	public void setRedovan(Boolean redovan) {
		this.redovan = redovan;
	}

	public List<PrijavljeniIspitDTO> getPrijavljeniIspiti() {
		return prijavljeniIspiti;
	}

	public void setPrijavljeniIspiti(List<PrijavljeniIspitDTO> prijavljeniIspiti) {
		this.prijavljeniIspiti = prijavljeniIspiti;
	}
	
	
	
	
	
	

	
	
	
	
	
	
}
