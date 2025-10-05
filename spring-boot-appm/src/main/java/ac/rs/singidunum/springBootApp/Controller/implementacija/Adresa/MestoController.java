package ac.rs.singidunum.springBootApp.Controller.implementacija.Adresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.DTO.Adresa.MestoDTO;
import ac.rs.singidunum.springBootApp.DTO.Adresa.MestoDTO.MestoDTORecord;
import ac.rs.singidunum.springBootApp.Model.Adresa.Mesto;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.Service.implementacija.Adresa.MestoService;

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
