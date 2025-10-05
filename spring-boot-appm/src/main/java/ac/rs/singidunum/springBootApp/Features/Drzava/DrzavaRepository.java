package ac.rs.singidunum.springBootApp.Features.Drzava;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrzavaRepository extends CrudRepository<Drzava, Long> {
	Optional<Drzava> findByNazivIgnoreCase(String naziv);
}
