package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Zvanje;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Zvanje.NaucnaOblast;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaucnaOblastRepository extends CrudRepository<NaucnaOblast, Long> {
}
