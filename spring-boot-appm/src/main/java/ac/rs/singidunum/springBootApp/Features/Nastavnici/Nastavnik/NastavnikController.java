package ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Aspect.Logged;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovanKorisnikRepository;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;

@Controller
@RequestMapping("/api/nastavnici")
public class NastavnikController extends GenericCrudController<NastavnikDTO, Nastavnik, Long> {
	
	@Autowired
	private NastavnikService nastavnikService;
	
    @Autowired
    private RegistrovanKorisnikRepository registrovaniKorisnikRepository;

    @Autowired
    private NastavnikRepository nastavnikRepository;
	
	@Override
	protected CrudService<NastavnikDTO, Nastavnik, Long> getService() {
		return nastavnikService;
	}
	
	@Logged
	@Secured({"ROLE_NASTAVNIK","ROLE_ADMIN"})
	@GetMapping
	public ResponseEntity<List<NastavnikDTO>> getAll(){
		return super.getAll();
	}
	
	@Logged
	@Secured({"ROLE_NASTAVNIK","ROLE_ADMIN"})
	@GetMapping("/{id}")
	public ResponseEntity<NastavnikDTO> getById(Long id){
		return super.getById(id);
	}
	
	@Logged
	@Secured({"ROLE_NASTAVNIK","ROLE_ADMIN"})
	public ResponseEntity<NastavnikDTO> create (Nastavnik n){
		return super.create(n);
	}
	
	@Logged
	@Secured({"ROLE_NASTAVNIK","ROLE_ADMIN"})
	public ResponseEntity<NastavnikDTO> update(Long id,Nastavnik n){
		return super.update(id, n);
	}
	
	@Logged
	@Secured({"ROLE_NASTAVNIK","ROLE_ADMIN"})
	public ResponseEntity<NastavnikDTO> delete(Long id){
		return super.delete(id);
	}
	@GetMapping("/slobodni-nastavnici-fakultet")
	public ResponseEntity<List<NastavnikDTO>> getSlobodniNastavnici(){
		return ResponseEntity.ok(nastavnikService.slobodniNastavniciFakultet());
	}
	
	@GetMapping("/slobodni-nastavnici-univerzitet")
	public ResponseEntity<List<NastavnikDTO>> getUniverzitetslobodniNastavnici(){
		return ResponseEntity.ok(nastavnikService.slobodniNastavniciUniverzitet());
	}
	
}
