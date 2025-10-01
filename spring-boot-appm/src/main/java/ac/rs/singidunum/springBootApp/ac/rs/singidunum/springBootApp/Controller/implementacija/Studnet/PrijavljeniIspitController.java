package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.implementacija.Studnet;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Student.PrijavljeniIspitDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.PrijavljeniIspit;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Predmet.PrijavljeniIspitService;

@RestController 
@RequestMapping("/api/prijavljeniIspiti")
public class PrijavljeniIspitController extends GenericCrudController<PrijavljeniIspitDTO, PrijavljeniIspit, Long> {
	@Autowired
	private PrijavljeniIspitService prService;

	@Override
	protected CrudService<PrijavljeniIspitDTO, PrijavljeniIspit, Long> getService() {
		// TODO Auto-generated method stub
		return prService;
	}
	
	 @PostMapping(value = "/prijavi", consumes = "application/json")
	    public ResponseEntity<PrijavljeniIspitDTO> prijavi(@RequestBody Map<String, Object> body) {
	        Long studentId         = ((Number) body.get("studentId")).longValue();
	        Long studentNaGodiniId = ((Number) body.get("studentNaGodiniId")).longValue();
	        Long predmetId         = ((Number) body.get("predmetId")).longValue();
	        Long ispitniRokId      = ((Number) body.get("ispitniRokId")).longValue();
	        Double cenaPrijave     = body.get("cenaPrijave") != null
	                                  ? ((Number) body.get("cenaPrijave")).doubleValue()
	                                  : null;

	        PrijavljeniIspitDTO dto = prService.saveForStudentAndSng(
	                studentId, studentNaGodiniId, predmetId, ispitniRokId, cenaPrijave
	        );
	        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	    }
}
