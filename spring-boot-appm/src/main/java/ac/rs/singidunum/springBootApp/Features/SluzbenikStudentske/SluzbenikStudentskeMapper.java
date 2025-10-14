package ac.rs.singidunum.springBootApp.Features.SluzbenikStudentske;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.SluzbenikStudentske.SluzbenikStudentskeDTO.SluzbenikStudentskeDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;



@Component
public class SluzbenikStudentskeMapper implements Mapper<SluzbenikStudentskeDTORecord, SluzbenikStudentske> {

	@Override
	public SluzbenikStudentskeDTORecord map(SluzbenikStudentske e) {
		if(e  == null) {
			return null;
		}
		RegistrovaniKorisnikDTORecord registrovaniKorisnikDTO = null;
		if(e.getKorisnik() != null) {
			registrovaniKorisnikDTO = new RegistrovaniKorisnikDTORecord(e.getKorisnik().getId(),
        			e.getKorisnik().getIme(),
        			e.getKorisnik().getPrezime(),
        			e.getKorisnik().getKorisnickoIme(),
        			e.getKorisnik().getLozinka(),
        			e.getKorisnik().getEmail(),
        			null, null); 
		}
		SluzbenikStudentskeDTORecord sDto = new SluzbenikStudentskeDTORecord(
				e.getId(),
				e.getJmbg(),
				e.getTelefon(),
				e.getNalogAktivan(), registrovaniKorisnikDTO);
		
//		if(e.getKorisnik() != null) {
//			sDto.setKorisnik(
//					new RegistrovaniKorisnikDTO(
//							e.getKorisnik().getId(),
//							e.getKorisnik().getIme(),
//							e.getKorisnik().getPrezime(),
//							e.getKorisnik().getKorisnickoIme(),
//							e.getKorisnik().getLozinka(),
//							e.getKorisnik().getEmail())
//					);
//		}
		return sDto;
	}

//	@Override
//	public List<SluzbenikStudentskeDTO> map(List<SluzbenikStudentske> e) {
//		return e.stream().map(this::map).collect(Collectors.toList());
//	}

}
