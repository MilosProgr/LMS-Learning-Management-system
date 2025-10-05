package ac.rs.singidunum.springBootApp.Features.Polaganja.TipEvaluacije;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipEvaluacijeRepository extends CrudRepository<TipEvaluacije, Long>{

}
