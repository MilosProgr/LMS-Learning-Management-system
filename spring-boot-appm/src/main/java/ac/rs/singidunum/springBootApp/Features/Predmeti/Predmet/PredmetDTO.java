package ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet;

import java.util.HashSet;
import java.util.Set;


import ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod.IshodDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Kurs.KursDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta.RealizacijaPredmetaDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgramDTO;
import ac.rs.singidunum.springBootApp.Features.Sifarnik.SifraDTO;
import ac.rs.singidunum.springBootApp.Features.Sifarnik.SifraDTO.SifraDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija.GodinaStudijaDTO;



public class PredmetDTO {
	
	private Long id;
	private String naziv;
	private Integer esbn;
	private Boolean obavezan;
	private Integer brojPredavanja;
	private Integer brojVezbi;
	private Integer drugiObliciNastave;
	private Integer istrazivackiRad;
	private Integer ostaliCasovi;
	private Set<IshodDTO> silabus;
//	private SifraDTO sifra;
	private SifraDTORecord sifra;
	
	private Set<StudijskiProgramDTO> studijskiProgrami = new HashSet<>();
	
	private GodinaStudijaDTO godinaStudija;
	private RealizacijaPredmetaDTO realizacijaPredmeta;
	
	private Set<KursDTO> kursevi = new HashSet<>();
	public PredmetDTO(Long id, String naziv, Integer esbn, Boolean obavezan, Integer brojPredavanja, Integer brojVezbi,
			Integer drugiObliciNastave, Integer istrazivackiRad, Integer ostaliCasovi) {
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
	}
	public PredmetDTO() {
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
	public void setObavzean(Boolean obavezan) {
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
	public Set<KursDTO> getKursevi() {
		return kursevi;
	}
	public void setKursevi(Set<KursDTO> kursevi) {
		this.kursevi = kursevi;
	}
	public GodinaStudijaDTO getGodinaStudija() {
		return godinaStudija;
	}
	public void setGodinaStudija(GodinaStudijaDTO godinaStudija) {
		this.godinaStudija = godinaStudija;
	}
	public RealizacijaPredmetaDTO getRealizacijaPredmeta() {
		return realizacijaPredmeta;
	}
	public void setRealizacijaPredmeta(RealizacijaPredmetaDTO realizacijaPredmeta) {
		this.realizacijaPredmeta = realizacijaPredmeta;
	}
	public void setObavezan(Boolean obavezan) {
		this.obavezan = obavezan;
	}
	public Set<IshodDTO> getSilabus() {
		return silabus;
	}
	public void setSilabus(Set<IshodDTO> silabus) {
		this.silabus = silabus;
	}
	public Set<StudijskiProgramDTO> getStudijskiProgrami() {
		return studijskiProgrami;
	}
	public void setStudijskiProgrami(Set<StudijskiProgramDTO> studijskiProgrami) {
		this.studijskiProgrami = studijskiProgrami;
	}
	public SifraDTORecord getSifra() {
		return sifra;
	}
	public void setSifra(SifraDTORecord sifra) {
		this.sifra = sifra;
	}
	
	
	
	
	
	
	
	
	
}
