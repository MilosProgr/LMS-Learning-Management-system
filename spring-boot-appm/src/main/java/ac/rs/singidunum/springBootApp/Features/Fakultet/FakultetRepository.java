package ac.rs.singidunum.springBootApp.Features.Fakultet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FakultetRepository extends CrudRepository<Fakultet, Long> {
    boolean existsByNazivIgnoreCase(String naziv);
    boolean existsByNazivIgnoreCaseAndIdNot(String naziv, Long id);
	
    boolean existsByNazivIgnoreCaseAndUniverzitet_Id(String naziv, Long univerzitetId);
    boolean existsByNazivIgnoreCaseAndUniverzitet_IdAndIdNot(String naziv, Long univerzitetId, Long id);
    boolean existsByDekan_Id(Long dekanId);
    boolean existsByDekan_IdAndIdNot(Long dekanId, Long id);
    
    long countByUniverzitetId(Long univerzitetId);
}
