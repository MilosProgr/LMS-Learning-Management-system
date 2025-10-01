package ac.rs.singidunum.springBootApp.Controller.implementacija.Predmet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.DTO.predmet.IshodDTO;
import ac.rs.singidunum.springBootApp.Model.Predmet.Ishod;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.Service.implementacija.Predmet.IshodService;

@Controller
@RequestMapping("api/ishodi")
public class IshodController extends GenericCrudController<IshodDTO, Ishod, Long> {
	
	@Autowired
	private IshodService ishodService;

	@Override
	protected CrudService<IshodDTO, Ishod, Long> getService() {
		// TODO Auto-generated method stub
		return ishodService;
	}

}
