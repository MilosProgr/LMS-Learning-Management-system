package ac.rs.singidunum.springBootApp.Features.Predmeti.NastavniMaterijal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.Predmeti.NastavniMaterijal.NastavniMaterijalDTO.NastavniMaterijalDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;


@Service
public class NastavniMaterijalService extends GenericCrudService<NastavniMaterijalDTORecord, NastavniMaterijal, Long> {

	protected NastavniMaterijalService(CrudRepository<NastavniMaterijal, Long> repository,
			Mapper<NastavniMaterijalDTORecord, NastavniMaterijal> mapper) {
		super(repository, mapper);
	}

}
