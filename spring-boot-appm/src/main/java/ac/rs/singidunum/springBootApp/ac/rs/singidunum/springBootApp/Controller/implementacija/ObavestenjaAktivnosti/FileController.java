package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.implementacija.ObavestenjaAktivnosti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.ObavestenjaAktivnosti.FileDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.File;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Predmet.FileService;
//promenjena ruta radi jasnoce
@Controller
@RequestMapping("api/file/instrumentiEvaluacije")
public class FileController extends GenericCrudController<FileDTO, File, Long> {
	@Autowired
	private FileService fileService;

	@Override
	protected CrudService<FileDTO, File, Long> getService() {
		return fileService;
	}
}
