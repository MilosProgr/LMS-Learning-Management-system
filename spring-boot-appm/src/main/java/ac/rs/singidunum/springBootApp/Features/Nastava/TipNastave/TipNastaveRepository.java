package ac.rs.singidunum.springBootApp.Features.Nastava.TipNastave;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipNastaveRepository extends CrudRepository<TipNastave, Long>{

}
