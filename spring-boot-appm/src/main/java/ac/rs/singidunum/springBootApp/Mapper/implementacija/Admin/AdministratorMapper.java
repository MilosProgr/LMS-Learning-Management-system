package ac.rs.singidunum.springBootApp.Mapper.implementacija.Admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ac.rs.singidunum.springBootApp.DTO.AdminDTO.AdministratorDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.DTO.Student.StudentNaGodiniDTO;
import ac.rs.singidunum.springBootApp.Mapper.deklaracija.Mapper;
import ac.rs.singidunum.springBootApp.Model.Admin.Administrator;


@Component
public class AdministratorMapper implements Mapper<AdministratorDTO, Administrator> {

    @Override
    public AdministratorDTO map(Administrator admin) {
        if (admin == null) {
            return null;
        }

        // Kreiranje AdministratorDTO objekta sa podacima iz entiteta
        AdministratorDTO adminDTO = new AdministratorDTO(
                admin.getId(),
                admin.getJmbg(),
                admin.getTelefon(),
                admin.getPoslovniEmail(),
                admin.getDatumkreiranjaNaloga(),
                admin.isNalogAktivan()
        );
        
		if(admin.getKorisnik() != null) {
			adminDTO.setKorisnik(
					new RegistrovaniKorisnikDTO(admin.getKorisnik().getId(), admin.getKorisnik().getIme(),
							admin.getKorisnik().getPrezime(), admin.getKorisnik().getKorisnickoIme(),
							admin.getKorisnik().getLozinka() ,admin.getKorisnik().getEmail())
			);
		}

        return adminDTO;
    }

    @Override
    public List<AdministratorDTO> map(List<Administrator> administrators) {
        return administrators.stream().map(this::map).collect(Collectors.toList());
    }
}
