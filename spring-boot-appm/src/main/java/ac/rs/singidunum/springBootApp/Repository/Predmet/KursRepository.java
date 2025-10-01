package ac.rs.singidunum.springBootApp.Repository.Predmet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.Model.Predmet.Kurs;

@Repository
public interface KursRepository extends CrudRepository<Kurs, Long> {

}
