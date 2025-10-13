package ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.PermissionDTO.PermissionDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;




@Controller
@RequestMapping("api/uloge")
public class PermissionController extends GenericCrudController<PermissionDTORecord, Permission, Long> {
	@Autowired
	PermissionService oService;

	@Override
	protected CrudService<PermissionDTORecord, Permission, Long> getService() {
		// TODO Auto-generated method stub
		return oService;
	}
}
