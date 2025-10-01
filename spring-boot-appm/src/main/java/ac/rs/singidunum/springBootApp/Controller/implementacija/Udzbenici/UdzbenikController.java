package ac.rs.singidunum.springBootApp.Controller.implementacija.Udzbenici;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.DTO.Udzbenici.UdzbenikDTO;
import ac.rs.singidunum.springBootApp.Model.Udzbenici.Udzbenik;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.Service.implementacija.Udzbenici.UdzbenikService;

@Controller
@RequestMapping("/api/udzbenici")
public class UdzbenikController extends GenericCrudController<UdzbenikDTO, Udzbenik, Long> {

	@Autowired
	private UdzbenikService udzbenikService;
	
	@Override
	protected CrudService<UdzbenikDTO, Udzbenik, Long> getService() {
		// TODO Auto-generated method stub
		return udzbenikService;
	}

}
