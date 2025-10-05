package ac.rs.singidunum.springBootApp.Features.Obavestenja.OpstaObavestenja;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OpsteObavestenjeRepository extends CrudRepository<OpsteObavestenje, Long> {

    boolean existsByNaslovIgnoreCase(String naslov);
    boolean existsByNaslovIgnoreCaseAndIdNot(String naslov, Long id);

    boolean existsByTekstIgnoreCase(String tekst);
    boolean existsByTekstIgnoreCaseAndIdNot(String tekst, Long id);

}