package ac.rs.singidunum.springBootApp.Service.implementacija.Adresa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.DTO.Adresa.DrzavaDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Adresa.Drzava;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class DrzavaService extends GenericCrudService<DrzavaDTO, Drzava, Long> {

	protected DrzavaService(CrudRepository<Drzava, Long> repository, Mapper<DrzavaDTO, Drzava> mapper) {
		super(repository, mapper);
		// TODO Auto-generated constructor stub
	}
	
}
