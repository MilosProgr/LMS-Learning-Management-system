package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Nastavnici;

import org.springframework.data.repository.CrudRepository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Nastavnici.Univerzitet;

public interface UniverzitetRepository extends CrudRepository<Univerzitet, Long> {
	
    boolean existsByNazivIgnoreCase(String naziv);
    boolean existsByNazivIgnoreCaseAndIdNot(String naziv, Long id);
    
    boolean existsByRektorId(Long rektorId);
    boolean existsByRektorIdAndIdNot(Long rektorId, Long id);

}
