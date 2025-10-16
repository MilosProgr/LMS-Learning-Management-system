package ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija.GodinaStudijaDTO.GodinaStudijaDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;



@Service
public class GodinaStudijaService extends GenericCrudService<GodinaStudijaDTORecord, GodinaStudija, Long> {

	@Autowired
	private GodinaStudijaRepository godinaStudijaRepository;
	
	protected GodinaStudijaService(CrudRepository<GodinaStudija, Long> repository,
			Mapper<GodinaStudijaDTORecord, GodinaStudija> mapper) {
		super(repository, mapper);
	}
	
    @Override
    public GodinaStudijaDTORecord save(GodinaStudija entity) {
        if (godinaStudijaRepository.existsByGodina(entity.getGodina())) {
        	throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Godina studija " + entity.getGodina() + " već postoji!");
        }
        return super.save(entity);
    }

    @Override
    public GodinaStudijaDTORecord update(GodinaStudija entity) {
        if (godinaStudijaRepository.existsByGodinaAndIdNot(entity.getGodina(), entity.getId())) {
        	throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Godina studija " + entity.getGodina() + " već postoji!");
        }
        return super.update(entity);
    }

}
