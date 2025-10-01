package ac.rs.singidunum.springBootApp.Service.implementacija.Nastavnici;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.DTO.Adresa.AdresaDTO;
import ac.rs.singidunum.springBootApp.DTO.Nastavnici.FakultetDTO;
import ac.rs.singidunum.springBootApp.DTO.Nastavnici.UniverzitetDTO;
import ac.rs.singidunum.springBootApp.DTO.Nastavnici.UniverzitetRequest;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Mapper.implementacija.Adresa.AdresaMapper;
import ac.rs.singidunum.springBootApp.Mapper.implementacija.Nastavnici.UniverzitetMapper;
import ac.rs.singidunum.springBootApp.Model.Adresa.Adresa;
import ac.rs.singidunum.springBootApp.Model.Adresa.Drzava;
import ac.rs.singidunum.springBootApp.Model.Adresa.Mesto;
import ac.rs.singidunum.springBootApp.Model.Nastavnici.Fakultet;
import ac.rs.singidunum.springBootApp.Model.Nastavnici.Nastavnik;
import ac.rs.singidunum.springBootApp.Model.Nastavnici.Univerzitet;
import ac.rs.singidunum.springBootApp.Repository.Adresa.AdresaRepository;
import ac.rs.singidunum.springBootApp.Repository.Adresa.DrzavaRepository;
import ac.rs.singidunum.springBootApp.Repository.Adresa.MestoRepository;
import ac.rs.singidunum.springBootApp.Repository.Nastavnici.FakultetRepository;
import ac.rs.singidunum.springBootApp.Repository.Nastavnici.NastavnikRepository;
import ac.rs.singidunum.springBootApp.Repository.Nastavnici.UniverzitetRepository;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;
import ac.rs.singidunum.springBootApp.Service.implementacija.Adresa.AdresaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UniverzitetService extends GenericCrudService<UniverzitetDTO, Univerzitet, Long> {

	
	@Autowired private AdresaService adresaService;
    @Autowired private MestoRepository mestoRepository;
	@Autowired private AdresaRepository adresaRepository;
	@Autowired private UniverzitetRepository univerzitetRepository;
	@Autowired private NastavnikRepository nastavnikRepository;
	@Autowired private DrzavaRepository drzavaRepository;
	@Autowired private FakultetRepository fakultetRepository;
	
	@Autowired private UniverzitetMapper univerzitetMapper;
	
	
	protected UniverzitetService(CrudRepository<Univerzitet, Long> repository,
			Mapper<UniverzitetDTO, Univerzitet> mapper) {
		super(repository, mapper);
		// TODO Auto-generated constructor stub
	}
	
    @Transactional
    public UniverzitetDTO save(Univerzitet u) {
        //Provera jedinstvenog naziva
        if (univerzitetRepository.existsByNazivIgnoreCase(u.getNaziv().trim())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Univerzitet sa nazivom '" + u.getNaziv() + "' već postoji.");
        }

        if (u.getAdresa() != null) {
            adresaService.save(u.getAdresa());
        }

        return super.save(u);
    }
    
    @Transactional
    public UniverzitetDTO update(Univerzitet u) {
        String key = u.getNaziv().trim().replaceAll("\\s+", " ");
        if (u.getId() != null &&
            univerzitetRepository.existsByNazivIgnoreCaseAndIdNot(key, u.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Univerzitet sa nazivom '" + key + "' već postoji.");
        }
        u.setNaziv(key);
        if (u.getAdresa() != null) adresaService.save(u.getAdresa());
        return super.update(u);
    }
    
    
    /* ------------------ TRANSAKCIONO DODAVANJE SVEGA ------------------ */

    @Transactional
    public UniverzitetDTO upSetUniverzitet(UniverzitetRequest req) {
        // --- VALIDACIJE ---

    	if (isBlank(req.getNaziv())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Naziv je obavezan.");
    	if (req.getDatumOsnivanja() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datum osnivanja je obavezan.");
    	if (isBlank(req.getUlica())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ulica je obavezna.");
    	if (isBlank(req.getBroj())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Broj je obavezan.");
    	if (req.getRektorId() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rektor je obavezan.");

        // Jedinstvenost naziva
        String keyNaziv = norm(req.getNaziv());
        if (req.getId() == null) {
            if (univerzitetRepository.existsByNazivIgnoreCase(keyNaziv)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Univerzitet sa nazivom '" + keyNaziv + "' već postoji.");
            }
        } else {
            if (univerzitetRepository.existsByNazivIgnoreCaseAndIdNot(keyNaziv, req.getId())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Univerzitet sa nazivom '" + keyNaziv + "' već postoji.");
            }
        }

        // Rektor mora postojati i ne sme biti zauzet na drugom univerzitetu
        Nastavnik rektor = nastavnikRepository.findById(req.getRektorId())
            .orElseThrow(() -> new EntityNotFoundException("Rektor (nastavnik) ne postoji: id=" + req.getRektorId()));
        //stavljeno kao dodatna siigurnost i ako imam filtriranje u selekt poljima
        boolean rektorTaken = (req.getId() == null)
                ? univerzitetRepository.existsByRektorId(rektor.getId())
                : univerzitetRepository.existsByRektorIdAndIdNot(rektor.getId(), req.getId());
        if (rektorTaken) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Izabrani nastavnik je već postavljen kao rektor drugog univerziteta.");
        }

        // Država (po id ili naziv, ili kreiraj po nazivu)
        Drzava drzava = resolveDrzava(req.getDrzavaId(), req.getDrzavaNaziv());

        // Mesto (po id ili naziv u okviru te države, ili kreiraj)
        Mesto mesto = resolveMesto(req.getMestoId(), req.getMestoNaziv(), drzava.getId());

        // Adresa (nađi ili kreiraj)
        Adresa adresa = adresaRepository
            .findByUlicaAndBrojAndMestoId(req.getUlica().trim(), req.getBroj().trim(), mesto.getId())
            .orElseGet(() -> {
                Adresa a = new Adresa();
                a.setUlica(req.getUlica().trim());
                a.setBroj(req.getBroj().trim());
                a.setMesto(mesto);
                return adresaRepository.save(a);
            });

        // Novi ili postojeći univerzitet
        Univerzitet u = (req.getId() == null)
            ? new Univerzitet()
            : univerzitetRepository.findById(req.getId())
                .orElseThrow(() -> new EntityNotFoundException("Univerzitet ne postoji: id=" + req.getId()));

        u.setNaziv(keyNaziv);
        u.setOpis(norm(req.getOpis()));
        u.setDatumOsnivanja(req.getDatumOsnivanja());
        u.setAdresa(adresa);
        u.setRektor(rektor);

        Univerzitet sacuvan = univerzitetRepository.save(u);
        return univerzitetMapper.map(sacuvan); 
    }
    
    //brisanje
    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!univerzitetRepository.existsById(id)) return false;

        long cnt = fakultetRepository.countByUniverzitetId(id);
        if (cnt > 0) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Nije moguće obrisati univerzitet: postoje povezani fakulteti."
            );
        }

        univerzitetRepository.deleteById(id);
        return true;
    }

    /* ------------------ Helperi ------------------ */

    private Drzava resolveDrzava(Long drzavaId, String drzavaNaziv) {
        if (drzavaId != null) {
            return drzavaRepository.findById(drzavaId)
                .orElseThrow(() -> new EntityNotFoundException("Država ne postoji: id=" + drzavaId));
        }
        if (isBlank(drzavaNaziv)) {
            throw new IllegalArgumentException("Potrebna je država (id ili naziv).");
        }
        String key = drzavaNaziv.trim();
        return drzavaRepository.findByNazivIgnoreCase(key)
            .orElseGet(() -> {
                Drzava d = new Drzava();
                d.setNaziv(key);
                return drzavaRepository.save(d);
            });
    }

    private Mesto resolveMesto(Long mestoId, String mestoNaziv, Long drzavaId) {
        if (mestoId != null) {
            return mestoRepository.findById(mestoId)
                .orElseThrow(() -> new EntityNotFoundException("Mesto ne postoji: id=" + mestoId));
        }
        if (isBlank(mestoNaziv)) {
            throw new IllegalArgumentException("Za adresu je potrebno mesto (id ili naziv).");
        }
        String key = mestoNaziv.trim();
        return mestoRepository.findByNazivAndDrzava_Id(key, drzavaId)
            .orElseGet(() -> {
                Mesto m = new Mesto();
                m.setNaziv(key);
                m.setDrzava(drzavaRepository.findById(drzavaId)
                    .orElseThrow(() -> new EntityNotFoundException("Država ne postoji: id=" + drzavaId)));
                return mestoRepository.save(m);
            });
    }

    private String norm(String s) {
        if (s == null) return null;
        return s.trim().replaceAll("\\s+", " ");
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
