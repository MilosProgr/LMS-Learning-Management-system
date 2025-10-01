package ac.rs.singidunum.springBootApp.Repository.Nastavnici;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.Model.Nastavnici.NastavnikNaRealizaciji;

@Repository
public interface NastavnikNaRealizaacijiRepository extends CrudRepository<NastavnikNaRealizaciji, Long> {
    boolean existsByNastavnik_Id(Long nastavnikId);
    Optional<NastavnikNaRealizaciji> findByNastavnik_Id(Long nastavnikId);
}
