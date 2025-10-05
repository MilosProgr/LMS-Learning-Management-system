package ac.rs.singidunum.springBootApp.Features.Zvanje.TipZvanja;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class TipZvanjaService extends GenericCrudService<TipZvanjaDTO, TipZvanja, Long> {
    protected TipZvanjaService(CrudRepository<TipZvanja, Long> repository, Mapper<TipZvanjaDTO, TipZvanja> mapper) {
        super(repository, mapper);
    }
}
