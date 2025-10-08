package ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;



@Service
public class UdzbenikService extends GenericCrudService<UdzbenikDTO, Udzbenik, Long> {

	protected UdzbenikService(CrudRepository<Udzbenik, Long> repository, Mapper<UdzbenikDTO, Udzbenik> mapper) {
		super(repository, mapper);
	}

}
