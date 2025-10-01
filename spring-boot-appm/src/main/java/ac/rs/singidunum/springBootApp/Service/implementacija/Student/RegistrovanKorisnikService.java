package ac.rs.singidunum.springBootApp.Service.implementacija.Student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.DTO.Student.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.RegistrovaniKorisnikOsnovniPodaciDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.StudentDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Mapper.implementacija.Student.RegistrovanKorisnikMapper;
import ac.rs.singidunum.springBootApp.Model.Student.RegistrovaniKorisnik;
import ac.rs.singidunum.springBootApp.Model.Student.Student;
import ac.rs.singidunum.springBootApp.Repository.Student.RegistrovanKorisnikRepository;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class RegistrovanKorisnikService extends GenericCrudService<RegistrovaniKorisnikDTO, RegistrovaniKorisnik, Long> {
	private RegistrovanKorisnikRepository kRepos;
	
	@Autowired
	private RegistrovanKorisnikMapper korisnikMapper;
	
	
	protected RegistrovanKorisnikService(CrudRepository<RegistrovaniKorisnik, Long> repository,
			Mapper<RegistrovaniKorisnikDTO, RegistrovaniKorisnik> mapper) {
		super(repository, mapper);
		this.kRepos = (RegistrovanKorisnikRepository) repository;
	} 
	
	public Optional<RegistrovaniKorisnik> findKorisnikByKorisnickoIme(String korisnickoIme){
		return kRepos.findKorisnikByKorisnickoIme(korisnickoIme);
	}
	
	public Optional<RegistrovaniKorisnik> findKorisnikByEmail(String email){
		return kRepos.findByEmail(email);
	}
	
	//METODA ZA MAPIRANJE IZ RegistrovaniKorisnikDTO u RegistrovaniKorisnikOsnovniPodaciDTO
	public List<RegistrovaniKorisnikOsnovniPodaciDTO> findAllOsnovniPodaci() {
	    List<RegistrovaniKorisnik> korisnici = (List<RegistrovaniKorisnik>) kRepos.findAll();
	    return korisnikMapper.mapToOsnovniDTO(korisnici);
	}
	
}
