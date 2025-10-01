package ac.rs.singidunum.springBootApp.Service.implementacija.Predmet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.DTO.predmet.IshodDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Predmet.Ishod;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class IshodService extends GenericCrudService<IshodDTO, Ishod, Long> {

	protected IshodService(CrudRepository<Ishod, Long> repository, Mapper<IshodDTO, Ishod> mapper) {
		super(repository, mapper);
	}

}
