package ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface NastavnikRepository extends CrudRepository<Nastavnik, Long> {
	@Query("SELECT n FROM Nastavnik n LEFT JOIN Fakultet f ON n.id = f.dekan.id WHERE f.dekan IS NULL")
    List<Nastavnik> findNastavniciWithoutFakultet();
	
	@Query("SELECT n FROM Nastavnik n LEFT JOIN Univerzitet u ON n.id = u.rektor.id WHERE u.rektor IS NULL")
	List<Nastavnik> findNastavniciWithoutUniverzitet();
}
