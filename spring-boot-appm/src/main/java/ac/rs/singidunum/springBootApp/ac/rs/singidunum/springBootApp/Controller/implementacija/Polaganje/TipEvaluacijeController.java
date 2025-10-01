package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.implementacija.Polaganje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet.TipEvaluacijeDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.TipEvaluacije;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Predmet.TipEvaluacijeService;

@Controller
@RequestMapping("/api/tipEvaluacije")
public class TipEvaluacijeController extends GenericCrudController<TipEvaluacijeDTO, TipEvaluacije, Long> {
	@Autowired
	private TipEvaluacijeService twService;

	@Override
	protected CrudService<TipEvaluacijeDTO, TipEvaluacije, Long> getService() {
		// TODO Auto-generated method stub
		return twService;
	}
}
