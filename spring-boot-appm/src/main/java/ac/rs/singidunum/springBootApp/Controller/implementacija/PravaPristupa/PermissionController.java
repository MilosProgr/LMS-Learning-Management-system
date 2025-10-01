package ac.rs.singidunum.springBootApp.Controller.implementacija.PravaPristupa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.DTO.PravaPristupa.PermissionDTO;
import ac.rs.singidunum.springBootApp.Model.PravaPristupa.Permission;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.Service.implementacija.PravaPristupa.PermissionService;


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
