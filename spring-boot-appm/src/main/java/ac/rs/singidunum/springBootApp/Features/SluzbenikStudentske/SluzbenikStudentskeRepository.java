package ac.rs.singidunum.springBootApp.Features.SluzbenikStudentske;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SluzbenikStudentskeRepository extends CrudRepository<SluzbenikStudentske, Long> {

}
