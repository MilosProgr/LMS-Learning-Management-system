package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.SluzbenikStudentske;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.SluzbenikStudentske.SluzbenikStudentske;


@Repository
public interface SluzbenikStudentskeRepository extends CrudRepository<SluzbenikStudentske, Long> {

}
