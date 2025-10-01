package ac.rs.singidunum.springBootApp.Controller.implementacija.SluzbenikStudentski;

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
import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.DTO.Nastavnici.NastavnikDTO;
import ac.rs.singidunum.springBootApp.DTO.SluzbenikStudentske.SluzbenikStudentskeDTO;
import ac.rs.singidunum.springBootApp.Model.Nastavnici.Nastavnik;
import ac.rs.singidunum.springBootApp.Model.SluzbenikStudentske.SluzbenikStudentske;
import ac.rs.singidunum.springBootApp.Model.Student.Student;
import ac.rs.singidunum.springBootApp.Repository.SluzbenikStudentske.SluzbenikStudentskeRepository;
import ac.rs.singidunum.springBootApp.Repository.Student.RegistrovanKorisnikRepository;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.Service.implementacija.SluzbenikStudentske.SluzbenikStudentskeService;

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