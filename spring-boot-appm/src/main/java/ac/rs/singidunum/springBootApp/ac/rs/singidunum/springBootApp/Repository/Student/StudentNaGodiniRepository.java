package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Student;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.StudentNaGodini;

@Repository
public interface StudentNaGodiniRepository extends CrudRepository<StudentNaGodini, Long> {
	
	List<StudentNaGodini> findByBrojIndeksaStartingWith(String prefix);
	
	boolean existsByStudentIdAndStudijskiProgramId(Long studentId, Long programId);
	
}
