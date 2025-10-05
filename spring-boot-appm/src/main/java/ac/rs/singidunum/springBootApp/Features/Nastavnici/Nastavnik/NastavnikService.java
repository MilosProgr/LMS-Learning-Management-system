package ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Student.RegistrovaniKorisnik;
import ac.rs.singidunum.springBootApp.Model.Student.Student;
import ac.rs.singidunum.springBootApp.Repository.Student.RegistrovanKorisnikRepository;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class NastavnikService extends GenericCrudService<NastavnikDTO, Nastavnik, Long> {

	@Autowired
	private NastavnikMapper nastavnikMapper;
	
	@Autowired 
	private NastavnikRepository nRepository;
	
	@Autowired
	private RegistrovanKorisnikRepository rRepository;
	
	protected NastavnikService(CrudRepository<Nastavnik, Long> repository, Mapper<NastavnikDTO, Nastavnik> mapper) {
		super(repository, mapper);
		this.nastavnikMapper = nastavnikMapper;
	}
	
	public List<NastavnikDTO> slobodniNastavniciFakultet(){
		return nastavnikMapper.map(nRepository.findNastavniciWithoutFakultet());
	}
	
	public List<NastavnikDTO> slobodniNastavniciUniverzitet(){
		return nastavnikMapper.map(nRepository.findNastavniciWithoutUniverzitet());
	}
	
//	public NastavnikDTO save(Nastavnik n) {
//		
//		 RegistrovaniKorisnik registrovaniKorisnik = rRepository.findById(n.getId())
//	                .orElseThrow(() -> new RuntimeException("Registrovani korisnik sa datim ID-jem ne postoji"));
//		
//		Nastavnik nastavnik = new Nastavnik();
//		nastavnik.setId(registrovaniKorisnik.getId());
//		nastavnik.setBiografija(n.getBiografija());
//		nastavnik.setJmbg(n.getJmbg());
//		nastavnik.setBrojSlobodnihDana(0);
//		nastavnik.setBrojIskoristenihDana(0);
//		nastavnik.setTelefon(n.getTelefon());
//		nastavnik.setPoslovniMail(n.getPoslovniMail());
//		
//		return super.save(nastavnik);
//	}
// KOD IZNAD PROVERITI DA LI JE POTREBAN
	
}
