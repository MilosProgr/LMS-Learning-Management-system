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
import ac.rs.singidunum.springBootApp.Features.Student.RegistrovaniKorisnik.RegistrovaniKorisnikDTO;
import ac.rs.singidunum.springBootApp.Generics.Mapper.Mapper;
import ac.rs.singidunum.springBootApp.Generics.Service.GenericCrudService;


@Service
public class UserPermissionService extends GenericCrudService<UserPermissionDTO, UserPermission, Long> {
	
	@Autowired
	private UserPermissionRepository uRepos;
	
	protected UserPermissionService(CrudRepository<UserPermission, Long> repository,
			Mapper<UserPermissionDTO, UserPermission> mapper) {
		super(repository, mapper);
		this.uRepos = (UserPermissionRepository) uRepos;
	}
	
	public Optional<UserPermission> findPermissionByNameUP(String ime){
		return uRepos.findByPermission_Ime(ime);
	}
	
	
	public List<UserPermissionDTO> getKorisniciSaListomUloga() {
		List<UserPermission> userPermissions = (List<UserPermission>) uRepos.findAll();

        Map<Long, UserPermissionDTO> korisnikMap = new HashMap<>();

        for (UserPermission up : userPermissions) {
            Long korisnikId = up.getKorisnik().getId();
            PermissionDTO ulogaDTO = new PermissionDTO(up.getPermission().getId(), up.getPermission().getIme());

            korisnikMap.computeIfAbsent(korisnikId, k -> {
                RegistrovaniKorisnikDTO korisnikDTO = new RegistrovaniKorisnikDTO(
                        up.getKorisnik().getId(),
                        up.getKorisnik().getIme(),
                        up.getKorisnik().getPrezime(),
                        up.getKorisnik().getKorisnickoIme(),
                        up.getKorisnik().getLozinka(),
                        up.getKorisnik().getEmail()
                );

                return new UserPermissionDTO(k, null, korisnikDTO);
            }).getListaUloga().add(ulogaDTO);
        }

        return new ArrayList<>(korisnikMap.values());
    }

}
