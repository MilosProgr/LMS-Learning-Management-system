package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.implementacija.Nastavnici;

import java.util.List;

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
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Nastavnici.FakultetDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Nastavnici.FakultetRequest;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Nastavnici.Fakultet;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Nastavnici.FakultetService;

//@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/api/fakulteti")
public class FakultetController extends GenericCrudController<FakultetDTO, Fakultet, Long> {
	@Autowired
	private FakultetService fakultetService;

	@Override
	protected CrudService<FakultetDTO, Fakultet, Long> getService() {
		// TODO Auto-generated method stub
		return fakultetService;
	}
	
    @PostMapping("/fakultetAdd")
    public ResponseEntity<FakultetDTO> createFakultet(@RequestBody FakultetRequest req) {
        req.setId(null);
        return ResponseEntity.ok(fakultetService.upSetFakultet(req));
    }

    @PutMapping("/fakultetEdit/{id}")
    public ResponseEntity<FakultetDTO> updateFakultet(@PathVariable Long id, @RequestBody FakultetRequest req) {
        req.setId(id);
        return ResponseEntity.ok(fakultetService.upSetFakultet(req));
    }
	

	
	
	
	
}
