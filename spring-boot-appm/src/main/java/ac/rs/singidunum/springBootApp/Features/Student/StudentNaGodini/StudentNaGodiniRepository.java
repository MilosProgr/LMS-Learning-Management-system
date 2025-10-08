package ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentNaGodiniRepository extends CrudRepository<StudentNaGodini, Long> {
	
	List<StudentNaGodini> findByBrojIndeksaStartingWith(String prefix);
	
	boolean existsByStudentIdAndStudijskiProgramId(Long studentId, Long programId);
	
}
