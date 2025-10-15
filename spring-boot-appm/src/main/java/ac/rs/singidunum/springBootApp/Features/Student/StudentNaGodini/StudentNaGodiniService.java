package ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.Permission;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.PermissionRepository;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermission;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermissionRepository;
import ac.rs.singidunum.springBootApp.Features.Student.Student;
import ac.rs.singidunum.springBootApp.Features.Student.StudentRepository;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO.StudentNaGodiniDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;
import jakarta.transaction.Transactional;

@Service
public class StudentNaGodiniService extends GenericCrudService<StudentNaGodiniDTORecord, StudentNaGodini, Long> {

	@Autowired
	private StudentNaGodiniRepository sNaGodRepos;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentNaGodiniMapper sNaGodMapper;
	
	@Autowired
	private PermissionRepository ulogaRepository;

	@Autowired
	private UserPermissionRepository userPermissionRepository;
	
	protected StudentNaGodiniService(CrudRepository<StudentNaGodini, Long> repository,
			Mapper<StudentNaGodiniDTORecord, StudentNaGodini> mapper) {
		super(repository, mapper);
	}
    
    @Override
    @Transactional
    public StudentNaGodiniDTORecord save(StudentNaGodini entity) {
    	
    	Long studentId = entity.getStudent().getId(); // Preuzimamo ID korisnika iz objekta StudentNaGodini
        Long programId = entity.getStudijskiProgram().getId();
        Long godinaId = entity.getGodinaStudija().getId();

	    // Provera da li student sa tim ID postoji
	    Student student = studentRepository.findById(studentId)
	            .orElseThrow(() -> new RuntimeException("Student sa ID " + studentId + " nije pronađen"));

	 // Provera da li je već upisan na taj studijski program
	    boolean postojiUpis = sNaGodRepos.existsByStudentIdAndStudijskiProgramId(studentId, programId);
	    if (postojiUpis) {
	    	throw new ResponseStatusException(HttpStatus.CONFLICT, "Student je već upisan na izabrani studijski program.");

	    }
	    
	    // Provera da li student na godini sa tim ID već postoji
//	    if (sNaGodRepos.existsById(studentId)) {
//	        throw new RuntimeException("Student na godini sa ID " + studentId + " već postoji.");
//	    }

	    // Povezivanje Studenta sa Student na godini entitetom
	    entity.setStudent(student);
	    //manuelno setovanje id (id od studenta)
//	    entity.setId(studentId);
	    
        // Postavljanje trenutnog datuma pre nego što  se sačuva entitet
        entity.setDatumUpisa(LocalDateTime.now());
        
        // Generisanje broja indeksa
        String godina = String.valueOf(LocalDate.now().getYear()); // npr. "2025"

        // Pronađi sve brojeve indeksa koji počinju sa trenutnom godinom
        List<StudentNaGodini> studentiTeGodine = sNaGodRepos.findByBrojIndeksaStartingWith(godina);

        // Izdvoji brojeve, konvertuj u int, pronađi max
        int maxRedniBroj = studentiTeGodine.stream()
            .map(s -> s.getBrojIndeksa().substring(4)) // uzmi deo posle godine
            .mapToInt(Integer::parseInt)
            .max()
            .orElse(0); // ako nema nijedan, počinjemo od 0

        int noviRedniBroj = maxRedniBroj + 1;
        String redniBrojFormatted = String.format("%06d", noviRedniBroj); // npr. "000024"

        String brojIndeksa = godina + redniBrojFormatted;
        System.out.printf("Novi broj indeksa je: ", brojIndeksa);
        
        entity.setBrojIndeksa(brojIndeksa); //Postavljamo broj indeksa
        
        // Dodajemo studenta na godini u bazu
        StudentNaGodini sacuvan = sNaGodRepos.save(entity);

        // Ažuriranje statusa studiranja
        student.setStatusStudiranja(true);
        studentRepository.save(student);
        
        // Dodela role studentu
        Permission rolaStudent = ulogaRepository.findPermissionByIme("ROLE_STUDENT")
                .orElseThrow(() -> new RuntimeException("Uloga ROLE_STUDENT ne postoji u bazi."));

        boolean vecImaRolu = userPermissionRepository
                .existsByKorisnikIdAndPermissionId(studentId, rolaStudent.getId());

        if (!vecImaRolu) {
            UserPermission up = new UserPermission();
            up.setKorisnik(student.getKorisnik());  
            up.setPermission(rolaStudent);
            userPermissionRepository.save(up);
        }

        return sNaGodMapper.map(sacuvan);

    	
    	
    }
    
 

}
