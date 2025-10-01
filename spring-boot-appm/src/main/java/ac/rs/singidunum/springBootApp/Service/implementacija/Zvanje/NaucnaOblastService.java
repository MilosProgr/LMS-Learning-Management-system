package ac.rs.singidunum.springBootApp.Service.implementacija.Zvanje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.DTO.Zvanje.NaucnaOblastDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Zvanje.NaucnaOblast;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class NaucnaOblastService extends GenericCrudService<NaucnaOblastDTO, NaucnaOblast, Long> {

    @Autowired
    protected NaucnaOblastService(CrudRepository<NaucnaOblast, Long> repository, Mapper<NaucnaOblastDTO, NaucnaOblast> mapper) {
        super(repository, mapper);
    }
}
