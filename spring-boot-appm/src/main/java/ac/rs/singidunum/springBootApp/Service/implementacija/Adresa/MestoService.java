package ac.rs.singidunum.springBootApp.Service.implementacija.Adresa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.DTO.Adresa.MestoDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Adresa.Mesto;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class MestoService extends GenericCrudService<MestoDTO, Mesto, Long> {
	

    protected MestoService(CrudRepository<Mesto, Long> repository, Mapper<MestoDTO, Mesto> mapper) {
		super(repository, mapper);
		
	}
   
}
