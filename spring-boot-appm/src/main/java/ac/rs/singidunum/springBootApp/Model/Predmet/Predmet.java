package ac.rs.singidunum.springBootApp.Model.Predmet;

import java.util.HashSet;
import java.util.Set;

import ac.rs.singidunum.springBootApp.Model.Sifarnik.Sifra;
import ac.rs.singidunum.springBootApp.Model.Student.GodinaStudija;
import ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Predmet implements BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, columnDefinition = "TEXT")
	private String naziv;
	
	private Integer esbn;
	@Column(nullable = false, columnDefinition = "BOOLEAN")
	private Boolean obavezan;
	
	private Integer brojPredavanja;
	private Integer brojVezbi;
	private Integer drugiObliciNastave;
	private Integer istrazivackiRad;
	private Integer ostaliCasovi;
	
	@OneToOne
	private Sifra sifra;
	
	@OneToMany(mappedBy = "predmet")
    private Set<Ishod> silabus;
	
	@ManyToMany
	@JoinTable(name = "predmet_studijski_program",
	    joinColumns = @JoinColumn(name = "predmet_id"),
	    inverseJoinColumns = @JoinColumn(name = "studijski_program_id"))
	private Set<StudijskiProgram> studijskiProgrami = new HashSet<>();

	
	@ManyToMany
	@JoinTable(name = "predmet_kurs",
	joinColumns = @JoinColumn(name = "predmet_id"),
	inverseJoinColumns = @JoinColumn(name = "kurs_id"))
	private Set<Kurs> kursevi = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	private GodinaStudija godinaStudija;

	@OneToOne(mappedBy = "predmet")
	private RealizacijaPredmeta realizacijaPredmeta;
	
	public Predmet(Long id, String naziv, Integer esbn, Boolean obavezan, Integer brojPredavanja, Integer brojVezbi,
			Integer drugiObliciNastave, Integer istrazivackiRad, Integer ostaliCasovi,Sifra sifra) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.esbn = esbn;
		this.obavezan = obavezan;
		this.brojPredavanja = brojPredavanja;
		this.brojVezbi = brojVezbi;
		this.drugiObliciNastave = drugiObliciNastave;
		this.istrazivackiRad = istrazivackiRad;
		this.ostaliCasovi = ostaliCasovi;
		this.sifra = sifra;
	}
	public Predmet() {
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
	public Integer getEsbn() {
		return esbn;
	}
	public void setEsbn(Integer esbn) {
		this.esbn = esbn;
	}
	public Boolean getObavezan() {
		return obavezan;
	}
	public void setObavezan(Boolean obavezan) {
		this.obavezan = obavezan;
	}
	public Integer getBrojPredavanja() {
		return brojPredavanja;
	}
	public void setBrojPredavanja(Integer brojPredavanja) {
		this.brojPredavanja = brojPredavanja;
	}
	public Integer getBrojVezbi() {
		return brojVezbi;
	}
	public void setBrojVezbi(Integer brojVezbi) {
		this.brojVezbi = brojVezbi;
	}
	public Integer getDrugiObliciNastave() {
		return drugiObliciNastave;
	}
	public void setDrugiObliciNastave(Integer drugiObliciNastave) {
		this.drugiObliciNastave = drugiObliciNastave;
	}
	public Integer getIstrazivackiRad() {
		return istrazivackiRad;
	}
	public void setIstrazivackiRad(Integer istrazivackiRad) {
		this.istrazivackiRad = istrazivackiRad;
	}
	public Integer getOstaliCasovi() {
		return ostaliCasovi;
	}
	public void setOstaliCasovi(Integer ostaliCasovi) {
		this.ostaliCasovi = ostaliCasovi;
	}
	public Set<Kurs> getKursevi() {
		return kursevi;
	}
	public void setKursevi(Set<Kurs> kursevi) {
		this.kursevi = kursevi;
	}
	public GodinaStudija getGodinaStudija() {
		return godinaStudija;
	}
	public void setGodinaStudija(GodinaStudija godinaStudija) {
		this.godinaStudija = godinaStudija;
	}
	public RealizacijaPredmeta getRealizacijaPredmeta() {
		return realizacijaPredmeta;
	}
	public void setRealizacijaPredmeta(RealizacijaPredmeta realizacijaPredmeta) {
		this.realizacijaPredmeta = realizacijaPredmeta;
	}
	public Set<Ishod> getSilabus() {
		return silabus;
	}
	public void setSilabus(Set<Ishod> silabus) {
		this.silabus = silabus;
	}
	public Set<StudijskiProgram> getStudijskiProgrami() {
		return studijskiProgrami;
	}
	public void setStudijskiProgrami(Set<StudijskiProgram> studijskiProgrami) {
		this.studijskiProgrami = studijskiProgrami;
	}
	public Sifra getSifra() {
		return sifra;
	}
	public void setSifra(Sifra sifra) {
		this.sifra = sifra;
	}
	
	
	
	
	
	
	
	
	
	
	
}
