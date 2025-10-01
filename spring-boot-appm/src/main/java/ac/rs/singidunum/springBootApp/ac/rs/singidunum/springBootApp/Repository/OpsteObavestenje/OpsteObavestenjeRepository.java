package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.OpsteObavestenje;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.OpsteObavestenje.OpsteObavestenje;


@Repository
public interface OpsteObavestenjeRepository extends CrudRepository<OpsteObavestenje, Long> {

    boolean existsByNaslovIgnoreCase(String naslov);
    boolean existsByNaslovIgnoreCaseAndIdNot(String naslov, Long id);

    boolean existsByTekstIgnoreCase(String tekst);
    boolean existsByTekstIgnoreCaseAndIdNot(String tekst, Long id);

}