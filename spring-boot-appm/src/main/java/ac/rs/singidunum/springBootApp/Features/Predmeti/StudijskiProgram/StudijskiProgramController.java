package ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;


@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("api/programi")
public class StudijskiProgramController extends GenericCrudController<StudijskiProgramDTO, StudijskiProgram, Long> {
	@Autowired
	StudijskiProgramService sService;

	@Override
	protected CrudService<StudijskiProgramDTO, StudijskiProgram, Long> getService() {
		return sService;
	}
}
