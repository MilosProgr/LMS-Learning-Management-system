package ac.rs.singidunum.springBootApp.Features.Zvanje.Zvanje;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.Zvanje.Zvanje.ZvanjeDTO.ZvanjeDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;



@Service
public class ZvanjeService extends GenericCrudService<ZvanjeDTORecord, Zvanje, Long> {

    protected ZvanjeService(CrudRepository<Zvanje, Long> repository, Mapper<ZvanjeDTORecord, Zvanje> mapper) {
        super(repository, mapper);
    }
}
