package ac.rs.singidunum.springBootApp.Controller.implementacija.ObavestenjaAktivnosti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.DTO.ObavestenjaAktivnosti.ObavestenjeAktivnostDTO;
import ac.rs.singidunum.springBootApp.Model.ObavestenjeAktivnosti.ObavestenjeAktivnosti;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.Service.implementacija.ObavestenjaAktivnost.ObavestenjaAktivnostiService;

@Controller
@RequestMapping("api/obavestenjaAktivnost")
public class ObavestenjeAktivnostController extends GenericCrudController<ObavestenjeAktivnostDTO, ObavestenjeAktivnosti, Long> {
	@Autowired
	private ObavestenjaAktivnostiService obavestenjaAktivnostiService;

	@Override
	protected CrudService<ObavestenjeAktivnostDTO, ObavestenjeAktivnosti, Long> getService() {
		// TODO Auto-generated method stub
		return obavestenjaAktivnostiService;
	}
	
	@PatchMapping("/{id}/procitano")
	public ResponseEntity<Void> oznaciProcitano(@PathVariable Long id) {
	    obavestenjaAktivnostiService.oznaciKaoProcitano(id);
	    return ResponseEntity.ok().build();
	}

}
