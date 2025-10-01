package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.AdminDTO.AdministratorDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.implementacija.Admin.AdministratorMapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Admin.Administrator;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.RegistrovaniKorisnik;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.Student;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Admin.AdministratorRepository;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Student.RegistrovanKorisnikRepository;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

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
