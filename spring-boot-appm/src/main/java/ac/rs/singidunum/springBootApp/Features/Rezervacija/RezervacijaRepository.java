package ac.rs.singidunum.springBootApp.Features.Rezervacija;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RezervacijaRepository extends CrudRepository<Rezervacija, Long> {
}
