package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.PravaPristupa;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.PravaPristupa.Permission;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {
    Optional<Permission> findPermissionByIme(String string);

}
