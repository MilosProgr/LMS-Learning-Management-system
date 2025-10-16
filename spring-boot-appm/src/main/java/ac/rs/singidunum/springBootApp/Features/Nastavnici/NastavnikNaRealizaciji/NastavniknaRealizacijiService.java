package ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji.NastavnikNaRealizacijiDTO.NastavnikNaRealizacijiDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;



@Service
public class NastavniknaRealizacijiService extends GenericCrudService<NastavnikNaRealizacijiDTORecord, NastavnikNaRealizaciji, Long> {

	@Autowired
	private NastavnikNaRealizaacijiRepository nastavnikNaRealizaacijiRepository;
	
	protected NastavniknaRealizacijiService(CrudRepository<NastavnikNaRealizaciji, Long> repository,
			Mapper<NastavnikNaRealizacijiDTORecord, NastavnikNaRealizaciji> mapper) {
		super(repository, mapper);
	}
	
	@Override
    public NastavnikNaRealizacijiDTORecord save(NastavnikNaRealizaciji entity) {
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
    public NastavnikNaRealizacijiDTORecord update(NastavnikNaRealizaciji entity) {
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
