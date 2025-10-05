package ac.rs.singidunum.springBootApp.Features.Drzava;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.Drzava.DrzavaDTO.DrzavaDTORecord;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class DrzavaService extends GenericCrudService<DrzavaDTORecord, Drzava, Long> {

	protected DrzavaService(CrudRepository<Drzava, Long> repository, Mapper<DrzavaDTORecord, Drzava> mapper) {
		super(repository, mapper);
		// TODO Auto-generated constructor stub
	}
	
}
