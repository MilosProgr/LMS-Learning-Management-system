package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Adresa;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Adresa.Mesto;

@Repository
public interface MestoRepository extends CrudRepository<Mesto, Long> {
	Optional<Mesto> findByNazivAndDrzava_Id(String naziv, Long drzavaId);
}
