package ac.rs.singidunum.springBootApp.Features.Predmeti.RealizacijaPredmeta;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Nastava.TerminNastave.TerminNastaveDTO;
import ac.rs.singidunum.springBootApp.Features.Nastava.TipNastave.TipNastaveDTO;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.NastavnikNaRealizaciji.NastavnikNaRealizacijiDTO;
import ac.rs.singidunum.springBootApp.Features.Polaganja.EvaluacijaZnanja.EvaluacijaZnanjaDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Semestar.SemestarDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Semestar.SemestarDTO.SemestarDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class RealizacijaPredmetaMapper implements Mapper<RealizacijaPredmetaDTO, RealizacijaPredmeta> {

	   @Override
	    public RealizacijaPredmetaDTO map(RealizacijaPredmeta e) {
	        if (e == null) {
	            return null;
	        }

	        RealizacijaPredmetaDTO rDto =
	                new RealizacijaPredmetaDTO(
	                        e.getId(),
	                        e.getPredmet() != null
	                                ? new PredmetDTO(
	                                        e.getPredmet().getId(),
	                                        e.getPredmet().getNaziv(),
	                                        e.getPredmet().getEsbn(),
	                                        e.getPredmet().getObavezan(),
	                                        e.getPredmet().getBrojPredavanja(),
	                                        e.getPredmet().getBrojVezbi(),
	                                        e.getPredmet().getDrugiObliciNastave(),
	                                        e.getPredmet().getIstrazivackiRad(),
	                                        e.getPredmet().getOstaliCasovi()
	                                )
	                                : null,
	                        e.getNastavnikNaRealizaciji() != null
	                                ? new NastavnikNaRealizacijiDTO(
	                                        e.getNastavnikNaRealizaciji().getId(),
	                                        e.getNastavnikNaRealizaciji().getBrojCasova())
	                                : null
	                );

	        rDto.setEvaluacijaZnanja(
	                e.getEvaluacijaZnanja().stream()
	                        .map(ev -> new EvaluacijaZnanjaDTO(
	                                ev.getId(),
	                                ev.getVremePocetka(),
	                                ev.getVremeZavrsetka(),
	                                ev.getOstvareniBodovi()
	                        ))
	                        .collect(Collectors.toList())
	        );

	        rDto.setTerminNastave(
	                e.getTerminNastave().stream()
	                        .map(t -> new TerminNastaveDTO(
	                                t.getId(),
	                                t.getVremePocetka(),
	                                t.getVremeZavrsetka(),
	                                t.getTipNastave() != null
	                                        ? new TipNastaveDTO(
	                                                t.getTipNastave().getId(),
	                                                t.getTipNastave().getNaziv())
	                                        : null
	                        ))
	                        .collect(Collectors.toList())
	        );

	        if (e.getSemestri() != null) {
	            rDto.setSemestri(
	                    e.getSemestri().stream()
	                            .map(s -> new SemestarDTORecord(
	                                    s.getId(),
	                                    s.getDatumPocetka(),
	                                    s.getDatumKraja(),
	                                    s.getTip()
	                            ))
	                            .collect(Collectors.toSet())
	            );
	        }

	        return rDto;
	    }

	    @Override
	    public List<RealizacijaPredmetaDTO> map(List<RealizacijaPredmeta> e) {
	        return e.stream().map(this::map).collect(Collectors.toList());
	    }

	    public Set<RealizacijaPredmetaDTO> map(Set<RealizacijaPredmeta> e) {
	        return e.stream().map(this::map).collect(Collectors.toSet());
	    }
	    
}
