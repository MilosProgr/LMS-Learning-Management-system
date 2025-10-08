package ac.rs.singidunum.springBootApp.Features.Polaganja.TipEvaluacije;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;



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
