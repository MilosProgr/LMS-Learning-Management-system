package ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta.RealizacijaPredmetaDTO.RealizacijaPredmetaDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;



@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/realizacijaPredmeta")
public class RealizacijaPredmetaController extends GenericCrudController<RealizacijaPredmetaDTORecord, RealizacijaPredmeta, Long> {
	@Autowired
	private RealizacijaPredmetaService reaService;
	
	@Autowired
	private RealizacijaPredmetaMapper rMapper;

	@Override
	protected CrudService<RealizacijaPredmetaDTORecord, RealizacijaPredmeta, Long> getService() {
		return reaService;
	}
	
	@GetMapping("/predmet/{predmetId}/realizacija-termini")
    public ResponseEntity<List<RealizacijaPredmetaDTORecord>> getRealizacijeWithTerminiAndTipNastave(@PathVariable Long predmetId) {
        List<RealizacijaPredmeta> realizacije = reaService.getRealizacijeWithTerminiAndTipNastave(predmetId);
        return ResponseEntity.ok(rMapper.map(realizacije));
    }
}
