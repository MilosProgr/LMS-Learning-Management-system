package ac.rs.singidunum.springBootApp.Features.Polaganja.TipEvaluacije;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.Polaganja.TipEvaluacije.TipEvaluacijeDTO.TipEvaluacijeDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;



@Service
public class TipEvaluacijeService extends GenericCrudService<TipEvaluacijeDTORecord, TipEvaluacije, Long> {

	protected TipEvaluacijeService(CrudRepository<TipEvaluacije, Long> repository,
			Mapper<TipEvaluacijeDTORecord, TipEvaluacije> mapper) {
		super(repository, mapper);
	}

}
