package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.implementacija.Studnet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Student.GodinaStudijaDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.GodinaStudija;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Student.GodinaStudijaService;

@RestController
@RequestMapping("/api/godinaStudija")
public class GodinaStudijaController extends GenericCrudController<GodinaStudijaDTO, GodinaStudija, Long> {
	@Autowired
	private GodinaStudijaService gStudija;

	@Override
	protected CrudService<GodinaStudijaDTO, GodinaStudija, Long> getService() {
		// TODO Auto-generated method stub
		return gStudija;
	}
}
