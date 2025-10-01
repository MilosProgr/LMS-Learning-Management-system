package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Obavestenja;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.ObavestenjeAktivnosti.ObavestenjeAktivnosti;

@Repository
public interface ObavestenjeAktivnostiRepository extends CrudRepository<ObavestenjeAktivnosti, Long> {
	
    boolean existsByNaslovIgnoreCase(String naslov);
    boolean existsByNaslovIgnoreCaseAndIdNot(String naslov, Long id);

    boolean existsBySadrzajIgnoreCase(String sadrzaj);
    boolean existsBySadrzajIgnoreCaseAndIdNot(String sadrzaj, Long id);

}
