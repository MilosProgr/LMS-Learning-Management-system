package ac.rs.singidunum.springBootApp.Features.Predmeti.Semestar;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SemestarRepository extends CrudRepository<Semestar, Long> {
	boolean existsByTip(String tip);

}
