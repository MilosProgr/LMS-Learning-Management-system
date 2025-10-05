package ac.rs.singidunum.springBootApp.Features.Zvanje.Zvanje;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class ZvanjeService extends GenericCrudService<ZvanjeDTO, Zvanje, Long> {

    protected ZvanjeService(CrudRepository<Zvanje, Long> repository, Mapper<ZvanjeDTO, Zvanje> mapper) {
        super(repository, mapper);
    }
}
