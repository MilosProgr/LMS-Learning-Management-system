package ac.rs.singidunum.springBootApp.Features.Fakultet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Features.Fakultet.FakultetDTO.FakultetDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;



//@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/api/fakulteti")
public class FakultetController extends GenericCrudController<FakultetDTORecord, Fakultet, Long> {
	@Autowired
	private FakultetService fakultetService;

	@Override
	protected CrudService<FakultetDTORecord, Fakultet, Long> getService() {
		// TODO Auto-generated method stub
		return fakultetService;
	}
	
    @PostMapping("/fakultetAdd")
    public ResponseEntity<FakultetDTORecord> createFakultet(@RequestBody FakultetRequest req) {
        req.setId(null);
        return ResponseEntity.ok(fakultetService.upSetFakultet(req));
    }

    @PutMapping("/fakultetEdit/{id}")
    public ResponseEntity<FakultetDTORecord> updateFakultet(@PathVariable Long id, @RequestBody FakultetRequest req) {
        req.setId(id);
        return ResponseEntity.ok(fakultetService.upSetFakultet(req));
    }
	

	
	
	
	
}
