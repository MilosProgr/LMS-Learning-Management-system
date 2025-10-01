package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Predmet;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.PohadjanjePredmeta;

@Repository
public interface PohadjanjePredmetaRepository extends CrudRepository<PohadjanjePredmeta, Long> {
	boolean existsByStudentNaGodiniIdAndPredmetId(Long studentNaGodiniId, Long predmetId);
    List<PohadjanjePredmeta> findByStudentNaGodiniId(Long studentNaGodiniId);
    Optional<PohadjanjePredmeta> findByStudentNaGodiniIdAndPredmetId(Long studentNaGodiniId, Long predmetId);

}
