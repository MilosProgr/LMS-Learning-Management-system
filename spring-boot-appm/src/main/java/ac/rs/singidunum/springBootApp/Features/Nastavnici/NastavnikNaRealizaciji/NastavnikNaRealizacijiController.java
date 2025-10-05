package ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;

@Controller
@RequestMapping("/api/nastavniciNaRealizaciji")
public class NastavnikNaRealizacijiController extends GenericCrudController<NastavnikNaRealizacijiDTO, NastavnikNaRealizaciji, Long> {
	@Autowired
	private NastavniknaRealizacijiService nService;

	@Override
	protected CrudService<NastavnikNaRealizacijiDTO, NastavnikNaRealizaciji, Long> getService() {
		// TODO Auto-generated method stub
		return nService;
	}
}
