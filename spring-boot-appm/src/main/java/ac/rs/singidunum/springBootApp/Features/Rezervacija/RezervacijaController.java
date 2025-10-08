package ac.rs.singidunum.springBootApp.Features.Rezervacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;

import java.util.Map;

@Controller
@RequestMapping("/api/rezervacije")
public class RezervacijaController extends GenericCrudController<RezervacijaDTO, Rezervacija, Long> {

    @Autowired
    private RezervacijaService rezervacijaService;

    @Override
    protected CrudService<RezervacijaDTO, Rezervacija, Long> getService() {
        return rezervacijaService;
    }
}
