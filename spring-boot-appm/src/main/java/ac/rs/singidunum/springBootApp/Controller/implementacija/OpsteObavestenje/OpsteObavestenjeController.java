package ac.rs.singidunum.springBootApp.Controller.implementacija.OpsteObavestenje;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.DTO.OpsteObavestenje.OpsteObavestenjeDTO;
import ac.rs.singidunum.springBootApp.Model.OpsteObavestenje.OpsteObavestenje;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.Service.implementacija.Obavestenja.ObavestenjeService;

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
