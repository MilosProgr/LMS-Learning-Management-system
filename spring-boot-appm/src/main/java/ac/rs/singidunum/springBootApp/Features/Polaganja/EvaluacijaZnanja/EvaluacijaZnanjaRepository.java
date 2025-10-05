package ac.rs.singidunum.springBootApp.Features.Polaganja.EvaluacijaZnanja;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacijaZnanjaRepository extends CrudRepository<EvaluacijaZnanja, Long> {

}
