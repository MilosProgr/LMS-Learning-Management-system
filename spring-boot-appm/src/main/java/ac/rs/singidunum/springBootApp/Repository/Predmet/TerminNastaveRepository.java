package ac.rs.singidunum.springBootApp.Repository.Predmet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.Model.Predmet.TerminNastave;

@Repository
public interface TerminNastaveRepository extends CrudRepository<TerminNastave, Long> {

}
