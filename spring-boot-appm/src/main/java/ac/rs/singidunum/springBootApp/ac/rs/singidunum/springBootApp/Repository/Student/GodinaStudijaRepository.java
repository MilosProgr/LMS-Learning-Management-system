package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.GodinaStudija;

@Repository
public interface GodinaStudijaRepository extends CrudRepository<GodinaStudija, Long> {
    boolean existsByGodina(Integer godina);
    boolean existsByGodinaAndIdNot(Integer godina, Long id);
}
