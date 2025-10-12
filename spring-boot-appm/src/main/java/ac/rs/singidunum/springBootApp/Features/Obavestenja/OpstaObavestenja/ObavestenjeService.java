package ac.rs.singidunum.springBootApp.Features.Obavestenja.OpstaObavestenja;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.Features.Obavestenja.OpstaObavestenja.OpsteObavestenjeDTO.OpsteObavestenjeDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;




@Service
public class ObavestenjeService extends GenericCrudService<OpsteObavestenjeDTORecord, OpsteObavestenje, Long>{
	@Autowired
	private OpsteObavestenjeRepository opsteObavestenjeRepository;

	protected ObavestenjeService(CrudRepository<OpsteObavestenje, Long> repository,
			Mapper<OpsteObavestenjeDTORecord, OpsteObavestenje> mapper) {
		super(repository, mapper);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public OpsteObavestenjeDTORecord save(OpsteObavestenje o) {
        final String naslov = o.getNaslov() != null ? o.getNaslov().trim() : null;
        final String tekst  = o.getTekst()  != null ? o.getTekst().trim()  : null;

        if (naslov != null && !naslov.isEmpty()) {
            boolean existsTitle = (o.getId() == null)
                ? opsteObavestenjeRepository.existsByNaslovIgnoreCase(naslov)
                : opsteObavestenjeRepository.existsByNaslovIgnoreCaseAndIdNot(naslov, o.getId());

            if (existsTitle) {
                throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Obaveštenje sa naslovom \"" + naslov + "\" već postoji!"
                );
            }
        }

        if (tekst != null && !tekst.isEmpty()) {
            boolean existsText = (o.getId() == null)
                ? opsteObavestenjeRepository.existsByTekstIgnoreCase(tekst)
                : opsteObavestenjeRepository.existsByTekstIgnoreCaseAndIdNot(tekst, o.getId());

            if (existsText) {
                throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Obaveštenje sa istim tekstom već postoji!"
                );
            }
        }

        // Popuni datum/vreme ako oba nedostaju
        if (o.getDatum() == null && o.getVreme() == null) {
            o.setDatum(LocalDate.now());
            o.setVreme(LocalTime.now());
        }

        return super.save(o);
    }
	
	@Override
	public OpsteObavestenjeDTORecord update(OpsteObavestenje o) {
	    if (o.getId() == null) {
	        throw new ResponseStatusException(
	            HttpStatus.BAD_REQUEST, "ID je obavezan za izmenu obaveštenja."
	        );
	    }

	    OpsteObavestenje existing = opsteObavestenjeRepository.findById(o.getId())
	        .orElseThrow(() -> new ResponseStatusException(
	            HttpStatus.NOT_FOUND, "Obaveštenje sa ID " + o.getId() + " nije pronađeno!"
	        ));

	    final String naslov = o.getNaslov() != null ? o.getNaslov().trim() : null;
	    final String tekst  = o.getTekst()  != null ? o.getTekst().trim()  : null;

	    if (naslov != null && !naslov.isEmpty()) {
	        boolean existsTitle = opsteObavestenjeRepository
	            .existsByNaslovIgnoreCaseAndIdNot(naslov, o.getId());
	        if (existsTitle) {
	            throw new ResponseStatusException(
	                HttpStatus.CONFLICT,
	                "Obaveštenje sa naslovom \"" + naslov + "\" već postoji!"
	            );
	        }
	    }

	    if (tekst != null && !tekst.isEmpty()) {
	        boolean existsText = opsteObavestenjeRepository
	            .existsByTekstIgnoreCaseAndIdNot(tekst, o.getId());
	        if (existsText) {
	            throw new ResponseStatusException(
	                HttpStatus.CONFLICT,
	                "Obaveštenje sa istim tekstom već postoji!"
	            );
	        }
	    }

	    if (o.getDatum() == null)  o.setDatum(existing.getDatum());
	    if (o.getVreme() == null)  o.setVreme(existing.getVreme());
	    if (o.getDatum() == null && o.getVreme() == null) {
	        o.setDatum(LocalDate.now());
	        o.setVreme(LocalTime.now());
	    }

	    return super.update(o); 
	}

}
