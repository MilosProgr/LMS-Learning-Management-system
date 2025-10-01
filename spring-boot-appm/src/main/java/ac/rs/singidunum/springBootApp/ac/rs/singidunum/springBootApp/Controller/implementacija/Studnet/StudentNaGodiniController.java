package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.implementacija.Studnet;


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

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Student.StudentNaGodiniDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.StudentNaGodini;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Student.StudentNaGodiniService;

@Controller
@RequestMapping("/api/studentNaGodini")
public class StudentNaGodiniController extends GenericCrudController<StudentNaGodiniDTO, StudentNaGodini, Long> {
	
	@Autowired
	private StudentNaGodiniService sgService;
	
	@Override
	protected CrudService<StudentNaGodiniDTO, StudentNaGodini, Long> getService() {
		// TODO Auto-generated method stub
		return sgService;
	}
	
}
