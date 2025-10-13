package ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti.ObavestenjeAktivnostDTO.ObavestenjeAktivnostiDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;



@Controller
@RequestMapping("api/obavestenjaAktivnost")
public class ObavestenjeAktivnostController extends GenericCrudController<ObavestenjeAktivnostiDTORecord, ObavestenjeAktivnosti, Long> {
	@Autowired
	private ObavestenjaAktivnostiService obavestenjaAktivnostiService;

	@Override
	protected CrudService<ObavestenjeAktivnostiDTORecord, ObavestenjeAktivnosti, Long> getService() {
		// TODO Auto-generated method stub
		return obavestenjaAktivnostiService;
	}
	
	@PatchMapping("/{id}/procitano")
	public ResponseEntity<Void> oznaciProcitano(@PathVariable Long id) {
	    obavestenjaAktivnostiService.oznaciKaoProcitano(id);
	    return ResponseEntity.ok().build();
	}

}
