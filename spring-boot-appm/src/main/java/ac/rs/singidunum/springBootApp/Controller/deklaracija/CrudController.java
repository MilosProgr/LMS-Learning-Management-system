package ac.rs.singidunum.springBootApp.Controller.deklaracija;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CrudController<T,E,ID> {
	@GetMapping
	ResponseEntity<List<T>> getAll();
	
	@GetMapping("/{id}")
    ResponseEntity<T> getById(@PathVariable ID id);
		
	@PostMapping
    ResponseEntity<T> create(@RequestBody E entity);
	
	@PutMapping("/{id}")
    ResponseEntity<T> update(@PathVariable ID id, @RequestBody E entity);
	
	@DeleteMapping("/{id}")
    ResponseEntity<T> delete(@PathVariable ID id);
	
}
