package ac.rs.singidunum.springBootApp.Features.Obavestenja.OpstaObavestenja;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;



@Controller
@RequestMapping("api/opstaObavestenja")
public class OpsteObavestenjeController extends GenericCrudController<OpsteObavestenjeDTO, OpsteObavestenje, Long> {
	@Autowired
	ObavestenjeService oService;

	@Override
	protected CrudService<OpsteObavestenjeDTO, OpsteObavestenje, Long> getService() {
		// TODO Auto-generated method stub
		return oService;
	}
}
