package ac.rs.singidunum.springBootApp.Features.Predmeti.StudijskiProgram;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudijskiProgramRepository extends CrudRepository<StudijskiProgram, Long> {

}
