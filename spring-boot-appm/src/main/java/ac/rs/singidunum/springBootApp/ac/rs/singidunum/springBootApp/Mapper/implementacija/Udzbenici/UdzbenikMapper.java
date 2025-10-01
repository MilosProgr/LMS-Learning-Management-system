package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.implementacija.Udzbenici;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Student.GodinaStudijaDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Udzbenici.UdzbenikDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Udzbenici.Udzbenik;

@Component
public class UdzbenikMapper implements Mapper<UdzbenikDTO, Udzbenik> {

	@Override
	public UdzbenikDTO map(Udzbenik e) {
		if(e == null) {
			return null;
		}
		UdzbenikDTO uDto = new UdzbenikDTO(e.getId(), e.getNaziv());
		if(e.getGodinaStudija() != null) {
			uDto.setGodinaStudija(
					new GodinaStudijaDTO(e.getGodinaStudija().getId(), e.getGodinaStudija().getGodina())
			);
		}
		if(e.getPredmet() != null) {
			PredmetDTO predmetDTO = new PredmetDTO(
					e.getPredmet().getId(),
					e.getPredmet().getNaziv(),
					e.getPredmet().getEsbn(),
					e.getPredmet().getObavezan(),
					e.getPredmet().getBrojPredavanja(),
					e.getPredmet().getBrojVezbi(),
					e.getPredmet().getDrugiObliciNastave(),
					e.getPredmet().getIstrazivackiRad(),
					e.getPredmet().getOstaliCasovi()
			);

			if (e.getPredmet().getGodinaStudija() != null) {
				predmetDTO.setGodinaStudija(
						new GodinaStudijaDTO(
								e.getPredmet().getGodinaStudija().getId(),
								e.getPredmet().getGodinaStudija().getGodina()
						)
				);
			}

			uDto.setPredmet(predmetDTO);
		}
		return uDto;
	}

	@Override
	public List<UdzbenikDTO> map(List<Udzbenik> e) {
		// TODO Auto-generated method stub
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
