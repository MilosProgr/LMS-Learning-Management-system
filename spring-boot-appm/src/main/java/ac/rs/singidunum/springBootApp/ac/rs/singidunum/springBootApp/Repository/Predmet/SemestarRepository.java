package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Predmet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.Semestar;

@Repository
public interface SemestarRepository extends CrudRepository<Semestar, Long> {
	boolean existsByTip(String tip);

}
