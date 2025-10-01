package ac.rs.singidunum.springBootApp.Repository.Predmet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.Model.Predmet.EvaluacijaZnanja;

@Repository
public interface EvaluacijaZnanjaRepository extends CrudRepository<EvaluacijaZnanja, Long> {

}
