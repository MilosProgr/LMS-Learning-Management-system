package ac.rs.singidunum.springBootApp.Repository.Sifarnik;

import org.springframework.data.repository.CrudRepository;

import ac.rs.singidunum.springBootApp.Model.Sifarnik.Sifra;

public interface SifraRepository extends CrudRepository<Sifra, Long> {
	
    boolean existsByTekstIgnoreCase(String tekst);
    boolean existsByTekstIgnoreCaseAndIdNot(String tekst, Long id);

}
