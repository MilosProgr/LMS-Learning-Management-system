package ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija.GodinaStudijaDTO.GodinaStudijaDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;



@RestController
@RequestMapping("/api/godinaStudija")
public class GodinaStudijaController extends GenericCrudController<GodinaStudijaDTORecord, GodinaStudija, Long> {
	@Autowired
	private GodinaStudijaService gStudija;

	@Override
	protected CrudService<GodinaStudijaDTORecord, GodinaStudija, Long> getService() {
		// TODO Auto-generated method stub
		return gStudija;
	}
}
