package ac.rs.singidunum.springBootApp.Repository.Zvanje;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.Model.Zvanje.NaucnaOblast;

@Repository
public interface NaucnaOblastRepository extends CrudRepository<NaucnaOblast, Long> {
}
