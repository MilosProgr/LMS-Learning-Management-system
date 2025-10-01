package ac.rs.singidunum.springBootApp.Mapper.implementacija.Student;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.Student.PrijavljeniIspitDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.StudentDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.StudentNaGodiniDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.EvaluacijaZnanjaDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.IspitniRokDTO;
import ac.rs.singidunum.springBootApp.DTO.predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Predmet.PrijavljeniIspit;

@Component
public class PrijavljeniIspitMapper implements Mapper<PrijavljeniIspitDTO, PrijavljeniIspit> {

	@Override
	public PrijavljeniIspitDTO map(PrijavljeniIspit e) {
		if(e == null) {
			return null;
		}
		PrijavljeniIspitDTO pDto = 
				new PrijavljeniIspitDTO(e.getId(), e.isPrijavljen(),e.getBrojPrijava());
		
		if (e.getEvaluacijeZnanja() != null && !e.getEvaluacijeZnanja().isEmpty()) {
		    List<EvaluacijaZnanjaDTO> evaluacijeDto = e.getEvaluacijeZnanja().stream()
		        .map(upis -> new EvaluacijaZnanjaDTO(
		                upis.getId(),
		                upis.getVremePocetka(),
		                upis.getVremeZavrsetka(),
		                upis.getOstvareniBodovi()
		        ))
		        .toList();

		    pDto.setEvaluacijeZnanja(evaluacijeDto);
		}
		
		
		if(e.getStudentNaGodini() != null) {
			
			pDto.setStudentNaGodini(
					new StudentNaGodiniDTO(
							e.getStudentNaGodini().getId(),
							e.getStudentNaGodini().getDatumUpisa(),
							e.getStudentNaGodini().getBrojIndeksa(),
							e.getStudentNaGodini().getProsek()
							)
					);
		}
		if(e.getPredmet() != null) {
			pDto.setPredmet(
					new PredmetDTO(e.getPredmet().getId(),
							e.getPredmet().getNaziv(),
							e.getPredmet().getEsbn(),
							e.getPredmet().getObavezan(),
							e.getPredmet().getBrojPredavanja(),
							e.getPredmet().getBrojVezbi(),
							e.getPredmet().getDrugiObliciNastave(),
							e.getPredmet().getIstrazivackiRad(),
							e.getPredmet().getOstaliCasovi())
					);
		}
		if(e.getIspitniRok() != null) {
			pDto.setIspitniRok(
					new IspitniRokDTO(e.getIspitniRok().getId(),
							e.getIspitniRok().getNaziv(),
							e.getIspitniRok().getPocetak(),
							e.getIspitniRok().getKraj(),
							e.getIspitniRok().getRedovan())
					);
		}
		
		
		return pDto;
	}

	@Override
	public List<PrijavljeniIspitDTO> map(List<PrijavljeniIspit> e) {
		// TODO Auto-generated method stub
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
