package ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Predmet.Semestar;
import ac.rs.singidunum.springBootApp.Repository.Predmet.SemestarRepository;
import ac.rs.singidunum.springBootApp.Service.deklaracija.GenericCrudService;
import jakarta.transaction.Transactional;

@Service
public class RealizacijaPredmetaService extends GenericCrudService<RealizacijaPredmetaDTO, RealizacijaPredmeta, Long> {

	@Autowired
    private RealizacijaPredmetaRepository realizacijaPredmetaRepository;
	
	@Autowired
	private SemestarRepository semestarRepository;
	
	
	protected RealizacijaPredmetaService(CrudRepository<RealizacijaPredmeta, Long> repository,
			Mapper<RealizacijaPredmetaDTO, RealizacijaPredmeta> mapper) {
		super(repository, mapper);
	}
	
	public List<RealizacijaPredmeta> getRealizacijeWithTerminiAndTipNastave(Long predmetId) {
        return realizacijaPredmetaRepository.findRealizacijeWithTerminiAndTipNastave(predmetId);
    }
	
	@Override
	@Transactional
	public RealizacijaPredmetaDTO save(RealizacijaPredmeta r) {
	    if (r == null) {
	    	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Telo zahteva je prazno.");
	    }
	    if (r.getPredmet() == null || r.getPredmet().getId() == null) {
	    	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Predmet nije prosleđen ili nema ID.");
	    }
	    	
	    if (r.getNastavnikNaRealizaciji() == null || r.getNastavnikNaRealizaciji().getId() == null) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nedostaje nastavnikNaRealizaciji.id.");
	    }
	    
	    Long predmetId = r.getPredmet().getId();
	    Long nnrId     = r.getNastavnikNaRealizaciji().getId();

	    if (realizacijaPredmetaRepository.existsByPredmet_IdAndNastavnikNaRealizaciji_Id(predmetId, nnrId)) {
	        throw new ResponseStatusException(HttpStatus.CONFLICT, ("Nastavnku je vec dodeljen taj predmet."));
	    }
	    dodajSemestre(r);
	    return super.save(r);
	}

	@Override
	@Transactional
	public RealizacijaPredmetaDTO update(RealizacijaPredmeta r) {
	    if (r.getId() == null)
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID je obavezan za izmenu realizacije predmeta.");

	    realizacijaPredmetaRepository.findById(r.getId())
	        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
	            "Realizacija predmeta sa ID " + r.getId() + " nije pronađena!"));

	    if (r.getPredmet() == null || r.getPredmet().getId() == null) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Predmet nije prosleđen ili nema ID.");
	    }
	    if (r.getNastavnikNaRealizaciji() == null || r.getNastavnikNaRealizaciji().getId() == null) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nedostaje nastavnikNaRealizaciji.id.");
	    }

	    Long predmetId = r.getPredmet().getId();
	    Long nnrId     = r.getNastavnikNaRealizaciji().getId();

	    if (realizacijaPredmetaRepository
	            .existsByPredmet_IdAndNastavnikNaRealizaciji_IdAndIdNot(predmetId, nnrId, r.getId())) {
	        throw new ResponseStatusException(HttpStatus.CONFLICT, ("Nastavnku je vec dodeljen taj predmet."));
	    }
	    dodajSemestre(r);
	    return super.update(r);
	}
	
	
	//helper funkcija za setovanje semestara
	private void dodajSemestre(RealizacijaPredmeta r) {
	    if (r.getSemestri() == null || r.getSemestri().isEmpty()) {
	        r.setSemestri(new java.util.HashSet<>());
	        return;
	    }
	    var ids = r.getSemestri().stream()
	        .map(Semestar::getId)
	        .filter(java.util.Objects::nonNull)
	        .collect(java.util.stream.Collectors.toSet());

	    var managed = new java.util.HashSet<Semestar>();
	    semestarRepository.findAllById(ids).forEach(managed::add); 
	    r.setSemestri(managed); // zamenimo transient instance managed referencama (Resava problem u slucaju da je neki id null)
	}


}
