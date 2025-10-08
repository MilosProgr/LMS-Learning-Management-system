package ac.rs.singidunum.springBootApp.Features.Predmeti.IspitniRok;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IspitniRokRepository extends CrudRepository<IspitniRok, Long> {


    boolean existsByNazivIgnoreCase(String naziv);

    boolean existsByNazivIgnoreCaseAndIdNot(String naziv, Long id);

    Optional<IspitniRok> findByNazivIgnoreCase(String naziv);
    
    List<IspitniRok> findByKrajIsNullOrKrajGreaterThanEqual(LocalDateTime now);
}
