package ac.rs.singidunum.springBootApp.Service.implementacija.Adresa;


import java.util.Map;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.DTO.Adresa.AdresaDTO;
import ac.rs.singidunum.springBootApp.DTO.Adresa.AdresaDTO.AdresaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Adresa;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class AdresaService extends GenericCrudService<AdresaDTORecord, Adresa, Long> {

	protected AdresaService(CrudRepository<Adresa, Long> repository, Mapper<AdresaDTORecord, Adresa> mapper) {
		super(repository, mapper);
	}

	
}
