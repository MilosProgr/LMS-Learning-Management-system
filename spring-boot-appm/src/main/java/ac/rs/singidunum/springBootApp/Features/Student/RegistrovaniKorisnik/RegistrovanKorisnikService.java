package ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;



@Service
public class RegistrovanKorisnikService extends GenericCrudService<RegistrovaniKorisnikDTORecord, RegistrovaniKorisnik, Long> {
	private RegistrovanKorisnikRepository kRepos;
	
	@Autowired
	private RegistrovanKorisnikMapper korisnikMapper;
	
	
	protected RegistrovanKorisnikService(CrudRepository<RegistrovaniKorisnik, Long> repository,
			Mapper<RegistrovaniKorisnikDTORecord, RegistrovaniKorisnik> mapper) {
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
