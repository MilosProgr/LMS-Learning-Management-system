package ac.rs.singidunum.springBootApp.Features.Zvanje.TipZvanja;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipZvanjaRepository extends CrudRepository<TipZvanja, Long> {
}
