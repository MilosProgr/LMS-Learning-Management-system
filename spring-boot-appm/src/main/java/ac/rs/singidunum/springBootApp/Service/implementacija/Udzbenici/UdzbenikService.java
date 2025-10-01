package ac.rs.singidunum.springBootApp.Service.implementacija.Udzbenici;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.DTO.Udzbenici.UdzbenikDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Udzbenici.Udzbenik;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class UdzbenikService extends GenericCrudService<UdzbenikDTO, Udzbenik, Long> {

	protected UdzbenikService(CrudRepository<Udzbenik, Long> repository, Mapper<UdzbenikDTO, Udzbenik> mapper) {
		super(repository, mapper);
	}

}
