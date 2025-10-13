package ac.rs.singidunum.springBootApp.Features.Zvanje.NaucnaOblast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Features.Zvanje.NaucnaOblast.NaucnaOblastDTO.NaucnaOblastDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;



@Controller
@RequestMapping("/api/naucna-oblast")
public class NaucnaOblastController extends GenericCrudController<NaucnaOblastDTORecord, NaucnaOblast, Long> {

    @Autowired
    private NaucnaOblastService naucnaOblastService;

    @Override
    protected CrudService<NaucnaOblastDTORecord, NaucnaOblast, Long> getService() {
        return naucnaOblastService;
    }
}
