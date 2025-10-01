package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Zvanje;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Zvanje.ZvanjeDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Zvanje.Zvanje;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ZvanjeService extends GenericCrudService<ZvanjeDTO, Zvanje, Long> {

    protected ZvanjeService(CrudRepository<Zvanje, Long> repository, Mapper<ZvanjeDTO, Zvanje> mapper) {
        super(repository, mapper);
    }
}
