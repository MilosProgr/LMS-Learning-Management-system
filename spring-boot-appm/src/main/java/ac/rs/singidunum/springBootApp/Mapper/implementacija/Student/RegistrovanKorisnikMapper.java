package ac.rs.singidunum.springBootApp.Mapper.implementacija.Student;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.Student.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.RegistrovaniKorisnikOsnovniPodaciDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.StudentDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.StudentNaGodiniDTO;
import ac.rs.singidunum.springBootApp.Features.Admin.Administrator;
import ac.rs.singidunum.springBootApp.Features.Admin.AdministratorDTO;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO;
import ac.rs.singidunum.springBootApp.Features.Obavestenja.ObavestenjaAktivnosti.ObavestenjeAktivnostDTO;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.PermissionDTO;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermissionDTO;
import ac.rs.singidunum.springBootApp.Features.SluzbenikStudentske.SluzbenikStudentskeDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Student.RegistrovaniKorisnik;

@Component
public class RegistrovanKorisnikMapper implements Mapper<RegistrovaniKorisnikDTO, RegistrovaniKorisnik> {
	
	
	@Override
	public RegistrovaniKorisnikDTO map(RegistrovaniKorisnik korisnik) {
		if(korisnik == null) {
			return null;
		}
		
		
		RegistrovaniKorisnikDTO korisnikDTO = 
				new RegistrovaniKorisnikDTO(
						korisnik.getId(),
						korisnik.getIme(),
						korisnik.getPrezime(),
						korisnik.getKorisnickoIme(),
						korisnik.getLozinka(),
						korisnik.getEmail()
						);
		

//		if(korisnik.getAdministrator() != null) {
//			korisnikDTO.setAdministrator(
//					new AdministratorDTO(korisnik.getAdministrator().getId(), korisnik.getAdministrator().getJmbg(),
//							korisnik.getAdministrator().getTelefon(), korisnik.getAdministrator().getPoslovniEmail(),
//							korisnik.getAdministrator().getDatumkreiranjaNaloga(),
//							korisnik.getAdministrator().isNalogAktivan())
//			);
//		}
//		
//		if(korisnik.getNastavnik() != null) {
//			korisnikDTO.setNastavnik(
//					new NastavnikDTO(korisnik.getNastavnik().getId(), korisnik.getNastavnik().getBiografija(),
//							korisnik.getNastavnik().getJmbg(), korisnik.getNastavnik().getTelefon(), 
//							korisnik.getNastavnik().getPoslovniMail(), korisnik.getNastavnik().getBrojSlobodnihDana(),
//							korisnik.getNastavnik().getBrojIskoristenihDana()
//							)
//					);
//		}
//		
//		if(korisnik.getStudent() != null) {
//			korisnikDTO.setStudent(
//					new StudentDTO(korisnik.getStudent().getId(), korisnik.getStudent().getJmbg(),
//							korisnik.getStudent().getTelefon(), korisnik.getStudent().getStatusStudiranja()
//							)
//					);
//		}
//		
//		if(korisnik.getSluzbenik() != null) {
//			korisnikDTO.setSluzbenik(
//					new SluzbenikStudentskeDTO(korisnik.getSluzbenik().getId(), korisnik.getSluzbenik().getJmbg(),
//							korisnik.getSluzbenik().getTelefon(), korisnik.getSluzbenik().getNalogAktivan()
//							)
//					);
//		}
		
		if(korisnik.getPravoPristupa()!=null) {
					korisnikDTO.setPravaPristupa(
							korisnik.getPravoPristupa().stream()
				.map(pravaPristupa ->
				 new UserPermissionDTO(
						 pravaPristupa.getId(),
						 new PermissionDTO(
								 pravaPristupa.getPermission().getId(),
								 pravaPristupa.getPermission().getIme()
								 ),
						 null
						 )
				).collect(Collectors.toSet())
				);
		}
		if(korisnik.getObavestenjaAktivnosti() != null) {
			korisnikDTO.setObavestenjaAktivnosti(
					korisnik.getObavestenjaAktivnosti().stream().map(
							aktivnostiOb ->
							new ObavestenjeAktivnostDTO(aktivnostiOb.getId(), aktivnostiOb.getVremePostavljanja(),
									aktivnostiOb.getSadrzaj(), aktivnostiOb.getNaslov(),
									aktivnostiOb.getProcitano())
							).collect(Collectors.toSet())
					);
		}

		return korisnikDTO;
	}

	@Override
	public List<RegistrovaniKorisnikDTO> map(List<RegistrovaniKorisnik> e) {

		return e.stream().map(this::map).collect(Collectors.toList());
	}
	
	//MAPIRANJE OSNOVNIH PODATAKA
	public RegistrovaniKorisnikOsnovniPodaciDTO mapToOsnovniDTO(RegistrovaniKorisnik korisnik) {
		if (korisnik == null) {
			return null;
		}

		RegistrovaniKorisnikOsnovniPodaciDTO dto =
			new RegistrovaniKorisnikOsnovniPodaciDTO(
				korisnik.getId(),
				korisnik.getIme(),
				korisnik.getPrezime(),
				korisnik.getKorisnickoIme(),
				korisnik.getEmail()
			);

		if (korisnik.getObavestenjaAktivnosti() != null) {
			dto.setObavestenjaAktivnosti(
				korisnik.getObavestenjaAktivnosti().stream()
					.map(ob -> new ObavestenjeAktivnostDTO(
						ob.getId(),
						ob.getVremePostavljanja(),
						ob.getSadrzaj(),
						ob.getNaslov(),
						ob.getProcitano()))
					.collect(Collectors.toSet())
			);
		}

		return dto;
	}

	public List<RegistrovaniKorisnikOsnovniPodaciDTO> mapToOsnovniDTO(List<RegistrovaniKorisnik> korisnici) {
		return korisnici.stream().map(this::mapToOsnovniDTO).collect(Collectors.toList());
	}


}