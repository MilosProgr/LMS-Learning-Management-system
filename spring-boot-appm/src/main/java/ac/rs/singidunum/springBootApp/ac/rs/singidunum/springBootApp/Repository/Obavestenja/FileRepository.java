package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Obavestenja;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.File;

@Repository
public interface FileRepository extends CrudRepository<File, Long> {
    Optional<File> findByOpis(String opis);
    Optional<File> findByUrl(String url);
}
