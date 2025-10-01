package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Zvanje;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Zvanje.Zvanje;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZvanjeRepository extends CrudRepository<Zvanje, Long> {
}
