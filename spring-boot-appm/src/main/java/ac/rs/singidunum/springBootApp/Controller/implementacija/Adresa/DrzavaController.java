package ac.rs.singidunum.springBootApp.Controller.implementacija.Adresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.DTO.Adresa.DrzavaDTO;
import ac.rs.singidunum.springBootApp.DTO.Adresa.DrzavaDTO.DrzavaDTORecord;
import ac.rs.singidunum.springBootApp.Model.Adresa.Drzava;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.Service.implementacija.Adresa.DrzavaService;

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
