package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija;

import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;

import org.springframework.data.repository.CrudRepository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.base.BaseEntity;
import jakarta.persistence.EntityNotFoundException;


public abstract class GenericCrudService<DTO,ENTITY extends BaseEntity<ID>,ID> implements CrudService<DTO, ENTITY, ID> {
	
	private final CrudRepository<ENTITY, ID> repository;
    private final Mapper<DTO, ENTITY> mapper;

    protected GenericCrudService(CrudRepository<ENTITY, ID> repository, Mapper<DTO, ENTITY> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<DTO> getAll() {
    	
    	Iterable<ENTITY> entiteti = repository.findAll();
    	List<ENTITY> entityList = new ArrayList<>();
    	for(ENTITY e : entiteti) {
    		entityList.add(e);
    	}
//        List<ENTITY> entities = (List<ENTITY>) repository.findAll();
        return mapper.map(entityList);
    }

    @Override
    public DTO getById(ID id) {
        ENTITY entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entitet sa ID: " + id + " nije pronadjen!"));
        return mapper.map(entity);
    }

    @Override
    //@Transactional
    public DTO save(ENTITY entity) {
        ENTITY savedEntity = repository.save(entity);
        return mapper.map(savedEntity);
    }

    @Override
   // @Transactional
    public DTO update(ENTITY entity) {
        if (repository.existsById((ID) entity.getId())) { 
            ENTITY updatedEntity = repository.save(entity);
            return mapper.map(updatedEntity);
        }
        return null;
    }

    @Override
    //@Transactional
    public boolean delete(ID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}