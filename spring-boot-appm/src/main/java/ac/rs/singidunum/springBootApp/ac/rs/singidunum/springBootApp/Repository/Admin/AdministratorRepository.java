package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Admin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Admin.Administrator;
import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Student.RegistrovaniKorisnik;

@Repository
public interface AdministratorRepository  extends CrudRepository<Administrator, Long> {
	
}
