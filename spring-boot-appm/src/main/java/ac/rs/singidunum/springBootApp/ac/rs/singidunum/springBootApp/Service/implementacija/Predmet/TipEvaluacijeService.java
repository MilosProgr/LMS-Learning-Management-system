package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Predmet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet.TipEvaluacijeDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.TipEvaluacije;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class TipEvaluacijeService extends GenericCrudService<TipEvaluacijeDTO, TipEvaluacije, Long> {

	protected TipEvaluacijeService(CrudRepository<TipEvaluacije, Long> repository,
			Mapper<TipEvaluacijeDTO, TipEvaluacije> mapper) {
		super(repository, mapper);
	}

}
