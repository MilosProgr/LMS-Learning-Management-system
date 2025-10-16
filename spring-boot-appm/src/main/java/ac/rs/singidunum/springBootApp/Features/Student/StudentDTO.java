package ac.rs.singidunum.springBootApp.Features.Student;
import java.util.List;

import ac.rs.singidunum.springBootApp.Features.Adresa.AdresaDTO.AdresaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Drzava.DrzavaDTO.DrzavaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Mesto.MestoDTO.MestoDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.StudentNaGodini.StudentNaGodiniDTO.StudentNaGodiniDTORecord;

public class StudentDTO  {
//public class StudentDTO extends RegistrovaniKorisnikDTO {


	
	public record StudentDTORecord(
			 Long id,
			 String jmbg,
			 String telefon,
			 boolean statusStudiranja,
			 boolean predmetiIzabrani,
			 Double stanjeNaRacunu ,
			
			 RegistrovaniKorisnikDTORecord korisnik,
			 MestoDTORecord mestoPrebivalista,
			 AdresaDTORecord adresa,
			 DrzavaDTORecord drzava,
			 List<StudentNaGodiniDTORecord> upisi
			) {}
	
	
}
