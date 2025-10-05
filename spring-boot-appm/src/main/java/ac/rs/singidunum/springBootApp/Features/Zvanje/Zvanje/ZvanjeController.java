package ac.rs.singidunum.springBootApp.Features.Zvanje.Zvanje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;

@Controller
@RequestMapping("/api/zvanje")
public class ZvanjeController extends GenericCrudController<ZvanjeDTO, Zvanje, Long> {

    @Autowired
    private ZvanjeService zvanjeService;

    @Override
    protected CrudService<ZvanjeDTO, Zvanje, Long> getService() {
        return zvanjeService;
    }
}