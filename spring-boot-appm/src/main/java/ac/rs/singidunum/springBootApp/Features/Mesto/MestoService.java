package ac.rs.singidunum.springBootApp.Features.Mesto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.Mesto.MestoDTO.MestoDTORecord;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class MestoService extends GenericCrudService<MestoDTORecord, Mesto, Long> {
	

    protected MestoService(CrudRepository<Mesto, Long> repository, Mapper<MestoDTORecord, Mesto> mapper) {
		super(repository, mapper);
		
	}
   
}
