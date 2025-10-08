package ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;




@Controller
@RequestMapping("api/uloge")
public class PermissionController extends GenericCrudController<PermissionDTO, Permission, Long> {
	@Autowired
	PermissionService oService;

	@Override
	protected CrudService<PermissionDTO, Permission, Long> getService() {
		// TODO Auto-generated method stub
		return oService;
	}
}
