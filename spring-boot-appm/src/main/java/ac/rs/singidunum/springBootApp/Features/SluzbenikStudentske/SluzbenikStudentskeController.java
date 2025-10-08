package ac.rs.singidunum.springBootApp.Features.SluzbenikStudentske;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Aspect.Logged;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.Nastavnik;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovanKorisnikRepository;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;


@Controller
@RequestMapping("/api/sluzbenici")
public class SluzbenikStudentskeController extends GenericCrudController<SluzbenikStudentskeDTO, SluzbenikStudentske, Long>{

     @Autowired
     private SluzbenikStudentskeService sluzbenikStudentskeService;
     
     @Autowired
     private RegistrovanKorisnikRepository registrovaniKorisnikRepository;
     
     @Autowired
     private SluzbenikStudentskeRepository sluzbenikStudentskeRepository;

     @Override
     protected CrudService<SluzbenikStudentskeDTO, SluzbenikStudentske, Long> getService() {
		// TODO Auto-generated method stub
		return sluzbenikStudentskeService;
	}
     
    @Override
// 	@Logged
// 	@Secured({"ROLE_ADMIN"})
 	public ResponseEntity<List<SluzbenikStudentskeDTO>> getAll(){
 		return super.getAll();
 	}
 	
 	@Override
// 	@Secured({"ROLE_ADMIN"})
 	public ResponseEntity<SluzbenikStudentskeDTO> getById(Long id){
 		return super.getById(id);
 	}
 	
 	@Override
// 	@Secured({"ROLE_ADMIN"})
 	public ResponseEntity<SluzbenikStudentskeDTO> create (SluzbenikStudentske n){
 		return super.create(n);
 	}
 	
 	@Override
// 	@Secured({"ROLE_ADMIN"})
 	public ResponseEntity<SluzbenikStudentskeDTO> update(Long id,SluzbenikStudentske n){
 		return super.update(id, n);
 	}
 	
 	@Override
// 	@Secured({"ROLE_ADMIN"})
 	public ResponseEntity<SluzbenikStudentskeDTO> delete(Long id){
 		return super.delete(id);
 	}
    
}