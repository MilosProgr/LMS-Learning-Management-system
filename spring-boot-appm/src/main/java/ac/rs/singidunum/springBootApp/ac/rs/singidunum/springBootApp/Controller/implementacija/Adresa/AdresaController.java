package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.implementacija.Adresa;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Adresa.AdresaDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Adresa.Adresa;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Adresa.AdresaService;

//@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/api/adrese")
public class AdresaController extends GenericCrudController<AdresaDTO, Adresa, Long> {
	@Autowired
	private AdresaService adresaService;

	@Override
	protected CrudService<AdresaDTO, Adresa, Long> getService() {
		// TODO Auto-generated method stub
		return adresaService;
	}

	
	
	
}
