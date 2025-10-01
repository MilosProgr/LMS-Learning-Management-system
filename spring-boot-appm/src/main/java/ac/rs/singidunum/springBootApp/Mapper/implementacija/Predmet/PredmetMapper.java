package ac.rs.singidunum.springBootApp.Mapper.implementacija.Predmet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.Sifarnik.SifraDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.GodinaStudijaDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.IshodDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.KursDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.StudijskiProgramDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Predmet.Predmet;

@Component
public class PredmetMapper implements Mapper<PredmetDTO, Predmet> {

	@Override
	public PredmetDTO map(Predmet e) {
		PredmetDTO predmetDTO =
				new PredmetDTO(
						e.getId(),
						e.getNaziv(),
						e.getEsbn(),
						e.getObavezan(),
						e.getBrojPredavanja(),
						e.getBrojVezbi(),
						e.getDrugiObliciNastave(),
						e.getIstrazivackiRad(),
						e.getOstaliCasovi()
						);
		if(e.getKursevi() != null) {
			
			predmetDTO.setKursevi(
					e.getKursevi().stream()
					.map(kursevi -> 
					new KursDTO(
							kursevi.getId(),
							kursevi.getNaziv(),
							kursevi.getTrajanje(),
							kursevi.getOznaka(),
							kursevi.getDatumPocetka(),
							kursevi.getDatumKraja()
							)
							).collect(Collectors.toSet()));
		}
		if(e.getSilabus() != null) {
			
			predmetDTO.setSilabus(
					e.getSilabus().stream()
					.map(silabusi ->
					new IshodDTO(silabusi.getId(), silabusi.getOpis(), silabusi.isPolozeno(), null)
							).collect(Collectors.toSet())
					);
		}
		if(e.getStudijskiProgrami() != null) {
			predmetDTO.setStudijskiProgrami(
					e.getStudijskiProgrami().stream()
					.map(
							programi -> 
							new StudijskiProgramDTO(programi.getId(), programi.getNaziv(), null)
							).collect(Collectors.toSet())
					);
		}
		if(e.getSifra() != null) {
			predmetDTO.setSifra(
					new SifraDTO(e.getSifra().getId(), e.getSifra().getTekst())
					);
		}
        if (e.getGodinaStudija() != null) {
            predmetDTO.setGodinaStudija(
                    new GodinaStudijaDTO(
                            e.getGodinaStudija().getId(),
                            e.getGodinaStudija().getGodina()
                    )
            );
        }
		
		
		
		return predmetDTO;
	}	

	@Override
	public List<PredmetDTO> map(List<Predmet> e) {
		return e.stream().map(this::map).collect(Collectors.toList());	

	}


}
