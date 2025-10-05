package ac.rs.singidunum.springBootApp.Service.implementacija.Predmet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.DTO.Predmet.StudijskiProgramDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Predmet.StudijskiProgram;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class StudijskiProgramService extends GenericCrudService<StudijskiProgramDTO, StudijskiProgram, Long>{

	@Autowired
	protected StudijskiProgramService(CrudRepository<StudijskiProgram, Long> repository,
			Mapper<StudijskiProgramDTO, StudijskiProgram> mapper) {
		super(repository, mapper);
	}

}
