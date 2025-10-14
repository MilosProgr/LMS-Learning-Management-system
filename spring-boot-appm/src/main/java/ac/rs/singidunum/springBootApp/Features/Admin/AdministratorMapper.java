package ac.rs.singidunum.springBootApp.Features.Admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.Features.Admin.AdministratorDTO.AdministratorDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;


@Component
public class AdministratorMapper implements Mapper<AdministratorDTORecord, Administrator> {

    @Override
    public AdministratorDTORecord map(Administrator admin) {
        if (admin == null) {
            return null;
        }
        RegistrovaniKorisnikDTORecord registrovaniKorisnikDTO = null;
        if(admin.getKorisnik() != null) 
        {
        	registrovaniKorisnikDTO = new RegistrovaniKorisnikDTORecord(admin.getKorisnik().getId(),
        			admin.getKorisnik().getIme(),
        			admin.getKorisnik().getPrezime(),
        			admin.getKorisnik().getKorisnickoIme(),
        			admin.getKorisnik().getLozinka(),
        			admin.getKorisnik().getEmail(),
        			null, null);        
        	}

        // Kreiranje AdministratorDTO objekta sa podacima iz entiteta
        AdministratorDTORecord adminDTO = new AdministratorDTORecord(
                admin.getId(),
                admin.getJmbg(),
                admin.getTelefon(),
                admin.getPoslovniEmail(),
                admin.getDatumkreiranjaNaloga(),
                admin.isNalogAktivan(), 
                registrovaniKorisnikDTO
        );
        
//		if(admin.getKorisnik() != null) {
//			adminDTO.setKorisnik(
//					new RegistrovaniKorisnikDTO(admin.getKorisnik().getId(), admin.getKorisnik().getIme(),
//							admin.getKorisnik().getPrezime(), admin.getKorisnik().getKorisnickoIme(),
//							admin.getKorisnik().getLozinka() ,admin.getKorisnik().getEmail())
//			);
//		}
        

        return adminDTO;
    }

//    @Override
//    public List<AdministratorDTO> map(List<Administrator> administrators) {
//        return administrators.stream().map(this::map).collect(Collectors.toList());
//    }
}
