package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.implementacija.Nastavnici;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet.RealizacijaPredmetaDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.implementacija.Nastavnici.RealizacijaPredmetaMapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.RealizacijaPredmeta;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Predmet.RealizacijaPredmetaService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/realizacijaPredmeta")
public class RealizacijaPredmetaController extends GenericCrudController<RealizacijaPredmetaDTO, RealizacijaPredmeta, Long> {
	@Autowired
	private RealizacijaPredmetaService reaService;
	
	@Autowired
	private RealizacijaPredmetaMapper rMapper;

	@Override
	protected CrudService<RealizacijaPredmetaDTO, RealizacijaPredmeta, Long> getService() {
		return reaService;
	}
	
	@GetMapping("/predmet/{predmetId}/realizacija-termini")
    public ResponseEntity<List<RealizacijaPredmetaDTO>> getRealizacijeWithTerminiAndTipNastave(@PathVariable Long predmetId) {
        List<RealizacijaPredmeta> realizacije = reaService.getRealizacijeWithTerminiAndTipNastave(predmetId);
        return ResponseEntity.ok(rMapper.map(realizacije));
    }
}
