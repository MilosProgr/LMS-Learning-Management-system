package ac.rs.singidunum.springBootApp.Mapper.implementacija.Predmet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.predmet.KursDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Predmet.Kurs;

@Component
public class KursMapper implements Mapper<KursDTO, Kurs> {

	@Override
	public KursDTO map(Kurs e) {
		KursDTO kursDTO =
		new KursDTO(
				e.getId(),
				e.getNaziv(),
				e.getTrajanje(),
				e.getOznaka(),
				e.getDatumPocetka(),
				e.getDatumKraja()
				);
		kursDTO.setPredmeti(
				e.getPredmeti().stream()
				.map(predmeti ->
				new PredmetDTO(
						predmeti.getId(),
						predmeti.getNaziv(),
						predmeti.getEsbn(),
						predmeti.getObavezan(),
						predmeti.getBrojPredavanja(),
						predmeti.getBrojVezbi(),
						predmeti.getDrugiObliciNastave(),
						predmeti.getIstrazivackiRad(),
						predmeti.getOstaliCasovi()
						)
				).collect(Collectors.toSet())
				);
		return kursDTO;
	}

	@Override
	public List<KursDTO> map(List<Kurs> e) {
		return e.stream().map(this::map).collect(Collectors.toList());

	}


}
