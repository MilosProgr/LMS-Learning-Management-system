package ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO.StudentNaGodiniDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;



@Controller
@RequestMapping("/api/studentNaGodini")
public class StudentNaGodiniController extends GenericCrudController<StudentNaGodiniDTORecord, StudentNaGodini, Long> {
	
	@Autowired
	private StudentNaGodiniService sgService;
	
	@Override
	protected CrudService<StudentNaGodiniDTORecord, StudentNaGodini, Long> getService() {
		// TODO Auto-generated method stub
		return sgService;
	}
	
}
