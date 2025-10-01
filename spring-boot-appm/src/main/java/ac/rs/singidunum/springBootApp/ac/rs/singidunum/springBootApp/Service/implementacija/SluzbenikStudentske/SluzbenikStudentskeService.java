package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.SluzbenikStudentske;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.SluzbenikStudentske.SluzbenikStudentskeDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.implementacija.SluzbenikStudentske.SluzbenikStudentskeMapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.SluzbenikStudentske.SluzbenikStudentske;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.RegistrovaniKorisnik;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.Student;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.SluzbenikStudentske.SluzbenikStudentskeRepository;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Student.RegistrovanKorisnikRepository;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class SluzbenikStudentskeService extends GenericCrudService<SluzbenikStudentskeDTO, SluzbenikStudentske, Long> {
	
	@Autowired
	private RegistrovanKorisnikRepository korisnikRepository;
	
	@Autowired
	private SluzbenikStudentskeRepository sluzbenikStudentskeRepository;
	
	@Autowired
	private SluzbenikStudentskeMapper sluzbenikStudentskeMapper;
	
	protected SluzbenikStudentskeService(CrudRepository<SluzbenikStudentske, Long> repository,
			Mapper<SluzbenikStudentskeDTO, SluzbenikStudentske> mapper) {
		super(repository, mapper);
		this.sluzbenikStudentskeMapper = sluzbenikStudentskeMapper;
	}
	
	public SluzbenikStudentskeDTO promoteToSluzbenik(SluzbenikStudentske sluzbenik) {
	    Long korisnikId = sluzbenik.getId(); // Preuzimamo ID korisnika iz objekta SluzbenikStudentske

	    // Provera da li korisnik sa tim ID postoji
	    RegistrovaniKorisnik korisnik = korisnikRepository.findById(korisnikId)
	            .orElseThrow(() -> new RuntimeException("Registrovani korisnik sa ID " + korisnikId + " nije pronađen"));

	    // Provera da li sluzbenik sa tim ID već postoji
	    if (sluzbenikStudentskeRepository.existsById(korisnikId)) {
	        throw new RuntimeException("Sluzbenik studentske sa ID " + korisnikId + " već postoji.");
	    }

	    // Povezivanje RegistrovaniKorisnik sa SluzbenikStudentske entitetom
	    sluzbenik.setKorisnik(korisnik);

	    // Dodajemo studenta u bazu
	    return sluzbenikStudentskeMapper.map(sluzbenikStudentskeRepository.save(sluzbenik));
	}

}
