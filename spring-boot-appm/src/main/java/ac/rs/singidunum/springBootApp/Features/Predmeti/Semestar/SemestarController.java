package ac.rs.singidunum.springBootApp.Features.Predmeti.Semestar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Features.Predmeti.Semestar.SemestarDTO.SemestarDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;



@Controller
@RequestMapping("api/semestri")
public class SemestarController extends GenericCrudController<SemestarDTORecord, Semestar, Long> {

	@Autowired
	private SemestarService semestarService;
	
	@Override
	protected CrudService<SemestarDTORecord, Semestar, Long> getService() {
		return semestarService;
	}

}
