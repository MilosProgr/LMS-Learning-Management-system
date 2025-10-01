package ac.rs.singidunum.springBootApp.Controller.implementacija.Predmet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.DTO.predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Mapper.implementacija.Predmet.PredmetMapper;
import ac.rs.singidunum.springBootApp.Model.Predmet.Predmet;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.Service.implementacija.Predmet.PredmetService;
@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping(path = "/api/predmeti")
public class PredmetController extends GenericCrudController<PredmetDTO, Predmet, Long> {
	
	@Autowired
	private PredmetService predmetService;
	
	@Autowired
	private PredmetMapper pMapper;

	@Override
	protected CrudService<PredmetDTO, Predmet, Long> getService() {
		// TODO Auto-generated method stub
		return predmetService;
	}
	
	@GetMapping("/program/{programId}")
    public ResponseEntity<List<PredmetDTO>> getPredmetiByProgram(@PathVariable Long programId) {
        List<Predmet> predmeti = predmetService.findPredmetiByProgramId(programId);
        
        if (predmeti.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pMapper.map(predmeti), HttpStatus.OK);
    }

}
