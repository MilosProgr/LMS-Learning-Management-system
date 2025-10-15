package ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;


import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO.PredmetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija.GodinaStudijaDTO.GodinaStudijaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO.StudentNaGodiniDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;


@Component
public class GodinaStudijaMapper implements Mapper<GodinaStudijaDTORecord, GodinaStudija> {

	@Override
	public GodinaStudijaDTORecord map(GodinaStudija e) {
		if(e == null) {
			return null;
		}
		
		 Set<StudentNaGodiniDTORecord> studentiNaGodini = null;
	        if (e.getStudentiNaGodini() != null) {
	            studentiNaGodini = e.getStudentiNaGodini().stream()
	                    .map(s -> new StudentNaGodiniDTORecord(
	                            s.getId(),
	                            s.getDatumUpisa(),
	                            s.getBrojIndeksa(),
	                            null, // student DTO ako je potreban
	                            null, // godinaStudija DTO ako je potreban
	                            s.getProsek(),
	                            null, // prijavljeni ispiti
	                            null, // studijski program
	                            null  // pohadjanja predmeta
	                    ))
	                    .collect(Collectors.toSet());
	        }
	        // Mapiranje predmeta
	        List<PredmetDTORecord> predmeti = null;
	        if (e.getPredmeti() != null) {
	            predmeti = e.getPredmeti().stream()
	                    .map(p -> new PredmetDTORecord(
	                            p.getId(),
	                            p.getNaziv(),
	                            p.getEsbn(),
	                            p.getObavezan(),
	                            p.getBrojPredavanja(),
	                            p.getBrojVezbi(),
	                            p.getDrugiObliciNastave(),
	                            p.getIstrazivackiRad(),
	                            p.getOstaliCasovi(),
	                            null, // silabus
	                            null, // sifra
	                            null, // studijski programi
	                            null, // godinaStudija
	                            null  // realizacijaPredmeta
	                    ))
	                    .collect(Collectors.toList());
	        }
		
//		if(e.getStudentiNaGodini() != null) {
//			gStudjaDto.setStudentiNaGodini(
//					e.getStudentiNaGodini().stream()
//					.map(
//							sNaGodini ->
//							new StudentNaGodiniDTO(sNaGodini.getId(), sNaGodini.getDatumUpisa(),
//									sNaGodini.getBrojIndeksa(),
//									sNaGodini.getProsek())
//							).collect(Collectors.toSet())
//					);
//		}
//		if (e.getPredmeti() != null) {
//			gStudjaDto.setPredmeti(
//					e.getPredmeti().stream()
//					.map(
//							predmeti ->
//							new PredmetDTO(predmeti.getId(),
//									predmeti.getNaziv(),
//									predmeti.getEsbn(),
//									predmeti.getObavezan(),
//									predmeti.getBrojPredavanja(),
//									predmeti.getBrojVezbi(),
//									predmeti.getDrugiObliciNastave(),
//									predmeti.getIstrazivackiRad(),
//									predmeti.getOstaliCasovi())
//							).collect(Collectors.toList())
//					);
//		}
		GodinaStudijaDTORecord gStudjaDto =
				new GodinaStudijaDTORecord(e.getId(), e.getGodina(), studentiNaGodini,
		                predmeti);
		
		return gStudjaDto;
	}

//	@Override
//	public List<GodinaStudijaDTORecord> map(List<GodinaStudija> e) {
//		// TODO Auto-generated method stub
//		return e.stream().map(this::map).collect(Collectors.toList());
//	}

}
