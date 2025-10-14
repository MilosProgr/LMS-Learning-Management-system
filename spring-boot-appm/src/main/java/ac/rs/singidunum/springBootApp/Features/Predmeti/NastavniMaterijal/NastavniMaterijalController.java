package ac.rs.singidunum.springBootApp.Features.Predmeti.NastavniMaterijal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Features.Predmeti.NastavniMaterijal.NastavniMaterijalDTO.NastavniMaterijalDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;


@Controller
@RequestMapping("api/nasMaterijali")
public class NastavniMaterijalController extends GenericCrudController<NastavniMaterijalDTORecord, NastavniMaterijal, Long> {

	@Autowired
	private NastavniMaterijalService nService;
	
	@Override
	protected CrudService<NastavniMaterijalDTORecord, NastavniMaterijal, Long> getService() {
		// TODO Auto-generated method stub
		return nService;
	}

}
