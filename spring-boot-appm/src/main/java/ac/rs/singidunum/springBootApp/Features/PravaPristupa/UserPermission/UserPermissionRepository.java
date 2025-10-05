package ac.rs.singidunum.springBootApp.Features.PravaPristupa.UserPermission;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionRepository extends CrudRepository<UserPermission, Long> {
	Optional<UserPermission> findByPermission_Ime(String ime);
	
    boolean existsByKorisnikIdAndPermissionId(Long korisnikId, Long permissionId);

}
