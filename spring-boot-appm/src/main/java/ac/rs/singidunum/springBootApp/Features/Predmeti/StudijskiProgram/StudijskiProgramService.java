package ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram.StudijskiProgramDTO.StudijskiProgramDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;



@Service
public class StudijskiProgramService extends GenericCrudService<StudijskiProgramDTORecord, StudijskiProgram, Long>{

	@Autowired
	protected StudijskiProgramService(CrudRepository<StudijskiProgram, Long> repository,
			Mapper<StudijskiProgramDTORecord, StudijskiProgram> mapper) {
		super(repository, mapper);
	}

}
