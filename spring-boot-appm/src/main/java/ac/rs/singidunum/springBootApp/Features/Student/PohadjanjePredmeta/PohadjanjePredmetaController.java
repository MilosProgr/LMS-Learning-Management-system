package ac.rs.singidunum.springBootApp.Features.Student.PohadjanjePredmeta;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.Features.Student.PohadjanjePredmeta.PohadjanjePredmetaDTO.PohadjanjePredmetaDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;



@Controller
@RequestMapping("/api/PohadjanjePredmeta")
public class PohadjanjePredmetaController extends GenericCrudController<PohadjanjePredmetaDTORecord, PohadjanjePredmeta, Long> {
	@Autowired
	private PohadjanjePredmetaService pService;

	@Override
	protected CrudService<PohadjanjePredmetaDTORecord, PohadjanjePredmeta, Long> getService() {
		// TODO Auto-generated method stub
		return pService;
	}
	
    @PostMapping("/{studentNaGodiniId}")
    public ResponseEntity<List<PohadjanjePredmetaDTORecord>> dodajPredmete(
            @PathVariable Long studentNaGodiniId,
            @RequestBody List<Long> predmetiIds) {
        List<PohadjanjePredmetaDTORecord> result =
                pService.dodajPredmeteZaStudenta(studentNaGodiniId, predmetiIds);
        return ResponseEntity.ok(result);
    }

     //Vraca sva pohađanja za datog studenta na godini.
     //Primer: GET /api/PohadjanjePredmeta/student-na-godini/1
    @GetMapping("/student-na-godini/{studentNaGodiniId}")
    public ResponseEntity<List<PohadjanjePredmetaDTORecord>> getByStudentNaGodiniId(
            @PathVariable Long studentNaGodiniId) {
        List<PohadjanjePredmetaDTORecord> lista = pService.getByStudentNaGodiniId(studentNaGodiniId);
        return ResponseEntity.ok(lista);
    }
    
    
 // PATCH /api/pohadjanje-predmeta/{id}
    @PatchMapping(path = "/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<PohadjanjePredmetaDTORecord> patchKonacnaOcena(
            @PathVariable Long id,
            @RequestBody Map<String, Object> patch) {

        if (!patch.containsKey("konacnaOcena")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nedostaje polje konacnaOcena.");
        }
        Object val = patch.get("konacnaOcena");
        Long konacna = (val instanceof Number) ? ((Number) val).longValue() : null;
        if (konacna == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "konacnaOcena mora biti broj.");
        }

        PohadjanjePredmetaDTORecord dto = pService.upisiKonacnuOcenuById(id, konacna);
        return ResponseEntity.ok(dto);
    }
    
    
}
