package ac.rs.singidunum.springBootApp.Controller.implementacija.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.DTO.AdminDTO.AdministratorDTO;
import ac.rs.singidunum.springBootApp.Model.Admin.Administrator;
import ac.rs.singidunum.springBootApp.Model.Student.RegistrovaniKorisnik;
import ac.rs.singidunum.springBootApp.Model.Student.Student;
import ac.rs.singidunum.springBootApp.Repository.Admin.AdministratorRepository;
import ac.rs.singidunum.springBootApp.Repository.Student.RegistrovanKorisnikRepository;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.Service.implementacija.Admin.AdministratorService;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/api/administratori")
public class AdministratorController extends GenericCrudController<AdministratorDTO, Administrator, Long> {

    @Autowired
    private AdministratorService adminService;

    @Autowired
    private RegistrovanKorisnikRepository registrovaniKorisnikRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public CrudService<AdministratorDTO, Administrator, Long> getService() {
        return adminService;
    }
}
