package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Student;

import java.util.HashSet;
import java.util.Set;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.AdminDTO.AdministratorDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Nastavnici.NastavnikDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.ObavestenjaAktivnosti.ObavestenjeAktivnostDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.PravaPristupa.UserPermissionDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.SluzbenikStudentske.SluzbenikStudentskeDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Admin.Administrator;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Nastavnici.Nastavnik;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.SluzbenikStudentske.SluzbenikStudentske;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.Student;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;

public class RegistrovaniKorisnikDTO {
	
	private Long id;
	private String ime;
	private String prezime;
    private String korisnickoIme;
    private String lozinka;
    private String email;
    
//    private StudentDTO student;
//
//    private NastavnikDTO nastavnik;
//
//    private AdministratorDTO administrator;
//    
//    private SluzbenikStudentskeDTO sluzbenik;
    
    private Set<UserPermissionDTO> pravaPristupa = new HashSet<>();
    
    private Set<ObavestenjeAktivnostDTO> obavestenjaAktivnosti = new HashSet<>();
    
	public RegistrovaniKorisnikDTO(Long id, String ime, String prezime, String korisnickoIme, String lozinka, String email) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.email = email;
		
		
	}
	public RegistrovaniKorisnikDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	public Set<UserPermissionDTO> getPravaPristupa() {
		return pravaPristupa;
	}
	public void setPravaPristupa(Set<UserPermissionDTO> pravaPristupa) {
		this.pravaPristupa = pravaPristupa;
	}
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<ObavestenjeAktivnostDTO> getObavestenjaAktivnosti() {
		return obavestenjaAktivnosti;
	}
	public void setObavestenjaAktivnosti(Set<ObavestenjeAktivnostDTO> obavestenjaAktivnosti) {
		this.obavestenjaAktivnosti = obavestenjaAktivnosti;
	}
//	public StudentDTO getStudent() {
//		return student;
//	}
//	public void setStudent(StudentDTO student) {
//		this.student = student;
//	}
//	public NastavnikDTO getNastavnik() {
//		return nastavnik;
//	}
//	public void setNastavnik(NastavnikDTO nastavnik) {
//		this.nastavnik = nastavnik;
//	}
//	public AdministratorDTO getAdministrator() {
//		return administrator;
//	}
//	public void setAdministrator(AdministratorDTO administrator) {
//		this.administrator = administrator;
//	}
//	public SluzbenikStudentskeDTO getSluzbenik() {
//		return sluzbenik;
//	}
//	public void setSluzbenik(SluzbenikStudentskeDTO sluzbenik) {
//		this.sluzbenik = sluzbenik;
//	}
	
	
}
