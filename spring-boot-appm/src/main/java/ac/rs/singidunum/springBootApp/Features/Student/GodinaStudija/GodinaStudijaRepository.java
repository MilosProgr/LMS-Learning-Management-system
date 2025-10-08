package ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GodinaStudijaRepository extends CrudRepository<GodinaStudija, Long> {
    boolean existsByGodina(Integer godina);
    boolean existsByGodinaAndIdNot(Integer godina, Long id);
}
