package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Predmet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.PrijavljeniIspit;

@Repository
public interface PrijavljeniIspitRepository extends CrudRepository<PrijavljeniIspit, Long>{
	boolean existsByStudentNaGodini_IdAndPredmet_IdAndIspitniRok_Id(Long sngId, Long predmetId, Long rokId);
}
