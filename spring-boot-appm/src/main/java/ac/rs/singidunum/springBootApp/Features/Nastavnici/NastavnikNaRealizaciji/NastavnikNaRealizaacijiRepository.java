package ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NastavnikNaRealizaacijiRepository extends CrudRepository<NastavnikNaRealizaciji, Long> {
    boolean existsByNastavnik_Id(Long nastavnikId);
    Optional<NastavnikNaRealizaciji> findByNastavnik_Id(Long nastavnikId);
}
