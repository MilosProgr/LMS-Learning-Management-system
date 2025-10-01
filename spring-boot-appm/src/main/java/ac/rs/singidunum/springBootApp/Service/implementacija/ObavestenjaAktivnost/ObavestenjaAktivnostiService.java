package ac.rs.singidunum.springBootApp.Service.implementacija.ObavestenjaAktivnost;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.DTO.ObavestenjaAktivnosti.ObavestenjeAktivnostDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.ObavestenjeAktivnosti.ObavestenjeAktivnosti;
import ac.rs.singidunum.springBootApp.Repository.Obavestenja.ObavestenjeAktivnostiRepository;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;

@Service
public class ObavestenjaAktivnostiService extends GenericCrudService<ObavestenjeAktivnostDTO, ObavestenjeAktivnosti, Long>{
	
	@Autowired
	private ObavestenjeAktivnostiRepository obaveseAktivnostiRepository;

	protected ObavestenjaAktivnostiService(CrudRepository<ObavestenjeAktivnosti, Long> repository,
			Mapper<ObavestenjeAktivnostDTO, ObavestenjeAktivnosti> mapper) {
		super(repository, mapper);
	}
	
	@Override
    public ObavestenjeAktivnostDTO save(ObavestenjeAktivnosti o) {

        final String naslov = o.getNaslov() != null ? o.getNaslov().trim() : null;
        final String sadrzaj  = o.getSadrzaj()  != null ? o.getSadrzaj().trim()  : null;


        if (naslov != null && !naslov.isEmpty()) {
            boolean existsTitle = (o.getId() == null)
                ? obaveseAktivnostiRepository.existsByNaslovIgnoreCase(naslov)
                : obaveseAktivnostiRepository.existsByNaslovIgnoreCaseAndIdNot(naslov, o.getId());

            if (existsTitle) {
                throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Obaveštenje aktivnosti sa naslovom \"" + naslov + "\" već postoji!"
                );
            }
        }

        if (sadrzaj != null && !sadrzaj.isEmpty()) {
            boolean existsText = (o.getId() == null)
                ? obaveseAktivnostiRepository.existsBySadrzajIgnoreCase(sadrzaj)
                : obaveseAktivnostiRepository.existsBySadrzajIgnoreCaseAndIdNot(sadrzaj, o.getId());

            if (existsText) {
                throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Obaveštenje aktivnosti sa istim sadrzajem već postoji!"
                );
            }
        }

        // Popuni ako nedostaje
        if (o.getVremePostavljanja() == null) {
            o.setVremePostavljanja(LocalDateTime.now());
        }

        return super.save(o);
    }
	
	@Override
	public ObavestenjeAktivnostDTO update(ObavestenjeAktivnosti o) {
	    if (o.getId() == null) {
	        throw new ResponseStatusException(
	            HttpStatus.BAD_REQUEST, "ID je obavezan za izmenu obaveštenja aktivnosti."
	        );
	    }
	    ObavestenjeAktivnosti existing = obaveseAktivnostiRepository.findById(o.getId())
	        .orElseThrow(() -> new ResponseStatusException(
	            HttpStatus.NOT_FOUND, "Obaveštenje aktivnosti sa ID " + o.getId() + " nije pronađeno!"
	        ));

	    final String naslov  = o.getNaslov()  != null ? o.getNaslov().trim()  : null;
	    final String sadrzaj = o.getSadrzaj() != null ? o.getSadrzaj().trim() : null;

	    if (naslov != null && !naslov.isEmpty()) {
	        boolean existsTitle = obaveseAktivnostiRepository
	            .existsByNaslovIgnoreCaseAndIdNot(naslov, o.getId());
	        if (existsTitle) {
	            throw new ResponseStatusException(
	                HttpStatus.CONFLICT,
	                "Obaveštenje aktivnosti sa naslovom \"" + naslov + "\" već postoji!"
	            );
	        }
	    }

	    if (sadrzaj != null && !sadrzaj.isEmpty()) {
	        boolean existsText = obaveseAktivnostiRepository
	            .existsBySadrzajIgnoreCaseAndIdNot(sadrzaj, o.getId());
	        if (existsText) {
	            throw new ResponseStatusException(
	                HttpStatus.CONFLICT,
	                "Obaveštenje aktivnosti sa istim sadržajem već postoji!"
	            );
	        }
	    }

	    if (o.getVremePostavljanja() == null) {
	        o.setVremePostavljanja(
	            existing.getVremePostavljanja() != null
	                ? existing.getVremePostavljanja()
	                : LocalDateTime.now()
	        );
	    }
	    return super.update(o);
	}

	public void oznaciKaoProcitano(Long id) {
        Optional<ObavestenjeAktivnosti> optional = obaveseAktivnostiRepository.findById(id);
        if(optional.isPresent()) {
            ObavestenjeAktivnosti obavestenje = optional.get();
            obavestenje.setProcitano(true);
            obaveseAktivnostiRepository.save(obavestenje);
        } else {
            throw new RuntimeException("Obaveštenje aktivnosti sa ID " + id + " nije pronađeno!");
        }
    }

}
