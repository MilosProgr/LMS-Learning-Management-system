package ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik.UdzbenikDTO.UdzbenikDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;



@Controller
@RequestMapping("/api/udzbenici")
public class UdzbenikController extends GenericCrudController<UdzbenikDTORecord, Udzbenik, Long> {

	@Autowired
	private UdzbenikService udzbenikService;
	
	@Override
	protected CrudService<UdzbenikDTORecord, Udzbenik, Long> getService() {
		// TODO Auto-generated method stub
		return udzbenikService;
	}

}
