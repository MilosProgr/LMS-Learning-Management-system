package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.implementacija.Predmet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet.NastavniMaterijalDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.NastavniMaterijal;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Predmet.NastavniMaterijalService;

@Controller
@RequestMapping("api/nasMaterijali")
public class NastavniMaterijalController extends GenericCrudController<NastavniMaterijalDTO, NastavniMaterijal, Long> {

	@Autowired
	private NastavniMaterijalService nService;
	
	@Override
	protected CrudService<NastavniMaterijalDTO, NastavniMaterijal, Long> getService() {
		// TODO Auto-generated method stub
		return nService;
	}

}
