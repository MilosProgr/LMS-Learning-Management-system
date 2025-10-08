package ac.rs.singidunum.springBootApp.Features.Predmeti.Kurs;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KursRepository extends CrudRepository<Kurs, Long> {

}
