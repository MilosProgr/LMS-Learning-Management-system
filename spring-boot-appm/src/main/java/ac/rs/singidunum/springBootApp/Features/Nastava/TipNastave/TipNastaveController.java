package ac.rs.singidunum.springBootApp.Features.Nastava.TipNastave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;


@Controller
@RequestMapping("/api/tipNastave")
public class TipNastaveController extends GenericCrudController<TipNastaveDTO, TipNastave, Long> {
	@Autowired
	private TipNastaveService tnService;

	@Override
	protected CrudService<TipNastaveDTO, TipNastave, Long> getService() {
		// TODO Auto-generated method stub
		return tnService;
	}
}
