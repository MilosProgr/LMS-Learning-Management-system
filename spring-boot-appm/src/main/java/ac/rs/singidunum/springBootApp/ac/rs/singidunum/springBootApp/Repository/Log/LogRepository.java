package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Log;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Log.Log;

@Repository
public interface LogRepository extends CrudRepository<Log, Long> {

}
