package ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IshodRepository extends CrudRepository<Ishod, Long> {

}
