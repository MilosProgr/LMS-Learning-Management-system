package ac.rs.singidunum.springBootApp.Features.Fakultet;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Adresa.Adresa;
import ac.rs.singidunum.springBootApp.Features.Adresa.AdresaDTO.AdresaDTORecord;
import ac.rs.singidunum.springBootApp.Features.Fakultet.FakultetDTO.FakultetDTORecord;
import ac.rs.singidunum.springBootApp.Features.Mesto.MestoDTO.MestoDTORecord;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.Nastavnik;
import ac.rs.singidunum.springBootApp.Features.Nastavnici.Nastavnik.NastavnikDTO.NastavnikDTORecord;
import ac.rs.singidunum.springBootApp.Features.Univerzitet.UniverzitetDTO.UniverzitetDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;

@Component
public class FakultetMapper implements Mapper<FakultetDTORecord, Fakultet> {

	@Override
	public FakultetDTORecord map(Fakultet e) {
		if(e == null) {
			return null;
		}
		MestoDTORecord mestoDTO = null;
        if (e.getAdresa().getMesto() != null) {
            mestoDTO = new MestoDTORecord(e.getAdresa().getMesto().getId(), e.getAdresa().getMesto().getNaziv(), null);
        }
		AdresaDTORecord adresaDTO = null;
		if(e.getAdresa() != null) {
			adresaDTO = new AdresaDTORecord(
					e.getAdresa().getId(),
					e.getAdresa().getUlica(),
					e.getAdresa().getBroj(),
					mestoDTO
					);
		}
		UniverzitetDTORecord univerzitetDTO = null;
		AdresaDTORecord adresaUniverzitetDTO = null;
		if(e.getUniverzitet() != null){
			
			Adresa adresaUniverzitet = e.getUniverzitet().getAdresa();
			Nastavnik rektor = e.getUniverzitet().getRektor();
			
			NastavnikDTORecord rektorDTO = null;
			
			rektorDTO = new NastavnikDTORecord(
					null, null, null, null, rektor.getPoslovniMail(), 0, 0, null, Set.of(), Set.of());
			
			adresaUniverzitetDTO = new AdresaDTORecord(
					adresaUniverzitet.getId(),
					adresaUniverzitet.getUlica(),
					adresaUniverzitet.getBroj(),
					null);
			
			univerzitetDTO = new UniverzitetDTORecord(
					e.getUniverzitet().getId(), 
					e.getUniverzitet().getNaziv(),
					e.getUniverzitet().getOpis(),
					e.getUniverzitet().getDatumOsnivanja(),
					List.of(),
					adresaUniverzitetDTO,
					rektorDTO);
		}
		Nastavnik dekan = e.getDekan();
		
		NastavnikDTORecord dekanDTO = null;
		dekanDTO = new NastavnikDTORecord(dekan.getId(), dekan.getBiografija(), null, dekan.getTelefon(), dekan.getPoslovniMail(), 0, 0, null, null, null);
		FakultetDTORecord fakultetDTO =
				new FakultetDTORecord(
						e.getId(),
						e.getNaziv(),
						adresaDTO,
						univerzitetDTO,
						dekanDTO, 
						e.getOpis(),
						null
						);
		
//		if(e.getAdresa() != null) {
//			
//			fakultetDTO.setAdresa(
//					new AdresaDTO(e.getAdresa().getId(),
//							e.getAdresa().getUlica(),
//							e.getAdresa().getBroj()
//							)
//					);
//		}
//		//ovo je da dobijemo univerzitet za jedan fakultet,vrati ce objekat
//		if(e.getUniverzitet() != null) {
//			
//			fakultetDTO.setUniverzitet(
//					
//					new UniverzitetDTO(
//							e.getUniverzitet().getId(),
//							e.getUniverzitet().getNaziv(),
//							e.getUniverzitet().getDatumOsnivanja(),
//							e.getUniverzitet().getOpis()
//							
//							)
//					);
//		}
//		if(e.getStudijskiProgrami() != null) {
//			
//			fakultetDTO.setProgrami(
//					e.getProgrami().stream()
//					.map(programi ->
//					new StudijskiProgramDTO(programi.getId(),
//							programi.getNaziv(), 
//							null)
//							).collect(Collectors.toList())
//					
//					);
//		}
//        if (e.getDekan() != null) {
//        	NastavnikDTORecord dekanDto = new NastavnikDTORecord(
//                e.getDekan().getId(),
//                e.getDekan().getBiografija(),
//                e.getDekan().getJmbg(),
//                e.getDekan().getTelefon(),
//                e.getDekan().getPoslovniMail(),
//                e.getDekan().getBrojSlobodnihDana(),
//                e.getDekan().getBrojIskoristenihDana(), null, null, null
//            );
//
//            if (e.getDekan().getKorisnik() != null) {
//                RegistrovaniKorisnikDTO korisnikDto = new RegistrovaniKorisnikDTO(
//                    e.getDekan().getKorisnik().getId(),
//                    e.getDekan().getKorisnik().getIme(),
//                    e.getDekan().getKorisnik().getPrezime(),
//                    e.getDekan().getKorisnik().getKorisnickoIme(),
//                    null,
//                    e.getDekan().getKorisnik().getEmail()
//                );
////                dekanDto.setKorisnik(korisnikDto);
//            }
//
////            fakultetDTO.setDekan(dekanDto);
//        }
        
        return fakultetDTO;
    }




}
