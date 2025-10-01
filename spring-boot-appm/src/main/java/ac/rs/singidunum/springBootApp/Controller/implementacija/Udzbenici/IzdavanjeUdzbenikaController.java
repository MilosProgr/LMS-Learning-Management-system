package ac.rs.singidunum.springBootApp.Controller.implementacija.Udzbenici;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.DTO.Udzbenici.IzdavanjeUdzbenikaDTO;
import ac.rs.singidunum.springBootApp.Model.Udzbenici.IzdavanjeUdzbenika;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.Service.implementacija.Udzbenici.IzdavanjeUdzbenikaService;

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
