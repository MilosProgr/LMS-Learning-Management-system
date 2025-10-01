package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Predmet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet.TerminNastaveDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.implementacija.Nastavnici.RealizacijaPredmetaMapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.TerminNastave;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Predmet.RealizacijaPredmetaRepository;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class TerminNastaveService extends GenericCrudService<TerminNastaveDTO, TerminNastave, Long> {


    @Autowired
    private RealizacijaPredmetaRepository realizacijaPredmetaRepository;
    
    @Autowired
    private RealizacijaPredmetaMapper rMapper;
    
	protected TerminNastaveService(CrudRepository<TerminNastave, Long> repository,
			Mapper<TerminNastaveDTO, TerminNastave> mapper) {
		super(repository, mapper);
	}
	
	

}
