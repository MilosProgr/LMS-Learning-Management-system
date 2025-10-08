package ac.rs.singidunum.springBootApp.Features.Zvanje.TipZvanja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ac.rs.singidunum.springBootApp.Generics.Controller.GenericCrudController;
import ac.rs.singidunum.springBootApp.Generics.Service.CrudService;



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
