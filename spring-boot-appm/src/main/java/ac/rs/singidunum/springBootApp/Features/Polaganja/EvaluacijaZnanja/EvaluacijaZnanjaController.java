package ac.rs.singidunum.springBootApp.Features.Polaganja.EvaluacijaZnanja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.DTO.Predmet.CreateEvaluacijaZnanjaRequest;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;

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
