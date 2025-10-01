package ac.rs.singidunum.springBootApp.Controller.implementacija.Rezervacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.DTO.Rezervacija.RezervacijaDTO;
import ac.rs.singidunum.springBootApp.Model.Rezervacija.Rezervacija;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.Service.implementacija.Rezervacija.RezervacijaService;

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
