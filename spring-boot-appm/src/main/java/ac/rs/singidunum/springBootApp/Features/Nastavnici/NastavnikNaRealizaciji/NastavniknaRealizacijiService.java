package ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class NastavniknaRealizacijiService extends GenericCrudService<NastavnikNaRealizacijiDTO, NastavnikNaRealizaciji, Long> {

	@Autowired
	private NastavnikNaRealizaacijiRepository nastavnikNaRealizaacijiRepository;
	
	protected NastavniknaRealizacijiService(CrudRepository<NastavnikNaRealizaciji, Long> repository,
			Mapper<NastavnikNaRealizacijiDTO, NastavnikNaRealizaciji> mapper) {
		super(repository, mapper);
	}
	
	@Override
    public NastavnikNaRealizacijiDTO save(NastavnikNaRealizaciji entity) {
        final Long nastavnikId = (entity.getNastavnik() != null) ? entity.getNastavnik().getId() : null;
        if (nastavnikId == null) {
            throw new IllegalArgumentException("Nedostaje nastavnik.id");
        }
        if (nastavnikNaRealizaacijiRepository.existsByNastavnik_Id(nastavnikId)) {
        	throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Nastavniku je vec dodeljena realizacija.");
        }
        return super.save(entity);
    }

    @Override
    public NastavnikNaRealizacijiDTO update(NastavnikNaRealizaciji entity) {
        final Long nastavnikId = (entity.getNastavnik() != null) ? entity.getNastavnik().getId() : null;
        if (nastavnikId != null) {
            Optional<NastavnikNaRealizaciji> existing = nastavnikNaRealizaacijiRepository.findByNastavnik_Id(nastavnikId);
            if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
            	throw new ResponseStatusException(
                        HttpStatus.CONFLICT, "Nastavniku je vec dodeljena realizacija.");
            }
        }
        return super.update(entity);
    }
	
	

}
