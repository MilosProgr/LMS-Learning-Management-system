package ac.rs.singidunum.springBootApp.Features.Predmeti.NastavniMaterijal;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO.NastavnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.SluzbenikStudentske.SluzbenikStudentskeDTO;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class NastavniMaterijalMapper implements Mapper<NastavniMaterijalDTO, NastavniMaterijal> {

	@Override
	public NastavniMaterijalDTO map(NastavniMaterijal e) {
		if(e == null) {
			return null;
		}
		NastavniMaterijalDTO nDto = 
				new NastavniMaterijalDTO(e.getId(), e.getNaziv(),e.getOdobreno(),e.getKolicina());
		if(e.getPodnosilacZahteva() != null) {
			
			nDto.setPodnosilacZahteva(
					new SluzbenikStudentskeDTO(
							e.getPodnosilacZahteva().getId(),
							
							e.getPodnosilacZahteva().getJmbg(), e.getPodnosilacZahteva().getTelefon(),
							e.getPodnosilacZahteva().getNalogAktivan()
							)
					);
		}
		if(e.getAutorizator() != null) {
			
			nDto.setAutorizator(
					new NastavnikDTORecord(
							e.getAutorizator().getId(),
							e.getAutorizator().getBiografija(),
							e.getAutorizator().getJmbg(),
							e.getAutorizator().getTelefon(),
							e.getAutorizator().getPoslovniMail(),
							e.getAutorizator().getBrojSlobodnihDana(),
							e.getAutorizator().getBrojIskoristenihDana(), null, null, null

							
							)
					);
		}
		
	
		return nDto;
	}

	@Override
	public List<NastavniMaterijalDTO> map(List<NastavniMaterijal> e) {
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
