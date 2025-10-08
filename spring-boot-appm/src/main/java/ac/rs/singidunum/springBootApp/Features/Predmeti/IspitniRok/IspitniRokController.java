package ac.rs.singidunum.springBootApp.Features.Predmeti.IspitniRok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;



@RestController
@RequestMapping("/api/rokovi")
public class IspitniRokController extends GenericCrudController<IspitniRokDTO, IspitniRok, Long> {

	@Autowired
	private IspitniRokService iService;
	
	@Override
	protected CrudService<IspitniRokDTO, IspitniRok, Long> getService() {
		return iService;
	}
	
	@GetMapping("/aktivni")
	public List<IspitniRokDTO> getNeistekli() {
	    return iService.getRokoviKojiNisuProsli();
	}


}
