package ac.rs.singidunum.springBootApp.Features.Obavestenja.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Features.Obavestenja.File.FileDTO.FileDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;


//promenjena ruta radi jasnoce
@Controller
@RequestMapping("api/file/instrumentiEvaluacije")
public class FileController extends GenericCrudController<FileDTORecord, File, Long> {
	@Autowired
	private FileService fileService;

	@Override
	protected CrudService<FileDTORecord, File, Long> getService() {
		return fileService;
	}
}
