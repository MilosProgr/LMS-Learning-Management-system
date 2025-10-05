package ac.rs.singidunum.springBootApp.Service.implementacija.Predmet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.DTO.Predmet.NastavniMaterijalDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Predmet.NastavniMaterijal;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class NastavniMaterijalService extends GenericCrudService<NastavniMaterijalDTO, NastavniMaterijal, Long> {

	protected NastavniMaterijalService(CrudRepository<NastavniMaterijal, Long> repository,
			Mapper<NastavniMaterijalDTO, NastavniMaterijal> mapper) {
		super(repository, mapper);
	}

}
