package ac.rs.singidunum.springBootApp.Repository.Student;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.Model.Student.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query("select s from Student s where s.id = :id")
    Optional<Student> findById(@Param("id") Long id);
    
    boolean existsByJmbg(String jmbg);
}
