package ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod.IshodDTO.IshodDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;



@Service
public class IshodService extends GenericCrudService<IshodDTORecord, Ishod, Long> {

	protected IshodService(CrudRepository<Ishod, Long> repository, Mapper<IshodDTORecord, Ishod> mapper) {
		super(repository, mapper);
	}

}
