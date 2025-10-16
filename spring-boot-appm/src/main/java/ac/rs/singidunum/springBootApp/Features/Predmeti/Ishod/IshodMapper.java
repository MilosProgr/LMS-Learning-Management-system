package ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Predmeti.Ishod.IshodDTO.IshodDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.NastavniMaterijal.NastavniMaterijalDTO.NastavniMaterijalDTORecord;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO.PredmetDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class IshodMapper implements Mapper<IshodDTORecord, Ishod> {

	@Override
	public IshodDTORecord map(Ishod e) {
	    if (e == null) {
	        return null;
	    }

	    // Mapiranje predmeta
	    PredmetDTORecord predmetDTO = null;
	    if (e.getPredmet() != null) {
	        predmetDTO = new PredmetDTORecord(
	                e.getPredmet().getId(),
	                e.getPredmet().getNaziv(),
	                e.getPredmet().getEsbn(),
	                e.getPredmet().getObavezan(),
	                e.getPredmet().getBrojPredavanja(),
	                e.getPredmet().getBrojVezbi(),
	                e.getPredmet().getDrugiObliciNastave(),
	                e.getPredmet().getIstrazivackiRad(),
	                e.getPredmet().getOstaliCasovi(), null, null, null, null, null, null
	        );
	    }

	    // Mapiranje nastavnih materijala
//	    Set<NastavniMaterijalDTORecord> materijaliDTO = null;
//	    if (e.getNastavniMaterijali() != null && !e.getNastavniMaterijali().isEmpty()) {
//	        materijaliDTO = e.getNastavniMaterijali().stream()
//	                .map(m -> new NastavniMaterijalDTORecord(
//	                        m.getId(),
//	                        m.getNaziv(),
//	                        m.getOdobreno(),
//	                        m.getKolicina(),
//	                        (m.getAutorizator() != null)
//	                                ? new NastavnikDTORecord(
//	                                        m.getAutorizator().getId(),
//	                                        m.getAutorizator().getIme(),
//	                                        m.getAutorizator().getPrezime(),
//	                                        m.getAutorizator().getEmail()
//	                                )
//	                                : null,
//	                        (m.getPodnosilacZahteva() != null)
//	                                ? new SluzbenikStudentskeDTORecord(
//	                                        m.getPodnosilacZahteva().getId(),
//	                                        m.getPodnosilacZahteva().getIme(),
//	                                        m.getPodnosilacZahteva().getPrezime(),
//	                                        m.getPodnosilacZahteva().getEmail()
//	                                )
//	                                : null
//	                ))
//	                .collect(Collectors.toSet());
//	    }

	    // Vraćamo DTO record
	    return new IshodDTORecord(
	            e.getId(),
	            e.getOpis(),
	            e.isPolozeno(),
	            predmetDTO
	    );
	}


	@Override
	public List<IshodDTORecord> map(List<Ishod> e) {
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
