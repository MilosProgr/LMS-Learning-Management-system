package ac.rs.singidunum.springBootApp.Features.Univerzitet;

import org.springframework.data.repository.CrudRepository;

public interface UniverzitetRepository extends CrudRepository<Univerzitet, Long> {
	
    boolean existsByNazivIgnoreCase(String naziv);
    boolean existsByNazivIgnoreCaseAndIdNot(String naziv, Long id);
    
    boolean existsByRektorId(Long rektorId);
    boolean existsByRektorIdAndIdNot(Long rektorId, Long id);

}
