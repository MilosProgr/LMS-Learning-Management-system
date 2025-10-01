package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.implementacija.SluzbenikStudentske;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.SluzbenikStudentske.SluzbenikStudentskeDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Student.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.SluzbenikStudentske.SluzbenikStudentske;

@Component
public class SluzbenikStudentskeMapper implements Mapper<SluzbenikStudentskeDTO, SluzbenikStudentske> {

	@Override
	public SluzbenikStudentskeDTO map(SluzbenikStudentske e) {
		if(e  == null) {
			return null;
		}
		SluzbenikStudentskeDTO sDto = new SluzbenikStudentskeDTO(
				e.getId(),
				e.getJmbg(),
				e.getTelefon(),
				e.getNalogAktivan());
		
		if(e.getKorisnik() != null) {
			sDto.setKorisnik(
					new RegistrovaniKorisnikDTO(
							e.getKorisnik().getId(),
							e.getKorisnik().getIme(),
							e.getKorisnik().getPrezime(),
							e.getKorisnik().getKorisnickoIme(),
							e.getKorisnik().getLozinka(),
							e.getKorisnik().getEmail())
					);
		}
		return sDto;
	}

	@Override
	public List<SluzbenikStudentskeDTO> map(List<SluzbenikStudentske> e) {
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
