package ac.rs.singidunum.springBootApp.Features.Predmeti.Udzbenik;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UdzbenikRepository extends CrudRepository<Udzbenik, Long> {

}
