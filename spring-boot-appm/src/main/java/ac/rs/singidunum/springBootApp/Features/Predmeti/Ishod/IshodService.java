package ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;



@Service
public class IshodService extends GenericCrudService<IshodDTO, Ishod, Long> {

	protected IshodService(CrudRepository<Ishod, Long> repository, Mapper<IshodDTO, Ishod> mapper) {
		super(repository, mapper);
	}

}
