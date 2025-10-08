package ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet;


import java.util.List;
import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgramRepository;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;
import jakarta.transaction.Transactional;

@Service
public class PredmetService extends GenericCrudService<PredmetDTO, Predmet, Long> {

	@Autowired
	private PredmetRepository predmetRepository;
	
	@Autowired
	private StudijskiProgramRepository studijskiProgramRepository;
	
	@Autowired
	private PredmetMapper predmetMapper;
	
	protected PredmetService(CrudRepository<Predmet, Long> repository, Mapper<PredmetDTO, Predmet> mapper) {
		super(repository, mapper);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	@Transactional
	public PredmetDTO save(Predmet entity) {
	    // Provera šifre i naziva
	    validateSifra(entity);
	    validateNaziv(entity);
	    Predmet saved = predmetRepository.save(entity);
	    return predmetMapper.map(saved);
	}

	@Override
	@Transactional
	public PredmetDTO update(Predmet entity) {
	    if (entity.getId() == null || !predmetRepository.existsById(entity.getId())) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Predmet sa ID: " + entity.getId() + " ne postoji!");
	    }

	    // Provera šifre i naziva
	    validateSifra(entity);
	    validateNaziv(entity);

	    Predmet updated = predmetRepository.save(entity);
	    return predmetMapper.map(updated);
	}

	private void validateSifra(Predmet entity) {
	    if (entity.getSifra() != null && entity.getSifra().getId() != null) {
	        boolean exists = predmetRepository.existsBySifra_Id(entity.getSifra().getId());

	        // Ako već postoji predmet sa tom šifrom i to nije isti predmet
	        if (exists && (entity.getId() == null ||
	                !predmetRepository.findById(entity.getId())
	                        .map(p -> p.getSifra() != null && Objects.equals(p.getSifra().getId(), entity.getSifra().getId()))
	                        .orElse(false))) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Šifra je već dodeljena drugom predmetu!");
	        }
	    }
	}
	
	private void validateNaziv(Predmet entity) {
	    if (entity.getNaziv() != null && !entity.getNaziv().isBlank()) {
	        boolean exists = predmetRepository.existsByNazivIgnoreCase(entity.getNaziv());

	        if (exists && (entity.getId() == null ||
	                !predmetRepository.findById(entity.getId())
	                        .map(p -> p.getNaziv().equalsIgnoreCase(entity.getNaziv()))
	                        .orElse(false))) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Predmet sa ovim nazivom već postoji!");
	        }
	    }
	    }
	
	public List<Predmet> findPredmetiByProgramId(Long programId) {
        return predmetRepository.findByStudijskiProgrami_Id(programId);
    }
}
