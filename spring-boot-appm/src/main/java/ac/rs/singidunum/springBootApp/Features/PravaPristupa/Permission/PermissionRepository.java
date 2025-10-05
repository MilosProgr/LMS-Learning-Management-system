package ac.rs.singidunum.springBootApp.Features.PravaPristupa.Permission;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {
    Optional<Permission> findPermissionByIme(String string);

}
