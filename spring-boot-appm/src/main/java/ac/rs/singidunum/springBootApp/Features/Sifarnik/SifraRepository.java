package ac.rs.singidunum.springBootApp.Features.Sifarnik;

import org.springframework.data.repository.CrudRepository;

public interface SifraRepository extends CrudRepository<Sifra, Long> {
	
    boolean existsByTekstIgnoreCase(String tekst);
    boolean existsByTekstIgnoreCaseAndIdNot(String tekst, Long id);

}
