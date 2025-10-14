package ac.rs.singidunum.springBootApp.Features.Admin;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ac.rs.singidunum.springBootApp.Features.Admin.AdministratorDTO.AdministratorDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;

//import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovanKorisnikRepository;



@Controller
@RequestMapping("/api/administratori")
public class AdministratorController extends GenericCrudController<AdministratorDTORecord, Administrator, Long> {

    @Autowired
    private AdministratorService adminService;

//    @Autowired
//    private RegistrovanKorisnikRepository registrovaniKorisnikRepository;
//
//    @Autowired
//    private AdministratorRepository administratorRepository;

    @Override
    public CrudService<AdministratorDTORecord, Administrator, Long> getService() {
        return adminService;
    }
}
