package ac.rs.singidunum.springBootApp.Features.Zvanje.NaucnaOblast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.Zvanje.NaucnaOblast.NaucnaOblastDTO.NaucnaOblastDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;



@Service
public class NaucnaOblastService extends GenericCrudService<NaucnaOblastDTORecord, NaucnaOblast, Long> {

    @Autowired
    protected NaucnaOblastService(CrudRepository<NaucnaOblast, Long> repository, Mapper<NaucnaOblastDTORecord, NaucnaOblast> mapper) {
        super(repository, mapper);
    }
}
