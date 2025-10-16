package ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji.NastavnikNaRealizacijiDTO.NastavnikNaRealizacijiDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;



@Controller
@RequestMapping("/api/nastavniciNaRealizaciji")
public class NastavnikNaRealizacijiController extends GenericCrudController<NastavnikNaRealizacijiDTORecord, NastavnikNaRealizaciji, Long> {
	@Autowired
	private NastavniknaRealizacijiService nService;

	@Override
	protected CrudService<NastavnikNaRealizacijiDTORecord, NastavnikNaRealizaciji, Long> getService() {
		// TODO Auto-generated method stub
		return nService;
	}
}
