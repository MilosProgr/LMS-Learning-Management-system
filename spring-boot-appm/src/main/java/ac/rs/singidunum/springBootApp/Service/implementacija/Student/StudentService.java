package ac.rs.singidunum.springBootApp.Service.implementacija.Student;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.DTO.Nastavnici.FakultetDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.StudentDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.UpisStudentaRequest;
import ac.rs.singidunum.springBootApp.Features.Adresa;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Mapper.implementacija.Student.StudentMapper;
import ac.rs.singidunum.springBootApp.Model.Adresa.Drzava;
import ac.rs.singidunum.springBootApp.Model.Adresa.Mesto;
import ac.rs.singidunum.springBootApp.Model.Nastavnici.Fakultet;
import ac.rs.singidunum.springBootApp.Model.Student.RegistrovaniKorisnik;
import ac.rs.singidunum.springBootApp.Model.Student.Student;
import ac.rs.singidunum.springBootApp.Repository.Adresa.AdresaRepository;
import ac.rs.singidunum.springBootApp.Repository.Adresa.DrzavaRepository;
import ac.rs.singidunum.springBootApp.Repository.Adresa.MestoRepository;
import ac.rs.singidunum.springBootApp.Repository.Student.RegistrovanKorisnikRepository;
import ac.rs.singidunum.springBootApp.Repository.Student.StudentRepository;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;
import ac.rs.singidunum.springBootApp.Service.implementacija.Adresa.AdresaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class StudentService extends GenericCrudService<StudentDTO, Student, Long> {

	@Autowired private AdresaService adresaService;
    @Autowired private RegistrovanKorisnikRepository registrovaniKorisnikRepository;
    @Autowired private StudentRepository studentRepository;
    @Autowired private StudentMapper studentMapper;
    
    @Autowired private DrzavaRepository drzavaRepository;
    @Autowired private MestoRepository mestoRepository;
    @Autowired private AdresaRepository adresaRepository;
    
	
	protected StudentService(CrudRepository<Student, Long> repository, Mapper<StudentDTO, Student> mapper) {
		super(repository, mapper);
		this.studentMapper = studentMapper;
	}
	
	//@Transactional
	public StudentDTO save(Student s) {
        
        System.out.println("Ovo je Id:" + s.getId());
        
        // Save address if it exists
        if (s.getAdresa() != null) {
            adresaService.save(s.getAdresa());
        }
        return super.save(s);
    }
	
	@Transactional
	public StudentDTO uvecajStanjeNaRacunu(Long studentId, Double iznos) {
	    if (studentId == null) {
	        throw new IllegalArgumentException("studentId je obavezan.");
	    }
	    if (iznos == null || iznos <= 0) {
        	throw new ResponseStatusException(HttpStatus.CONFLICT, "Iznos mora biti veći od nule.");
	    }

	    Student s = studentRepository.findById(studentId)
	        .orElseThrow(() -> new EntityNotFoundException("Student ne postoji: " + studentId));

	    double trenutno = s.getStanjeNaRacunu() == null ? 0.0 : s.getStanjeNaRacunu();
	    s.setStanjeNaRacunu(trenutno + iznos);

	    Student sacuvan = studentRepository.save(s);
	    return studentMapper.map(sacuvan);
	}
	
	 @Transactional
	    public StudentDTO upisiStudenta(UpisStudentaRequest req) {
	        // --- VALIDACIJE ---
	        if (isBlank(req.getJmbg()))  throw new IllegalArgumentException("JMBG je obavezan.");
	        if (isBlank(req.getTelefon())) throw new IllegalArgumentException("Telefon je obavezan.");
	        if (isBlank(req.getUlica()))  throw new IllegalArgumentException("Ulica je obavezna.");
	        if (isBlank(req.getBroj()))   throw new IllegalArgumentException("Broj je obavezan.");
	        if (req.getDrzavaId() == null && (req.getDrzavaNaziv() == null || req.getDrzavaNaziv().isBlank())) {
	            throw new IllegalArgumentException("Potrebna je država (id ili naziv).");
	        }


	        // Entiteti koji moraju postojati
	        RegistrovaniKorisnik korisnik = registrovaniKorisnikRepository.findById(req.getKorisnikId())
	                .orElseThrow(() -> new EntityNotFoundException("Registrovani korisnik ne postoji: id=" + req.getKorisnikId()));
	        
	        //Provera da li je vec student upisan
			 Long korisnikId = korisnik.getId();
			 if (studentRepository.existsById(korisnikId)) {
		        	throw new ResponseStatusException(HttpStatus.CONFLICT, "Student je vec upisan!");
			 }
	        // drzava: nađi po id ili naziv, ili kreiraj po nazivu
	        var drzava = resolveDrzava(req.getDrzavaId(), req.getDrzavaNaziv());

	        // Mesto za ADRESU (nađi ili kreiraj)
	        Mesto mestoZaAdresu = resolveMesto(req.getMestoId(), req.getMestoNaziv(), drzava.getId());

	        // Adresa (nađi ili kreiraj)
	        Adresa adresa = adresaRepository
	                .findByUlicaAndBrojAndMestoId(req.getUlica().trim(), req.getBroj().trim(), mestoZaAdresu.getId())
	                .orElseGet(() -> {
	                    Adresa nova = new Adresa();
	                    nova.setUlica(req.getUlica().trim());
	                    nova.setBroj(req.getBroj().trim());
	                    nova.setMesto(mestoZaAdresu);
	                    return adresaRepository.save(nova);
	                });

	        //Mesto PREBIVALIŠTA (nađi ili kreiraj)
	        Mesto mestoPrebivalista = null;
	        if (req.getMestoPrebivalistaId() != null) {
	            mestoPrebivalista = mestoRepository.findById(req.getMestoPrebivalistaId())
	                    .orElseThrow(() -> new EntityNotFoundException(
	                            "Mesto prebivališta ne postoji: id=" + req.getMestoPrebivalistaId()));
	        } else if (!isBlank(req.getMestoPrebivalistaNaziv())) {
	            mestoPrebivalista = mestoRepository
	                    .findByNazivAndDrzava_Id(req.getMestoPrebivalistaNaziv().trim(), drzava.getId())
	                    .orElseGet(() -> {
	                        Mesto m = new Mesto();
	                        m.setNaziv(req.getMestoPrebivalistaNaziv().trim());
	                        m.setDrzava(drzava);
	                        return mestoRepository.save(m);
	                    });
	        } else {
	            throw new IllegalArgumentException("Mesto prebivališta je obavezno (id ili naziv).");
	        }

	        // jedinstvenost JMBG
	        if (studentRepository.existsByJmbg(req.getJmbg().trim())) {
	            throw new ResponseStatusException(HttpStatus.CONFLICT, "Student sa datim JMBG već postoji i zahtev je podnet.");
	        }

	        Student s = new Student();
	        s.setId(korisnik.getId()); 
	        s.setJmbg(req.getJmbg().trim());
	        s.setTelefon(req.getTelefon().trim());
	        s.setAdresa(adresa);
	        s.setMestoPrebivalista(mestoPrebivalista);
	        s.setDrzava(drzava);
	        s.setKorisnik(korisnik);

	        Student sacuvan = studentRepository.save(s);
	        // return super.save(s);
	        return studentMapper.map(sacuvan);
	    }

	    private Mesto resolveMesto(Long mestoId, String mestoNaziv, Long drzavaId) {
	        if (mestoId != null) {
	            return mestoRepository.findById(mestoId)
	                    .orElseThrow(() -> new EntityNotFoundException("Mesto ne postoji: id=" + mestoId));
	        }
	        if (isBlank(mestoNaziv)) {
	            throw new IllegalArgumentException("Za adresu je potrebno mesto (id ili naziv).");
	        }
	        return mestoRepository.findByNazivAndDrzava_Id(mestoNaziv.trim(), drzavaId)
	                .orElseGet(() -> {
	                    Mesto m = new Mesto();
	                    m.setNaziv(mestoNaziv.trim());
	                    m.setDrzava(drzavaRepository.findById(drzavaId)
	                            .orElseThrow(() -> new EntityNotFoundException("Država ne postoji: id=" + drzavaId)));
	                    return mestoRepository.save(m);
	                });
	    }
	    
	    private Drzava resolveDrzava(Long drzavaId, String drzavaNaziv) {
	        if (drzavaId != null) {
	            return drzavaRepository.findById(drzavaId)
	                .orElseThrow(() -> new EntityNotFoundException("Država ne postoji: id=" + drzavaId));
	        }
	        if (isBlank(drzavaNaziv)) {
	            throw new IllegalArgumentException("Potrebna je država (id ili naziv).");
	        }
	        return drzavaRepository.findByNazivIgnoreCase(drzavaNaziv.trim())
	            .orElseGet(() -> {
	                var d = new Drzava();
	                d.setNaziv(drzavaNaziv.trim());
	                return drzavaRepository.save(d);
	            });
	    }
	    
	    
	    //pomocne metode
	    private boolean isBlank(String s) {
	        return s == null || s.trim().isEmpty();
	    }
}
