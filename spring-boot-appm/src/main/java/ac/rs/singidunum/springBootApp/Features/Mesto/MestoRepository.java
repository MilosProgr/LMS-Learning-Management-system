package ac.rs.singidunum.springBootApp.Features.Mesto;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MestoRepository extends CrudRepository<Mesto, Long> {
	Optional<Mesto> findByNazivAndDrzava_Id(String naziv, Long drzavaId);
}
