package ac.rs.singidunum.springBootApp.Model.Nastavnici;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ac.rs.singidunum.springBootApp.Model.Adresa.Adresa;
import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class Univerzitet implements BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String naziv;
	private LocalDate datumOsnivanja;
	
	@Column(columnDefinition = "TEXT")
	private String opis;

    @ManyToOne
    private Adresa adresa;
	
	@OneToMany(mappedBy = "univerzitet")
	List<Fakultet> fakulteti = new ArrayList<>();
	
	@OneToOne
    private Nastavnik rektor;

	public Univerzitet() {
		super();
	}

	public Univerzitet(Long id, String naziv, LocalDate datumOsnivanja) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.datumOsnivanja = datumOsnivanja;
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

	public LocalDate getDatumOsnivanja() {
		return datumOsnivanja;
	}


	public void setDatumOsnivanja(LocalDate datumOsnivanja) {
		this.datumOsnivanja = datumOsnivanja;
	}


	public Adresa getAdresa() {
		return adresa;
	}


	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}
	
	public List<Fakultet> getFakulteti() {
		return fakulteti;
	}

	public void setFakulteti(List<Fakultet> fakulteti) {
		this.fakulteti = fakulteti;
	}



	public Nastavnik getRektor() {
		return rektor;
	}



	public void setRektor(Nastavnik rektor) {
		this.rektor = rektor;
	}



	public String getOpis() {
		return opis;
	}



	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	
	
	
	
	
	
	
}
