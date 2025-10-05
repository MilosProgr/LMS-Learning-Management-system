package ac.rs.singidunum.springBootApp.Features.Adresa;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface AdresaRepository extends CrudRepository<Adresa, Long> {
	Optional<Adresa> findByUlicaAndBrojAndMestoId(String ulica, String broj, Long mestoId);
}
