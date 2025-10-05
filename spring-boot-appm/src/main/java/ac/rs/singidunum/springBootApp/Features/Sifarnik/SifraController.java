package ac.rs.singidunum.springBootApp.Features.Sifarnik;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;


//@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/api/sifre")
public class SifraController extends GenericCrudController<Map<String, Object>, Sifra, Long> {
	@Autowired
	private SifraService sifraService;

	@Override
	protected CrudService<Map<String, Object>, Sifra, Long> getService() {
		// TODO Auto-generated method stub
		return sifraService;
	}

	
	
	
}
