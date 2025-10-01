package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Student.GodinaStudijaDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.GodinaStudija;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Student.GodinaStudijaRepository;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class GodinaStudijaService extends GenericCrudService<GodinaStudijaDTO, GodinaStudija, Long> {

	@Autowired
	private GodinaStudijaRepository godinaStudijaRepository;
	
	protected GodinaStudijaService(CrudRepository<GodinaStudija, Long> repository,
			Mapper<GodinaStudijaDTO, GodinaStudija> mapper) {
		super(repository, mapper);
	}
	
    @Override
    public GodinaStudijaDTO save(GodinaStudija entity) {
        if (godinaStudijaRepository.existsByGodina(entity.getGodina())) {
        	throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Godina studija " + entity.getGodina() + " već postoji!");
        }
        return super.save(entity);
    }

    @Override
    public GodinaStudijaDTO update(GodinaStudija entity) {
        if (godinaStudijaRepository.existsByGodinaAndIdNot(entity.getGodina(), entity.getId())) {
        	throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Godina studija " + entity.getGodina() + " već postoji!");
        }
        return super.update(entity);
    }

}
