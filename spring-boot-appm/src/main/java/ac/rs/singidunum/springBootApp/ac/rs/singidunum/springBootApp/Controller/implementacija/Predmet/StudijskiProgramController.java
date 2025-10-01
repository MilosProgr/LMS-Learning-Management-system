package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.implementacija.Predmet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet.StudijskiProgramDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.StudijskiProgram;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Predmet.StudijskiProgramService;

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
