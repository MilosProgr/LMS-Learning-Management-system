package ac.rs.singidunum.springBootApp.Features.Student.PohadjanjePredmeta;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;


import ac.rs.singidunum.springBootApp.Features.Predmeti.Predmet.PredmetDTO;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;


@Component
public class PohadjanjePredmetaMapper implements Mapper<PohadjanjePredmetaDTO, PohadjanjePredmeta> {

	@Override
	public PohadjanjePredmetaDTO map(PohadjanjePredmeta e) {
		if(e == null) 
		{
			return null;
		}
		PohadjanjePredmetaDTO ppDto =
				new PohadjanjePredmetaDTO(e.getId(), e.getKonacnaOcena());
		if(e.getPredmet()!= null) {
			
			ppDto.setPredmet(
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
		
		if(e.getStudentNaGodini() != null) {
			ppDto.setStudentNaGodini(
					new StudentNaGodiniDTO(
							e.getStudentNaGodini().getId(),
							e.getStudentNaGodini().getDatumUpisa(),
							e.getStudentNaGodini().getBrojIndeksa(),
							e.getStudentNaGodini().getProsek()
							)
					
					);
		}
//		if(e.getRealizacijaPredmeta().getTerminNastave()!= null) {
//			
//			ppDto.getRealizaacijaPredmeta().setTerminNastave(
//					e.getRealizacijaPredmeta().getTerminNastave()
//					.stream().map(
//							termini ->
//							new TerminNastaveDTO(termini.getId(), termini.getVremePocetka(), termini.getVremeZavrsetka(),null)
//							).collect(Collectors.toList())
//					);
//		}
		
		
		
		return ppDto;
	}

	@Override
	public List<PohadjanjePredmetaDTO> map(List<PohadjanjePredmeta> e) {
		// TODO Auto-generated method stub
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
