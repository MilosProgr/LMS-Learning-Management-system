package ac.rs.singidunum.springBootApp.Features.Obavestenja.File;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<File, Long> {
    Optional<File> findByOpis(String opis);
    Optional<File> findByUrl(String url);
}
