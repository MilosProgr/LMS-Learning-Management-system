package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Controller.deklaracija;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.*;

public abstract class GenericCrudController<T,E,ID> implements CrudController<T, E, ID> {
	
	protected abstract CrudService<T, E, ID> getService();

    @Override
    public ResponseEntity<List<T>> getAll() {
        List<T> entities = getService().getAll();
        return entities.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entities);
    }

    @Override
    public ResponseEntity<T> getById(ID id) {
        T entity = getService().getById(id);
        return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<T> create(E entity) {
        T createdEntity = getService().save(entity);
        return createdEntity != null ? ResponseEntity.status(HttpStatus.CREATED).body(createdEntity) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<T> update(ID id, E entity) {
        T existingEntity = getService().getById(id);
        
        if (existingEntity == null) {
            return ResponseEntity.notFound().build();
        }
        
        T updatedEntity = getService().update(entity);
        
        return updatedEntity != null ? ResponseEntity.ok(updatedEntity) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<T> delete(ID id) {
        boolean deleted = getService().delete(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
    
    

}
