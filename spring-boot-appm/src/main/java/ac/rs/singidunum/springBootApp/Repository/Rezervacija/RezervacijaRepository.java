package ac.rs.singidunum.springBootApp.Repository.Rezervacija;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.Model.Rezervacija.Rezervacija;

@Repository
public interface RezervacijaRepository extends CrudRepository<Rezervacija, Long> {
}
