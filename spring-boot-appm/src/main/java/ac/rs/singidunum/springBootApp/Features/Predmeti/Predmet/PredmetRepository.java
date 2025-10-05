package ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PredmetRepository extends CrudRepository<Predmet, Long> {
    List<Predmet> findByStudijskiProgrami_Id(Long programId);
    
// Provera da li VEĆ postoji veza u join tabeli
//    @Query("SELECT COUNT(p) > 0 FROM Predmet p JOIN p.studijskiProgrami sp " +
//           "WHERE p.id = :predmetId AND sp.id = :programId")
//    boolean existsByIdAndProgram(@Param("predmetId") Long predmetId,
//                                 @Param("programId") Long programId);

    boolean existsByNazivIgnoreCase(String naziv);
    boolean existsBySifra_Id(Long sifraId);

}
