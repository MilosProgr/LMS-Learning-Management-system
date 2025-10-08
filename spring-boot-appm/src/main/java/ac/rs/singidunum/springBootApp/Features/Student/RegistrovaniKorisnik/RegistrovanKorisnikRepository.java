package ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistrovanKorisnikRepository extends CrudRepository<RegistrovaniKorisnik, Long> {
	
    public Optional<RegistrovaniKorisnik> findKorisnikByKorisnickoIme(String korisnickoIme);

    public Optional<RegistrovaniKorisnik> findById(Long id);
    
    public Optional<RegistrovaniKorisnik> findByEmail(String email);

    
    
}
