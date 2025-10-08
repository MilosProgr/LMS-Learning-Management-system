package ac.rs.singidunum.springBootApp.Generics.Service;

import java.util.List;

public interface CrudService<T,E,ID> {
	List<T> getAll();

    T getById(ID id);

    T save(E entity);

    T update(E entity);
    
    boolean delete(ID id);
}

