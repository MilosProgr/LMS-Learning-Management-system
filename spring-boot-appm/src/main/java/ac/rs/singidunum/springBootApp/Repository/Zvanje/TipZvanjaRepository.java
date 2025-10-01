package ac.rs.singidunum.springBootApp.Repository.Zvanje;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.Model.Zvanje.TipZvanja;

@Repository
public interface TipZvanjaRepository extends CrudRepository<TipZvanja, Long> {
}
