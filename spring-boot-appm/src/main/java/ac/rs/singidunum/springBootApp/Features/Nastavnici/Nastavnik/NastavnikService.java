package ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO.NastavnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovanKorisnikRepository;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;


@Service
public class NastavnikService extends GenericCrudService<NastavnikDTORecord, Nastavnik, Long> {

	@Autowired
	private NastavnikMapper nastavnikMapper;
	
	@Autowired 
	private NastavnikRepository nRepository;
	
	@Autowired
	private RegistrovanKorisnikRepository rRepository;
	
	protected NastavnikService(CrudRepository<Nastavnik, Long> repository, Mapper<NastavnikDTORecord, Nastavnik> mapper) {
		super(repository, mapper);
		this.nastavnikMapper = nastavnikMapper;
	}
	
	public List<NastavnikDTORecord> slobodniNastavniciFakultet(){
		return nastavnikMapper.map(nRepository.findNastavniciWithoutFakultet());
	}
	
	public List<NastavnikDTORecord> slobodniNastavniciUniverzitet(){
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
