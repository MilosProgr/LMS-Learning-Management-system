package ac.rs.singidunum.springBootApp.Controller.implementacija.Nastavnici;

import java.util.List;
import java.util.Map;

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

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.DTO.Nastavnici.UniverzitetDTO;
import ac.rs.singidunum.springBootApp.DTO.Nastavnici.UniverzitetRequest;
import ac.rs.singidunum.springBootApp.Model.Nastavnici.Univerzitet;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.Service.implementacija.Nastavnici.UniverzitetService;

//@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/api/univerziteti")
public class UniverzitetController extends GenericCrudController<UniverzitetDTO, Univerzitet, Long> {
	@Autowired
	private UniverzitetService univerzitetService;

	@Override
	protected CrudService<UniverzitetDTO, Univerzitet, Long> getService() {
		// TODO Auto-generated method stub
		return univerzitetService;
	}
	
    @PostMapping("/univeriztetAdd")
    public ResponseEntity<UniverzitetDTO> createUniverzitet(@RequestBody UniverzitetRequest req) {
        req.setId(null);
        return ResponseEntity.ok(univerzitetService.upSetUniverzitet(req));
    }

    @PutMapping("/univerzitetEdit/{id}")
    public ResponseEntity<UniverzitetDTO> updateUniverzitet(@PathVariable Long id,
                                                    @RequestBody UniverzitetRequest req) {
        req.setId(id);
        return ResponseEntity.ok(univerzitetService.upSetUniverzitet(req));
    }
	
}
