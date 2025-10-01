package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Adresa;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Adresa.Drzava;

@Repository
public interface DrzavaRepository extends CrudRepository<Drzava, Long> {
	Optional<Drzava> findByNazivIgnoreCase(String naziv);
}
