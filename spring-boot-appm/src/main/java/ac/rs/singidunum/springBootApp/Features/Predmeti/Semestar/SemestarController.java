package ac.rs.singidunum.springBootApp.Features.Predmeti.Semestar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;



@Controller
@RequestMapping("api/semestri")
public class SemestarController extends GenericCrudController<SemestarDTO, Semestar, Long> {

	@Autowired
	private SemestarService semestarService;
	
	@Override
	protected CrudService<SemestarDTO, Semestar, Long> getService() {
		return semestarService;
	}

}
