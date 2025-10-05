package ac.rs.singidunum.springBootApp.Controller.implementacija.Predmet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.DTO.Predmet.IspitniRokDTO;
import ac.rs.singidunum.springBootApp.Model.Predmet.IspitniRok;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.Service.implementacija.Predmet.IspitniRokService;

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
