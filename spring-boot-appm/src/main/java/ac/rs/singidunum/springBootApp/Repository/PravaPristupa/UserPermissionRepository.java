package ac.rs.singidunum.springBootApp.Repository.PravaPristupa;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.Model.PravaPristupa.UserPermission;

@Repository
public interface UserPermissionRepository extends CrudRepository<UserPermission, Long> {
	Optional<UserPermission> findByPermission_Ime(String ime);
	
    boolean existsByKorisnikIdAndPermissionId(Long korisnikId, Long permissionId);

}
