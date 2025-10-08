package ac.rs.singidunum.springBootApp.Features.Predmeti.Semestar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SemestarService extends GenericCrudService<SemestarDTO, Semestar, Long> {
	
	@Autowired
	private SemestarRepository semestarRepository;
	

	protected SemestarService(CrudRepository<Semestar, Long> repository, Mapper<SemestarDTO, Semestar> mapper) {
		super(repository, mapper);
	}
	
    @Override
    public SemestarDTO save(Semestar entity) {
        if (semestarRepository.existsByTip(entity.getTip())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Semestar sa imenom '" + entity.getTip() + "' već postoji!");
        }
        return super.save(entity);
    }
    
    @Override
    public SemestarDTO update(Semestar entity) {
        if (!semestarRepository.existsById(entity.getId())) {
            throw new EntityNotFoundException("Semestar sa ID: " + entity.getId() + " nije pronađen!");
        }

        // Provera duplikata na osnovu tipa
        boolean exists = semestarRepository.existsByTip(entity.getTip());
        Semestar existing = semestarRepository.findById(entity.getId()).orElse(null);

        if (exists && existing != null && !existing.getTip().equals(entity.getTip())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Semestar sa imenom '" + entity.getTip() + "' već postoji!");
        }

        return super.update(entity);
    }

}
