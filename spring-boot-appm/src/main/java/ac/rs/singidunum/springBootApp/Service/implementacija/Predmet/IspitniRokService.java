package ac.rs.singidunum.springBootApp.Service.implementacija.Predmet;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.DTO.Predmet.IspitniRokDTO;
import ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti.ObavestenjeAktivnostDTO;
import ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti.ObavestenjeAktivnosti;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Mapper.implementacija.Predmet.IspitniRokMapper;
import ac.rs.singidunum.springBootApp.Model.Predmet.IspitniRok;
import ac.rs.singidunum.springBootApp.Repository.Predmet.IspitniRokRepository;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;
import lombok.experimental.PackagePrivate;

@Service
public class IspitniRokService extends GenericCrudService<IspitniRokDTO, IspitniRok, Long> {

	@Autowired
	private IspitniRokRepository ispitniRokRepository;
	
	@Autowired
	private IspitniRokMapper ispitniRokMapper;
	
	protected IspitniRokService(CrudRepository<IspitniRok, Long> repository, Mapper<IspitniRokDTO, IspitniRok> mapper) {
		super(repository, mapper);
	}
	
	@Override
    public IspitniRokDTO save(IspitniRok rok) {
        final String naziv = rok.getNaziv() != null ? rok.getNaziv().trim() : null;

        if (naziv != null && !naziv.isEmpty()) {
            boolean existsNaziv = (rok.getId() == null)
                ? ispitniRokRepository.existsByNazivIgnoreCase(naziv)
                : ispitniRokRepository.existsByNazivIgnoreCaseAndIdNot(naziv, rok.getId());

            if (existsNaziv) {
                throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ispitni rok sa nazivom \"" + naziv + "\" već postoji!"
                );
            }
        }

        // validacija datuma:
        // if (rok.getPocetak() == null || rok.getKraj() == null)
        //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Polja 'pocetak' i 'kraj' su obavezna.");
        // if (!rok.getKraj().isAfter(rok.getPocetak()))
        //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Kraj mora biti posle početka.");

        return super.save(rok);
    }

    @Override
    public IspitniRokDTO update(IspitniRok rok) {
        if (rok.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id ispitnog roka je null!");
        }

        // baci 404 ako ne postoji
        ispitniRokRepository.findById(rok.getId()).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Ispitni rok sa ID " + rok.getId() + " nije pronađen!")
        );

        final String naziv = rok.getNaziv() != null ? rok.getNaziv().trim() : null;
        if (naziv != null && !naziv.isEmpty()) {
            boolean existsTitle = ispitniRokRepository.existsByNazivIgnoreCaseAndIdNot(naziv, rok.getId());
            if (existsTitle) {
                throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ispitni rok sa nazivom \"" + naziv + "\" već postoji!"
                );
            }
        }

        // validacija datuma:
        // if (rok.getPocetak() == null || rok.getKraj() == null)
        //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Polja 'pocetak' i 'kraj' su obavezna.");
        // if (!rok.getKraj().isAfter(rok.getPocetak()))
        //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Kraj mora biti posle početka.");

        return super.update(rok);
    }
    
    public List<IspitniRokDTO> getRokoviKojiNisuProsli() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        List<IspitniRok> sacuvano = ispitniRokRepository.findByKrajIsNullOrKrajGreaterThanEqual(now);

        return sacuvano.stream().map(ispitniRokMapper::map).collect(Collectors.toList());
    }

}
