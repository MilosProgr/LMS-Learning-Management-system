package ac.rs.singidunum.springBootApp.Features.Zvanje.TipZvanja;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;



@Service
public class TipZvanjaService extends GenericCrudService<TipZvanjaDTO, TipZvanja, Long> {
    protected TipZvanjaService(CrudRepository<TipZvanja, Long> repository, Mapper<TipZvanjaDTO, TipZvanja> mapper) {
        super(repository, mapper);
    }
}
