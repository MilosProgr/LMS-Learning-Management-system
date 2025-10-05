package ac.rs.singidunum.springBootApp.Service.implementacija.Predmet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.DTO.Student.PrijavljeniIspitDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.Predmet;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetRepository;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Predmet.IspitniRok;
import ac.rs.singidunum.springBootApp.Model.Predmet.PrijavljeniIspit;
import ac.rs.singidunum.springBootApp.Model.Student.Student;
import ac.rs.singidunum.springBootApp.Model.Student.StudentNaGodini;
import ac.rs.singidunum.springBootApp.Repository.Predmet.IspitniRokRepository;
import ac.rs.singidunum.springBootApp.Repository.Predmet.PrijavljeniIspitRepository;
import ac.rs.singidunum.springBootApp.Repository.Student.StudentNaGodiniRepository;
import ac.rs.singidunum.springBootApp.Repository.Student.StudentRepository;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;
import jakarta.transaction.Transactional;

@Service
public class PrijavljeniIspitService extends GenericCrudService<PrijavljeniIspitDTO, PrijavljeniIspit, Long> {

	@Autowired
    private StudentRepository studentRepository;

	@Autowired
	private StudentNaGodiniRepository studentNaGodiniRepository;
	
	@Autowired
	private PredmetRepository predmetRepository;
	
	@Autowired
	private IspitniRokRepository ispitniRokRepository;
	
	@Autowired
	private PrijavljeniIspitRepository prijavljeniIspitRepository;
    
	protected PrijavljeniIspitService(CrudRepository<PrijavljeniIspit, Long> repository,
			Mapper<PrijavljeniIspitDTO, PrijavljeniIspit> mapper) {
		super(repository, mapper);
	}
	
	
	public PrijavljeniIspitDTO save(PrijavljeniIspit p) {
		Integer brojPrijava = p.getBrojPrijava() == null ? 0 : p.getBrojPrijava() + 1;
		p.setBrojPrijava(brojPrijava);
		return super.save(p);
	}
	
	@Transactional
	public PrijavljeniIspitDTO saveForStudentAndSng(
	    Long studentId,
	    Long studentNaGodiniId,
	    Long predmetId,
	    Long ispitniRokId,
	    Double cenaPrijave
	) {
	  // ucitava i validira SNG, predmet, rok
	  StudentNaGodini sng = studentNaGodiniRepository.findById(studentNaGodiniId)
	      .orElseThrow(() -> new IllegalArgumentException("SNG ne postoji: " + studentNaGodiniId));

	  if (sng.getStudent() == null || !sng.getStudent().getId().equals(studentId)) {
	    throw new IllegalStateException("Prosleđeni SNG ne pripada studentu " + studentId);
	  }

	  Predmet predmet = predmetRepository.findById(predmetId)
	      .orElseThrow(() -> new IllegalArgumentException("Predmet ne postoji: " + predmetId));

	  IspitniRok rok = ispitniRokRepository.findById(ispitniRokId)
	      .orElseThrow(() -> new IllegalArgumentException("Ispitni rok ne postoji: " + ispitniRokId));

	  boolean postoji = prijavljeniIspitRepository
	      .existsByStudentNaGodini_IdAndPredmet_IdAndIspitniRok_Id(studentNaGodiniId, predmetId, ispitniRokId);
	  if (postoji) {
		throw new ResponseStatusException(HttpStatus.CONFLICT, "Ispit je već prijavljen za ovaj rok.");
	  }

	  Student student = studentRepository.findById(studentId)
	      .orElseThrow(() -> new IllegalStateException("Student ne postoji: " + studentId));

	  double cena = (cenaPrijave != null) ? cenaPrijave : 0.0;
	  if (cena < 0.0) throw new IllegalArgumentException("Cena prijave ne može biti negativna.");

	  double trenutno = student.getStanjeNaRacunu() == null ? 0.0 : student.getStanjeNaRacunu();
	  double novo = trenutno - cena;
	  if (novo < 0.0) {
	    throw new ResponseStatusException(HttpStatus.CONFLICT, "Nedovoljno sredstava na racunu.");
	  }
	  student.setStanjeNaRacunu(novo);
	  studentRepository.save(student);
	  
	  PrijavljeniIspit ent = new PrijavljeniIspit();
	  ent.setStudentNaGodini(sng);
	  ent.setPredmet(predmet);
	  ent.setIspitniRok(rok);
	  ent.setPrijavljen(Boolean.TRUE);
	  ent.setBrojPrijava( (ent.getBrojPrijava() == null ? 0 : ent.getBrojPrijava()) + 1 );

	  return super.save(ent);
	}
}
