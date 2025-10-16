package ac.rs.singidunum.springBootApp.Features.Nastava.TerminNastave;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.Nastava.TerminNastave.TerminNastaveDTO.TerminNastaveDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta.RealizacijaPredmetaMapper;
import ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta.RealizacijaPredmetaRepository;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;


@Service
public class TerminNastaveService extends GenericCrudService<TerminNastaveDTORecord, TerminNastave, Long> {


    @Autowired
    private RealizacijaPredmetaRepository realizacijaPredmetaRepository;
    
    @Autowired
    private RealizacijaPredmetaMapper rMapper;
    
	protected TerminNastaveService(CrudRepository<TerminNastave, Long> repository,
			Mapper<TerminNastaveDTORecord, TerminNastave> mapper) {
		super(repository, mapper);
	}
	
	

}
