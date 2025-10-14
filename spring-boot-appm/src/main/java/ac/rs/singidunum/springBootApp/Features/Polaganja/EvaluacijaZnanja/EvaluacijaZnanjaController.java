package ac.rs.singidunum.springBootApp.Features.Polaganja.EvaluacijaZnanja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Features.Polaganja.EvaluacijaZnanja.EvaluacijaZnanjaDTO.EvaluacijaZnanjaDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;



@Controller
@RequestMapping("/api/evaluacijaZnanja")
public class EvaluacijaZnanjaController extends GenericCrudController<EvaluacijaZnanjaDTORecord, EvaluacijaZnanja, Long> {
	@Autowired
	private EvaluacijaZnanjaService evaluacijaZnanjaService;
	@Override
	protected CrudService<EvaluacijaZnanjaDTORecord, EvaluacijaZnanja, Long> getService() {
		// TODO Auto-generated method stub
		return evaluacijaZnanjaService;
	}
	
    @PostMapping("/upis-bodova")
    public ResponseEntity<EvaluacijaZnanjaDTORecord> kreiraj(@RequestBody CreateEvaluacijaZnanjaRequest req) {
    	EvaluacijaZnanjaDTORecord dto = evaluacijaZnanjaService.kreiraj(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

}
