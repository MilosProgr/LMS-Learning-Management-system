package ac.rs.singidunum.springBootApp.Features.Predmeti.PrijavljeniIspit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrijavljeniIspitRepository extends CrudRepository<PrijavljeniIspit, Long>{
	boolean existsByStudentNaGodini_IdAndPredmet_IdAndIspitniRok_Id(Long sngId, Long predmetId, Long rokId);
}
