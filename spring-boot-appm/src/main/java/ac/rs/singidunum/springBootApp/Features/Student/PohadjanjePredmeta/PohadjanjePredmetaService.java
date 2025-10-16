package ac.rs.singidunum.springBootApp.Features.Student.PohadjanjePredmeta;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti.ObavestenjaAktivnostiService;
import ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti.ObavestenjeAktivnosti;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.Predmet;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetRepository;
import ac.rs.singidunum.springBootApp.Features.Student.Student;
import ac.rs.singidunum.springBootApp.Features.Student.StudentRepository;
import ac.rs.singidunum.springBootApp.Features.Student.PohadjanjePredmeta.PohadjanjePredmetaDTO.PohadjanjePredmetaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodini;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniRepository;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;
import jakarta.transaction.Transactional;

@Service
public class PohadjanjePredmetaService extends GenericCrudService<PohadjanjePredmetaDTORecord, PohadjanjePredmeta, Long> {

	@Autowired
	private StudentNaGodiniRepository studentNaGodiniRepository;
	
	@Autowired
	private PredmetRepository predmetRepository;
	
	@Autowired
	private ObavestenjaAktivnostiService obavestenjaAktivnostiService;
	
	@Autowired
	private PohadjanjePredmetaRepository pohadjanjePredmetaRepository;
	
	@Autowired
    private PohadjanjePredmetaMapper pohadjanjePredmetaMapper;
	
	@Autowired
	private StudentRepository studentRepository;

	
	protected PohadjanjePredmetaService(CrudRepository<PohadjanjePredmeta, Long> repository,
			Mapper<PohadjanjePredmetaDTORecord, PohadjanjePredmeta> mapper) {
		super(repository, mapper);
	}
	
	@Transactional
	public List<PohadjanjePredmetaDTORecord> dodajPredmeteZaStudenta(Long studentNaGodiniId, List<Long> predmetiIds) {
        StudentNaGodini sng = studentNaGodiniRepository.findById(studentNaGodiniId)
                .orElseThrow(() -> new RuntimeException("Student na godini nije pronađen"));

        List<PohadjanjePredmeta> sacuvano = predmetiIds.stream()
                .map(predmetId -> {
                    Predmet predmet = predmetRepository.findById(predmetId)
                            .orElseThrow(() -> new RuntimeException("Predmet nije pronađen sa ID: " + predmetId));

                    // Proveri da li već postoji veza
                    if (pohadjanjePredmetaRepository.existsByStudentNaGodiniIdAndPredmetId(sng.getId(), predmet.getId())) {
                        return null; 
                    }

                    PohadjanjePredmeta novo = new PohadjanjePredmeta();
                    novo.setStudentNaGodini(sng);
                    novo.setPredmet(predmet);
                    return pohadjanjePredmetaRepository.save(novo);
                })
                .filter(p -> p != null)
                .collect(Collectors.toList());
        
        //setovanje vrednosti studnetu da je izabrao predmete
        Student student = sng.getStudent();
        if (student != null && student.getId() != null && studentRepository.existsById(student.getId())) {
            if (!Boolean.TRUE.equals(student.isPredmetiIzabrani())) {
                student.setPredmetiIzabrani(true);
                studentRepository.save(student);
            }
        }
        
        return sacuvano.stream()
                .map(pohadjanjePredmetaMapper::map)
                .collect(Collectors.toList());

    }
	
	@Transactional
	public PohadjanjePredmetaDTORecord upisiKonacnuOcenuById(Long ppId, Long konacnaOcena) {
	    if (konacnaOcena == null || konacnaOcena < 6 || konacnaOcena > 10) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Konacna ocena mora biti u opsegu 6–10.");
	    }

	    PohadjanjePredmeta pp = pohadjanjePredmetaRepository.findById(ppId)
	        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PohadjanjePredmeta ne postoji: id=" + ppId));

	    pp.setKonacnaOcena(konacnaOcena);
	    
	    try {
	        Long korisnikId = null;
	        if (pp.getStudentNaGodini() != null &&
	            pp.getStudentNaGodini().getStudent() != null &&
	            pp.getStudentNaGodini().getStudent().getKorisnik() != null) {
	            korisnikId = pp.getStudentNaGodini().getStudent().getKorisnik().getId();
	        }
	        if (korisnikId != null) {
	            String predmetNaziv = (pp.getPredmet() != null && pp.getPredmet().getNaziv() != null)
	                    ? pp.getPredmet().getNaziv()
	                    : ("Predmet #" + (pp.getPredmet() != null ? pp.getPredmet().getId() : "?"));

	            var o = new ObavestenjeAktivnosti();
	            o.setNaslov("Upisana konačna ocena: " + predmetNaziv);
	            o.setSadrzaj(String.format("Upisana vam je ocena %d iz predmeta %s.", konacnaOcena, predmetNaziv));
	            o.setRegistrovaniKorisnik(pp.getStudentNaGodini().getStudent().getKorisnik());
	            o.setProcitano(false);
	            
	            obavestenjaAktivnostiService.save(o);
	        }
	    } catch (Exception ex) {
	        // ne ruši upis ocene ako obaveštenje zakaže
	        System.out.printf("Kreiranje obaveštenja nije uspelo (ppId={}): {}", ppId, ex.getMessage());
	    }
	    
	    return pohadjanjePredmetaMapper.map(pohadjanjePredmetaRepository.save(pp));
	}


	public List<PohadjanjePredmetaDTORecord> getByStudentNaGodiniId(Long studentNaGodiniId) {
		
	    return pohadjanjePredmetaRepository.findByStudentNaGodiniId(studentNaGodiniId)
	            .stream()
	            .map(pohadjanjePredmetaMapper::map)
	            .collect(Collectors.toList());
	}

}
