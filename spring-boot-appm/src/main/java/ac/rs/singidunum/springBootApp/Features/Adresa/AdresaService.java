package ac.rs.singidunum.springBootApp.Features.Adresa;


import java.util.Map;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.Adresa.AdresaDTO.AdresaDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;


@Service
public class AdresaService extends GenericCrudService<AdresaDTORecord, Adresa, Long> {

	protected AdresaService(CrudRepository<Adresa, Long> repository, Mapper<AdresaDTORecord, Adresa> mapper) {
		super(repository, mapper);
	}

	
}
