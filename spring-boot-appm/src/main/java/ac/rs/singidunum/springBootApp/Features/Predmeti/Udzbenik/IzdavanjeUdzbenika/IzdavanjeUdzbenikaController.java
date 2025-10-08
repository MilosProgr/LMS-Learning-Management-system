package ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik.IzdavanjeUdzbenika;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;



@RestController
@RequestMapping("/api/izdavanjeUdzbenika")
public class IzdavanjeUdzbenikaController extends GenericCrudController<IzdavanjeUdzbenikaDTO, IzdavanjeUdzbenika, Long> {

	@Autowired
	private IzdavanjeUdzbenikaService izdavanjeUdzbenikaService;
	@Override
	protected CrudService<IzdavanjeUdzbenikaDTO, IzdavanjeUdzbenika, Long> getService() {
		// TODO Auto-generated method stub
		return izdavanjeUdzbenikaService;
	}

}
