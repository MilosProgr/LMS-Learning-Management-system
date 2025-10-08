package ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;


@Controller
@RequestMapping("api/korisniciSaUlogama")
public class UserPermissionController extends GenericCrudController<UserPermissionDTO, UserPermission, Long> {
	@Autowired
	UserPermissionService oService;

	@Override
	protected CrudService<UserPermissionDTO, UserPermission, Long> getService() {
		// TODO Auto-generated method stub
		return oService;
	}
	
    @GetMapping("/lista")
    @ResponseBody
    public List<UserPermissionDTO> getKorisniciSaUlogama() {
        return oService.getKorisniciSaListomUloga();
    }
}

