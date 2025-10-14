package ac.rs.singidunum.springBootApp.Features.Fakultet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.Features.Adresa.Adresa;
import ac.rs.singidunum.springBootApp.Features.Adresa.AdresaRepository;
import ac.rs.singidunum.springBootApp.Features.Adresa.AdresaService;
import ac.rs.singidunum.springBootApp.Features.Drzava.Drzava;
import ac.rs.singidunum.springBootApp.Features.Drzava.DrzavaRepository;
import ac.rs.singidunum.springBootApp.Features.Fakultet.FakultetDTO.FakultetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Mesto.Mesto;
import ac.rs.singidunum.springBootApp.Features.Mesto.MestoRepository;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.Nastavnik;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikRepository;
import ac.rs.singidunum.springBootApp.Features.Univerzitet.Univerzitet;
import ac.rs.singidunum.springBootApp.Features.Univerzitet.UniverzitetRepository;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class FakultetService extends GenericCrudService<FakultetDTORecord, Fakultet, Long> {

    @Autowired private AdresaService adresaService;
    @Autowired private FakultetRepository fakultetRepository;
    @Autowired private NastavnikRepository nastavnikRepository;
    @Autowired private UniverzitetRepository univerzitetRepository;
    @Autowired private AdresaRepository adresaRepository;
    @Autowired private DrzavaRepository drzavaRepository;
    @Autowired private MestoRepository mestoRepository;
    
    
    @Autowired private FakultetMapper fakultetMapper;
	
	protected FakultetService(CrudRepository<Fakultet, Long> repository, Mapper<FakultetDTORecord, Fakultet> mapper) {
		super(repository, mapper);
		// TODO Auto-generated constructor stub
	}
	
	
    @Transactional
    public FakultetDTORecord save(Fakultet f) {
        //Provera jedinstvenog naziva
        if (fakultetRepository.existsByNazivIgnoreCase(f.getNaziv().trim())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Fakultet sa nazivom '" + f.getNaziv() + "' već postoji.");
        }

        if (f.getAdresa() != null) {
            adresaService.save(f.getAdresa());
        }

        return super.save(f);
    }
    
    @Transactional
    public FakultetDTORecord update(Fakultet f) {
        String key = f.getNaziv().trim().replaceAll("\\s+", " ");
        if (f.getId() != null &&
            fakultetRepository.existsByNazivIgnoreCaseAndIdNot(key, f.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Fakultet sa nazivom '" + key + "' već postoji.");
        }
        f.setNaziv(key);
        if (f.getAdresa() != null) adresaService.save(f.getAdresa());
        return super.update(f);
    }
    
    /* ================== TRANSAKCIONI UPSERT ================== */

    @Transactional
    public FakultetDTORecord upSetFakultet(FakultetRequest req) {
        // --- VALIDACIJE (400) ---
        if (isBlank(req.getNaziv()))         throw badReq("Naziv je obavezan.");
        if (isBlank(req.getUlica()))         throw badReq("Ulica je obavezna.");
        if (isBlank(req.getBroj()))          throw badReq("Broj je obavezan.");
        if (req.getDekanId() == null)        throw badReq("Dekan je obavezan.");
        if (req.getUniverzitetId() == null)  throw badReq("Univerzitet je obavezan.");
        if (isBlank(req.getTelefon()))       throw badReq("Telefon je obavezan.");

        String keyNaziv = norm(req.getNaziv());
        String telefon = norm(req.getTelefon());

        // Univerzitet mora postojati
        Univerzitet univerzitet = univerzitetRepository.findById(req.getUniverzitetId())
            .orElseThrow(() -> new EntityNotFoundException("Univerzitet ne postoji: id=" + req.getUniverzitetId()));

        // Jedinstvenost naziva po univerzitetu
        if (req.getId() == null) {
            if (fakultetRepository.existsByNazivIgnoreCaseAndUniverzitet_Id(keyNaziv, univerzitet.getId())) {
                throw conflict("Fakultet sa nazivom '" + keyNaziv + "' već postoji.");
            }
        } else {
            if (fakultetRepository.existsByNazivIgnoreCaseAndUniverzitet_IdAndIdNot(keyNaziv, univerzitet.getId(), req.getId())) {
                throw conflict("Fakultet sa nazivom '" + keyNaziv + "' već postoji.");
            }
        }

        // Dekan mora postojati i ne sme biti dekan na drugom fakultetu
        Nastavnik dekan = nastavnikRepository.findById(req.getDekanId())
            .orElseThrow(() -> new EntityNotFoundException("Dekan (nastavnik) ne postoji: id=" + req.getDekanId()));

        boolean dekanTaken = (req.getId() == null)
                ? fakultetRepository.existsByDekan_Id(dekan.getId())
                : fakultetRepository.existsByDekan_IdAndIdNot(dekan.getId(), req.getId());
        if (dekanTaken) {
            throw conflict("Izabrani nastavnik je već postavljen kao dekan drugog fakulteta.");
        }

        // Država
        Drzava drzava = resolveDrzava(req.getDrzavaId(), req.getDrzavaNaziv());

        // Mesto
        Mesto mesto = resolveMesto(req.getMestoId(), req.getMestoNaziv(), drzava.getId());

        // Adresa
        Adresa adresa = adresaRepository
            .findByUlicaAndBrojAndMestoId(req.getUlica().trim(), req.getBroj().trim(), mesto.getId())
            .orElseGet(() -> {
                Adresa a = new Adresa();
                a.setUlica(req.getUlica().trim());
                a.setBroj(req.getBroj().trim());
                a.setMesto(mesto);
                return adresaRepository.save(a);
            });

        // Novi ili postojeći fakultet
        Fakultet f = (req.getId() == null)
            ? new Fakultet()
            : fakultetRepository.findById(req.getId())
                .orElseThrow(() -> new EntityNotFoundException("Fakultet ne postoji: id=" + req.getId()));

        f.setNaziv(keyNaziv);
        f.setOpis(norm(req.getOpis()));
        f.setTelefon(telefon);
        f.setAdresa(adresa);
        f.setDekan(dekan);
        f.setUniverzitet(univerzitet);

        Fakultet sacuvan = fakultetRepository.save(f);
        return fakultetMapper.map(sacuvan);
    }

    /* ================== Helperi ================== */

    private Drzava resolveDrzava(Long drzavaId, String drzavaNaziv) {
        if (drzavaId != null) {
            return drzavaRepository.findById(drzavaId)
                .orElseThrow(() -> new EntityNotFoundException("Država ne postoji: id=" + drzavaId));
        }
        if (isBlank(drzavaNaziv)) throw badReq("Potrebna je država (id ili naziv).");
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
        if (isBlank(mestoNaziv)) throw badReq("Za adresu je potrebno mesto (id ili naziv).");
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
    private ResponseStatusException badReq(String msg) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, msg);
    }
    private ResponseStatusException conflict(String msg) {
        return new ResponseStatusException(HttpStatus.CONFLICT, msg);
    }

}
