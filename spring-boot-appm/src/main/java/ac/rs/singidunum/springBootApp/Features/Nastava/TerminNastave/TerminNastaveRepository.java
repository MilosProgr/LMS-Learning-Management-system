package ac.rs.singidunum.springBootApp.Features.Nastava.TerminNastave;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminNastaveRepository extends CrudRepository<TerminNastave, Long> {

}
