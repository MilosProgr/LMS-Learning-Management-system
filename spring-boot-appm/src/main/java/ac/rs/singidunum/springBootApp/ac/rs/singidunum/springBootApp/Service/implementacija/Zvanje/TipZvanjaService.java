package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Zvanje;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Zvanje.TipZvanjaDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Zvanje.TipZvanja;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class TipZvanjaService extends GenericCrudService<TipZvanjaDTO, TipZvanja, Long> {
    protected TipZvanjaService(CrudRepository<TipZvanja, Long> repository, Mapper<TipZvanjaDTO, TipZvanja> mapper) {
        super(repository, mapper);
    }
}
