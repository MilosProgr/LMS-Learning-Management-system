package ac.rs.singidunum.springBootApp.Features.Student.GodinaStudija;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;


import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;


@Component
public class GodinaStudijaMapper implements Mapper<GodinaStudijaDTO, GodinaStudija> {

	@Override
	public GodinaStudijaDTO map(GodinaStudija e) {
		if(e == null) {
			return null;
		}
		GodinaStudijaDTO gStudjaDto =
				new GodinaStudijaDTO(e.getId(), e.getGodina());
		
		if(e.getStudentiNaGodini() != null) {
			gStudjaDto.setStudentiNaGodini(
					e.getStudentiNaGodini().stream()
					.map(
							sNaGodini ->
							new StudentNaGodiniDTO(sNaGodini.getId(), sNaGodini.getDatumUpisa(),
									sNaGodini.getBrojIndeksa(),
									sNaGodini.getProsek())
							).collect(Collectors.toSet())
					);
		}
		if (e.getPredmeti() != null) {
			gStudjaDto.setPredmeti(
					e.getPredmeti().stream()
					.map(
							predmeti ->
							new PredmetDTO(predmeti.getId(),
									predmeti.getNaziv(),
									predmeti.getEsbn(),
									predmeti.getObavezan(),
									predmeti.getBrojPredavanja(),
									predmeti.getBrojVezbi(),
									predmeti.getDrugiObliciNastave(),
									predmeti.getIstrazivackiRad(),
									predmeti.getOstaliCasovi())
							).collect(Collectors.toList())
					);
		}
		
		return gStudjaDto;
	}

	@Override
	public List<GodinaStudijaDTO> map(List<GodinaStudija> e) {
		// TODO Auto-generated method stub
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
