package ac.rs.singidunum.springBootApp.Features.Mesto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Features.Mesto.MestoDTO.MestoDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;

@Controller
@RequestMapping("api/mesta")
public class MestoController extends GenericCrudController<MestoDTORecord, Mesto, Long> {
	@Autowired
	private MestoService mestoService;

	@Override
	protected CrudService<MestoDTORecord, Mesto, Long> getService() {
		// TODO Auto-generated method stub
		return mestoService;
	}
	
}
