package ac.rs.singidunum.springBootApp.Features.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovanKorisnikRepository;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnik;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;


@Service
public class AdministratorService extends GenericCrudService<AdministratorDTO, Administrator, Long> {
	
	@Autowired
	private RegistrovanKorisnikRepository korisnikRepository;
	
	@Autowired
	private AdministratorRepository administratorRepository;
	
	@Autowired
	private AdministratorMapper administratorMapper;

	
	protected AdministratorService(CrudRepository<Administrator, Long> repository, Mapper<AdministratorDTO, Administrator> mapper) {
        super(repository, mapper);
        this.administratorMapper = administratorMapper;
    }
	
	
	public AdministratorDTO promoteToAdministratora(Administrator administrator) {
	    Long korisnikId = administrator.getId(); // Preuzimamo ID korisnika iz objekta administrator

	    // Provera da li korisnik sa tim ID postoji
	    RegistrovaniKorisnik korisnik = korisnikRepository.findById(korisnikId)
	            .orElseThrow(() -> new RuntimeException("Registrovani korisnik sa ID " + korisnikId + " nije pronađen"));

	    // Provera da li administrator sa tim ID već postoji
	    if (administratorRepository.existsById(korisnikId)) {
	        throw new RuntimeException("Administrator sa ID " + korisnikId + " već postoji.");
	    }

	    // Povezivanje RegistrovaniKorisnik sa administrator entitetom
	    administrator.setKorisnik(korisnik);

	    // Dodajemo studenta u bazu
	    return administratorMapper.map(administratorRepository.save(administrator));
	}
}
