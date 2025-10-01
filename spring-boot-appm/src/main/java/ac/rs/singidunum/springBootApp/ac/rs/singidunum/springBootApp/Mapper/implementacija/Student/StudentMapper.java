package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.implementacija.Student;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Adresa.AdresaDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Adresa.DrzavaDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Adresa.MestoDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Student.RegistrovaniKorisnikDTO;
//import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Student.PohadjanjePredmetaDTO;
//import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Student.PrijavljeniIspitDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Student.StudentDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.Student.StudentNaGodiniDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.DTO.predmet.StudijskiProgramDTO;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.Student;

@Component
public class StudentMapper implements Mapper<StudentDTO, Student> {

	@Override
	public StudentDTO map(Student e) {
		if(e == null) {
			return null;
		}
		StudentDTO studentDTO =
				new StudentDTO(
						e.getId(),
						e.getJmbg(),
						e.getTelefon(),
						e.getStatusStudiranja(),
						e.getStanjeNaRacunu(),
						e.isPredmetiIzabrani()
						);
		
		if (e.getUpisi() != null && !e.getUpisi().isEmpty()) {
		    List<StudentNaGodiniDTO> upisiDto = e.getUpisi().stream()
		        .map(upis -> new StudentNaGodiniDTO(
		                upis.getId(),
		                upis.getDatumUpisa(),
		                upis.getBrojIndeksa(),
		                upis.getProsek()
		        ))
		        .toList();

		    studentDTO.setUpisi(upisiDto);
		}
		
		
	
		if(e.getDrzava()!=null) {
		studentDTO.setDrzava(
				new DrzavaDTO(e.getDrzava().getId(), e.getDrzava().getNaziv())
				);
		}
		
		if(e.getAdresa()!=null) {
		studentDTO.setAdresa(
				new AdresaDTO(e.getAdresa().getId(), e.getAdresa().getUlica(), e.getAdresa().getBroj())
				);
		}
		
		if(e.getMestoPrebivalista()!=null) {
		studentDTO.setMestoPrebivalista(
				new MestoDTO(e.getMestoPrebivalista().getId(), e.getMestoPrebivalista().getNaziv())
				);
		}
		
		if(e.getKorisnik() != null) {
			studentDTO.setKorisnik(
					new RegistrovaniKorisnikDTO(e.getKorisnik().getId(),
							e.getKorisnik().getIme(),
							e.getKorisnik().getPrezime(),
							e.getKorisnik().getKorisnickoIme(),
							e.getKorisnik().getLozinka(),
							e.getKorisnik().getEmail())
					);
		}
		
		return studentDTO;
	}

	@Override
	public List<StudentDTO> map(List<Student> e) {
		return e.stream().map(this::map).collect(Collectors.toList());
	}

}
