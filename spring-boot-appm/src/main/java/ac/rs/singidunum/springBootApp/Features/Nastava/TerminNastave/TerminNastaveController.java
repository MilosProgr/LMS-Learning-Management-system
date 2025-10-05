package ac.rs.singidunum.springBootApp.Features.Nastava.TerminNastave;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;

@Controller
@RequestMapping("/api/tNastave")
public class TerminNastaveController extends GenericCrudController<TerminNastaveDTO, TerminNastave, Long> {
	@Autowired
	private TerminNastaveService tnService;
	
	@Autowired
	private TerminNastaveMapper tMapper;

	@Override
	protected CrudService<TerminNastaveDTO, TerminNastave, Long> getService() {
		// TODO Auto-generated method stub
		return tnService;
	}
	
	
}
