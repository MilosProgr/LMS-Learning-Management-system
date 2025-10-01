package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.implementacija.Zvanje;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Zvanje.NaucnaOblastDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Zvanje.NaucnaOblast;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Zvanje.NaucnaOblastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/naucna-oblast")
public class NaucnaOblastController extends GenericCrudController<NaucnaOblastDTO, NaucnaOblast, Long> {

    @Autowired
    private NaucnaOblastService naucnaOblastService;

    @Override
    protected CrudService<NaucnaOblastDTO, NaucnaOblast, Long> getService() {
        return naucnaOblastService;
    }
}
