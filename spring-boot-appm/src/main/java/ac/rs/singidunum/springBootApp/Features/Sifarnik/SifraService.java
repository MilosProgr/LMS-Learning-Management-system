package ac.rs.singidunum.springBootApp.Features.Sifarnik;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class SifraService extends GenericCrudService<Map<String, Object>, Sifra, Long> {
	
	@Autowired private SifraRepository sifraRepository;

	protected SifraService(CrudRepository<Sifra, Long> repository, Mapper<Map<String, Object>, Sifra> mapper) {
		super(repository, mapper);
	}
	
	@Override
    public Map<String, Object> save(Sifra entity) {
        final String tekst = normalizeTekst(entity.getTekst());
        if (tekst.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Polje 'tekst' je obavezno.");
        }
        if (sifraRepository.existsByTekstIgnoreCase(tekst)) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Šifra sa tekstom '" + tekst + "' već postoji."
            );
        }
        entity.setTekst(tekst); 
        return super.save(entity);
    }

    @Override
    public Map<String, Object> update(Sifra entity) {
        if (entity.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID je obavezan za izmenu.");
        }
        final String tekst = normalizeTekst(entity.getTekst());
        if (tekst.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Polje 'tekst' je obavezno.");
        }
        if (sifraRepository.existsByTekstIgnoreCaseAndIdNot(tekst, entity.getId())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Šifra sa tekstom '" + tekst + "' već postoji (drugi zapis)."
            );
        }
        entity.setTekst(tekst);
        return super.update(entity);
    }

    private String normalizeTekst(String t) {
        return t == null ? "" : t.trim();
    }

	

	
	
	
}
