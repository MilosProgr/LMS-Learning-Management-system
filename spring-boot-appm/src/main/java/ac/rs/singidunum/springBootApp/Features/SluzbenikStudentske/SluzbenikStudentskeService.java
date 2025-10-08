package ac.rs.singidunum.springBootApp.Features.SluzbenikStudentske;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovanKorisnikRepository;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnik;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;



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
