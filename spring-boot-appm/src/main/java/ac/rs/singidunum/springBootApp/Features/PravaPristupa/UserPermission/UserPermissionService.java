package ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.PermissionDTO;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission.PermissionDTO.PermissionDTORecord;
import ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission.UserPermissionDTO.UserPermissionDTORecord;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO.RegistrovaniKorisnikDTORecord;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;


@Service
public class UserPermissionService extends GenericCrudService<UserPermissionDTORecord, UserPermission, Long> {
	
	@Autowired
	private UserPermissionRepository uRepos;
	
	protected UserPermissionService(CrudRepository<UserPermission, Long> repository,
			Mapper<UserPermissionDTORecord, UserPermission> mapper) {
		super(repository, mapper);
		this.uRepos = (UserPermissionRepository) uRepos;
	}
	
	public Optional<UserPermission> findPermissionByNameUP(String ime){
		return uRepos.findByPermission_Ime(ime);
	}
	
	
	public List<UserPermissionDTORecord> getKorisniciSaListomUloga() {
		List<UserPermission> userPermissions = (List<UserPermission>) uRepos.findAll();

        Map<Long, UserPermissionDTORecord> korisnikMap = new HashMap<>();

        for (UserPermission up : userPermissions) {
            Long korisnikId = up.getKorisnik().getId();
            PermissionDTORecord ulogaDTO = new PermissionDTORecord(up.getPermission().getId(), up.getPermission().getIme(), null);

            korisnikMap.computeIfAbsent(korisnikId, k -> {
                RegistrovaniKorisnikDTORecord korisnikDTO = new RegistrovaniKorisnikDTORecord(
                        up.getKorisnik().getId(),
                        up.getKorisnik().getIme(),
                        up.getKorisnik().getPrezime(),
                        up.getKorisnik().getKorisnickoIme(),
                        up.getKorisnik().getLozinka(),
                        up.getKorisnik().getEmail(), null, null
                );

                return new UserPermissionDTORecord(k, null, korisnikDTO, null);
            }).listaUloga().add(ulogaDTO);
        }

        return new ArrayList<>(korisnikMap.values());
    }

}
