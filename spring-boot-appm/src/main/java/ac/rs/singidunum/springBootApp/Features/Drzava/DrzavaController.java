package ac.rs.singidunum.springBootApp.Features.Drzava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Features.Drzava.DrzavaDTO.DrzavaDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;

@Controller
@RequestMapping("api/drzave")
public class DrzavaController extends GenericCrudController<DrzavaDTORecord, Drzava, Long> {
	@Autowired
	DrzavaService dService;

	@Override
	protected CrudService<DrzavaDTORecord, Drzava, Long> getService() {
		// TODO Auto-generated method stub
		return dService;
	}
}
