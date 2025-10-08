package ac.rs.singidunum.springBootApp.Features.Admin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdministratorRepository  extends CrudRepository<Administrator, Long> {
	
}
