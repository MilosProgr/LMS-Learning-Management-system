package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.implementacija.Polaganje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet.CreateEvaluacijaZnanjaRequest;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet.EvaluacijaZnanjaDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.EvaluacijaZnanja;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Predmet.EvaluacijaZnanjaService;

@Controller
@RequestMapping("/api/evaluacijaZnanja")
public class EvaluacijaZnanjaController extends GenericCrudController<EvaluacijaZnanjaDTO, EvaluacijaZnanja, Long> {
	@Autowired
	private EvaluacijaZnanjaService evaluacijaZnanjaService;
	@Override
	protected CrudService<EvaluacijaZnanjaDTO, EvaluacijaZnanja, Long> getService() {
		// TODO Auto-generated method stub
		return evaluacijaZnanjaService;
	}
	
    @PostMapping("/upis-bodova")
    public ResponseEntity<EvaluacijaZnanjaDTO> kreiraj(@RequestBody CreateEvaluacijaZnanjaRequest req) {
        EvaluacijaZnanjaDTO dto = evaluacijaZnanjaService.kreiraj(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

}
