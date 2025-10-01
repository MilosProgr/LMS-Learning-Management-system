package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Rezervacija;

import org.springframework.data.repository.CrudRepository;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Rezervacija.Rezervacija;
import org.springframework.stereotype.Repository;

@Repository
public interface RezervacijaRepository extends CrudRepository<Rezervacija, Long> {
}
