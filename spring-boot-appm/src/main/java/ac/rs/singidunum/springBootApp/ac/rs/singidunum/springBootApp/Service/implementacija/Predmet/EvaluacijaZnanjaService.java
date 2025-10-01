package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.Predmet;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet.CreateEvaluacijaZnanjaRequest;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet.EvaluacijaZnanjaDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.ObavestenjeAktivnosti.ObavestenjeAktivnosti;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.EvaluacijaZnanja;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.File;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.Ishod;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.Predmet;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.PrijavljeniIspit;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.RealizacijaPredmeta;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.TipEvaluacije;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Obavestenja.FileRepository;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Predmet.IshodRepository;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Predmet.PredmetRepository;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Predmet.PrijavljeniIspitRepository;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Predmet.RealizacijaPredmetaRepository;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Predmet.TipEvaluacijeRepository;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Service.implementacija.ObavestenjaAktivnost.ObavestenjaAktivnostiService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class EvaluacijaZnanjaService extends GenericCrudService<EvaluacijaZnanjaDTO, EvaluacijaZnanja, Long> {

	@Autowired private IshodService ishodService;
	@Autowired private ObavestenjaAktivnostiService obavestenjaAktivnostiService;
    @Autowired private TipEvaluacijeRepository tipEvaluacijeRepository;
    @Autowired private FileRepository fileReporepository;
    @Autowired private PrijavljeniIspitRepository prijavljeniIspitRepository;
    @Autowired private RealizacijaPredmetaRepository realizacijaPredmetaRepository;
    @Autowired private IshodRepository ishodRepository;
    @Autowired private PredmetRepository predmetRepository;
    
	protected EvaluacijaZnanjaService(CrudRepository<EvaluacijaZnanja, Long> repository,
			Mapper<EvaluacijaZnanjaDTO, EvaluacijaZnanja> mapper) {
		super(repository, mapper);
	}
	
	public EvaluacijaZnanjaDTO save(EvaluacijaZnanja e) {
		e.setVremePocetka(LocalDateTime.now());
		e.setVremeZavrsetka(LocalDateTime.now().plusHours(2));
		
		if (e.getPrijavljeniIspit() != null) {
		    e.getPrijavljeniIspit().addEvaluacija(e);
		}

//		if(e.getIshod() != null) {
//			ishodService.save(e.getIshod());
//		}
		return super.save(e);
	}
	
	@Transactional
    public EvaluacijaZnanjaDTO kreiraj(CreateEvaluacijaZnanjaRequest req) {
        // --- VALIDACIJE UNOSA ---
        if (req.getPrijavljeniIspitId() == null)
            throw new IllegalArgumentException("prijavljeniIspitId je obavezan.");

        if (req.getTipEvaluacijeId() == null)
            throw new IllegalArgumentException("tipEvaluacijeId je obavezan.");

        if (req.getRealizacijaPredmetaId() == null)
            throw new IllegalArgumentException("realizacijaPredmetaId je obavezan.");

        if (req.getOstvareniBodovi() != null && req.getOstvareniBodovi() < 0)
            throw new IllegalArgumentException("Ostvareni bodovi ne mogu biti negativani.");

        // --- UČITAJ RELACIJE ---
        PrijavljeniIspit prijavljeniIspit = prijavljeniIspitRepository.findById(req.getPrijavljeniIspitId())
                .orElseThrow(() -> new IllegalArgumentException("Prijavljeni ispit ne postoji: id=" + req.getPrijavljeniIspitId()));

        TipEvaluacije tip = tipEvaluacijeRepository.findById(req.getTipEvaluacijeId())
                .orElseThrow(() -> new IllegalArgumentException("Tip evaluacije ne postoji: id=" + req.getTipEvaluacijeId()));

        RealizacijaPredmeta realizacija = realizacijaPredmetaRepository.findById(req.getRealizacijaPredmetaId())
                .orElseThrow(() -> new IllegalArgumentException("Realizacija predmeta ne postoji: id=" + req.getRealizacijaPredmetaId()));

     // ===== FILE (instrument) =====
        File instrument = null;

        if (req.getInstrumentFileId() != null) {
            instrument = fileReporepository.findById(req.getInstrumentFileId()).orElse(null);
            if (instrument == null) {
                // Ako ID ne postoji, kreiramo NOVI iz unetih vrednosti
                if (isBlank(req.getInstrumentOpis()) || isBlank(req.getInstrumentUrl())) {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Instrument ne postoji; za kreiranje prosledite Opis i Url.");
                    
                }
                File novi = new File();
                novi.setOpis(req.getInstrumentOpis().trim());
                novi.setUrl(req.getInstrumentUrl().trim());
                instrument = fileReporepository.save(novi);
            }
        } else if (!isBlank(req.getInstrumentOpis()) || !isBlank(req.getInstrumentUrl())) {
            // Ako nije poslat ID, a korisnik je uneo podatke — kreiraj novi
            if (isBlank(req.getInstrumentOpis()) || isBlank(req.getInstrumentUrl())) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT, "Za kreiranje instrumenta potrebni su i Opis i Url.");
            }
            File novi = new File();
            novi.setOpis(req.getInstrumentOpis().trim());
            novi.setUrl(req.getInstrumentUrl().trim());
            instrument = fileReporepository.save(novi);
        }

     // ===== ISHOD =====
        Ishod ishod = null;

        if (req.getIshodId() != null) {
            ishod = ishodRepository.findById(req.getIshodId()).orElse(null);
            if (ishod == null) {
                // ako nema tog ID-ja: kreiramo NOVI iz unetih vrednosti
                if (isBlank(req.getIshodOpis())) {
                    throw new IllegalArgumentException("Ishod ne postoji; za kreiranje prosledite ishodOpis (i opciono ishodPredmetId).");
                    
                }
                Predmet predmetZaIshod = null;
                if (req.getIshodPredmetId() != null) {
                    predmetZaIshod = predmetRepository.findById(req.getIshodPredmetId())
                        .orElseThrow(() -> new EntityNotFoundException("Predmet za ishod ne postoji: id=" + req.getIshodPredmetId()));
                } else {
                    // podrazumevano vežem ishod na predmet iz realizacije
                    predmetZaIshod = realizacija.getPredmet();
                    if (predmetZaIshod == null) {
                        throw new IllegalStateException("Realizacija nema predmet; nije moguće kreirati ishod bez predmeta.");
                    }
                }
                Ishod novi = new Ishod();
                novi.setOpis(req.getIshodOpis().trim());
                novi.setPredmet(predmetZaIshod);
                novi.setPolozeno(Boolean.TRUE.equals(req.getIshodPolozeno()));
                ishod = ishodRepository.save(novi);
            }
        } else if (!isBlank(req.getIshodOpis())) {
            // bez ID — ako je korisnik uneo opis, kreiraj novi ishod
            Predmet predmetZaIshod = null;
            if (req.getIshodPredmetId() != null) {
                predmetZaIshod = predmetRepository.findById(req.getIshodPredmetId())
                    .orElseThrow(() -> new EntityNotFoundException("Predmet za ishod ne postoji: id=" + req.getIshodPredmetId()));
            } else {
                predmetZaIshod = realizacija.getPredmet();
                if (predmetZaIshod == null) {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Realizacija nema predmet; nije moguće kreirati ishod bez predmeta.");
                }
            }
            Ishod novi = new Ishod();
            novi.setOpis(req.getIshodOpis().trim());
            novi.setPredmet(predmetZaIshod);
            novi.setPolozeno(Boolean.TRUE.equals(req.getIshodPolozeno()));
            ishod = ishodRepository.save(novi);
        }

        // predmet iz prijave mora odgovarati predmetu realizacije
        if (prijavljeniIspit.getPredmet() == null ||
            realizacija.getPredmet() == null ||
            !prijavljeniIspit.getPredmet().getId().equals(realizacija.getPredmet().getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Predmet iz prijave ne odgovara predmetu realizacije.");
        }


        EvaluacijaZnanja e = new EvaluacijaZnanja();
        e.setVremePocetka(LocalDateTime.now());
        e.setVremeZavrsetka(LocalDateTime.now().plusHours(2)); // ili iz req-a
        e.setOstvareniBodovi(req.getOstvareniBodovi() != null ? req.getOstvareniBodovi() : 0L);

        e.setTipEvaluacije(tip);
        e.setInstrumentEvaluacije(instrument);
        e.setRealizacijaPredmeta(realizacija);
        e.setIshod(ishod);
        e.setPrijavljeniIspit(prijavljeniIspit);


        // Ako je veza bidirekciona (u PrijavljeniIspit postoji polje evaluacijaZnanja): dodajem samo u prijavljeni ispit evaluaciju
        prijavljeniIspit.addEvaluacija(e);
        
        return super.save(e);
    }
	
	
	//pomocna metoda
	private boolean isBlank(String s) {
	    return s == null || s.trim().isEmpty();
	}

}
