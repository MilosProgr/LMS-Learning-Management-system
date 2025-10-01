package ac.rs.singidunum.springBootApp.Controller.implementacija.Nastavnici;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.DTO.Nastavnici.NastavnikNaRealizacijiDTO;
import ac.rs.singidunum.springBootApp.Model.Nastavnici.NastavnikNaRealizaciji;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.Service.implementacija.Nastavnici.NastavniknaRealizacijiService;

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
