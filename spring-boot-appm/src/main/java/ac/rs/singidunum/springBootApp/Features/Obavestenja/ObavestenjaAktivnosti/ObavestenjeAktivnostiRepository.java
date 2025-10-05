package ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObavestenjeAktivnostiRepository extends CrudRepository<ObavestenjeAktivnosti, Long> {
	
    boolean existsByNaslovIgnoreCase(String naslov);
    boolean existsByNaslovIgnoreCaseAndIdNot(String naslov, Long id);

    boolean existsBySadrzajIgnoreCase(String sadrzaj);
    boolean existsBySadrzajIgnoreCaseAndIdNot(String sadrzaj, Long id);

}
