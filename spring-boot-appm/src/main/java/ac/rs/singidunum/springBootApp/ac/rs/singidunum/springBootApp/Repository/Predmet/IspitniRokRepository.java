package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Predmet;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.IspitniRok;

@Repository
public interface IspitniRokRepository extends CrudRepository<IspitniRok, Long> {


    boolean existsByNazivIgnoreCase(String naziv);

    boolean existsByNazivIgnoreCaseAndIdNot(String naziv, Long id);

    Optional<IspitniRok> findByNazivIgnoreCase(String naziv);
    
    List<IspitniRok> findByKrajIsNullOrKrajGreaterThanEqual(LocalDateTime now);
}
