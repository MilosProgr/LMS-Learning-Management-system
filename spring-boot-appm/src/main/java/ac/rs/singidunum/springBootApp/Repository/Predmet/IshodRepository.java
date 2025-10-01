package ac.rs.singidunum.springBootApp.Repository.Predmet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.Model.Predmet.Ishod;

@Repository
public interface IshodRepository extends CrudRepository<Ishod, Long> {

}
