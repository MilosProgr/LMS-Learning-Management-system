package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.implementacija.Polaganje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet.TipNastaveDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.TipNastave;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Predmet.TipNastaveService;

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
