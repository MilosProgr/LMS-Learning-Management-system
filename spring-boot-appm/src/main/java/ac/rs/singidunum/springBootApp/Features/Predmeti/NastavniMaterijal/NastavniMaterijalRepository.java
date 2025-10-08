package ac.rs.singidunum.springBootApp.Features.Predmeti.NastavniMaterijal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NastavniMaterijalRepository extends CrudRepository<NastavniMaterijal, Long> {
}
