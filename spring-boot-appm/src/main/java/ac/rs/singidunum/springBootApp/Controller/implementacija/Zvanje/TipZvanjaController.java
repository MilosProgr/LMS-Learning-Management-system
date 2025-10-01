package ac.rs.singidunum.springBootApp.Controller.implementacija.Zvanje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Controller.deklaracija.GenericCrudController;
import ac.rs.singidunum.springBootApp.DTO.Zvanje.TipZvanjaDTO;
import ac.rs.singidunum.springBootApp.Model.Zvanje.TipZvanja;
import ac.rs.singidunum.springBootApp.Service.deklaracija.CrudService;
import ac.rs.singidunum.springBootApp.Service.implementacija.Zvanje.TipZvanjaService;

@Controller
@RequestMapping("/api/tip-zvanja")
public class TipZvanjaController extends GenericCrudController<TipZvanjaDTO, TipZvanja, Long> {

    @Autowired
    private TipZvanjaService tipZvanjaService;

    @Override
    protected CrudService<TipZvanjaDTO, TipZvanja, Long> getService() {
        return tipZvanjaService;
    }
}
