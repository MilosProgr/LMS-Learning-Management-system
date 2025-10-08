package ac.rs.singidunum.springBootApp.Features.Student;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.UpisStudentaRequest;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;


@Controller
@RequestMapping("/api/studenti")
public class StudentController extends GenericCrudController<StudentDTO, Student, Long> {
	@Autowired
	private StudentService studentService;
	
    
    

	@Override
	protected CrudService<StudentDTO, Student, Long> getService() {
		// TODO Auto-generated method stub
		return studentService;
	}
	
	 @RequestMapping(path = "/{id}",method = RequestMethod.GET)
	    public ResponseEntity<StudentDTO> getById(@PathVariable Long id) {
	        return super.getById(id);
	 }
	
	 @Override
//	 @Secured("ROLE_ADMIN")
	 @RequestMapping(path = "",method = RequestMethod.POST)
	 public ResponseEntity<StudentDTO> create(@RequestBody Student student) {
		 return super.create(student);
	 }

	 @Override
	 // @Secured("ROLE_ADMIN")
	 @RequestMapping(path = "/{id}",method = RequestMethod.PUT)
	 public ResponseEntity<StudentDTO> update(@PathVariable("id") Long id,@RequestBody Student student) {
	 	return super.update(id,student);
	 }
	
	 @Override
//	 @Secured("ROLE_ADMIN")
	 @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
	 public ResponseEntity<StudentDTO> delete(@PathVariable Long id) {
	     return super.delete(id);
	 }
	 
	 @PatchMapping("/{id}/stanje")
	 @ResponseBody
	 public ResponseEntity<StudentDTO> patchUvecajStanje(
	         @PathVariable("id") Long studentId,
	         @RequestBody Map<String, Object> body) {
	     try {
	         if (body == null || !body.containsKey("iznos")) {
	             return ResponseEntity.badRequest().build();
	         }
	         Object v = body.get("iznos");
	         Double iznos = (v instanceof Number) ? ((Number) v).doubleValue() : null;

	         StudentDTO dto = studentService.uvecajStanjeNaRacunu(studentId, iznos);
	         return ResponseEntity.ok(dto);

	     } catch (IllegalArgumentException e) {
	         return ResponseEntity.badRequest().build();
	     }
	 }
	 
    @PostMapping("/upis-studenta")
    public ResponseEntity<StudentDTO> upisiTransakciono(@RequestBody UpisStudentaRequest req) {
        StudentDTO dto = studentService.upisiStudenta(req);
        return ResponseEntity.ok(dto);
    }


	 
}
