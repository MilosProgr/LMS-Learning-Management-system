package ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RealizacijaPredmetaRepository extends CrudRepository<RealizacijaPredmeta, Long> {

	@Query("SELECT r FROM RealizacijaPredmeta r JOIN r.predmet p JOIN r.terminNastave t JOIN t.tipNastave tip WHERE r.predmet.id = :predmetId")
    List<RealizacijaPredmeta> findRealizacijeWithTerminiAndTipNastave(@Param("predmetId") Long predmetId);
	
	boolean existsByPredmet_IdAndNastavnikNaRealizaciji_Id(Long predmetId, Long nnrId);

	boolean existsByPredmet_IdAndNastavnikNaRealizaciji_IdAndIdNot(Long predmetId, Long nnrId, Long excludeId);
}
