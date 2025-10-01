package ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Repository.Predmet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ac.rs.singidunum.springBootApp.ac.rs.singidunum.springBootApp.Model.Predmet.TipNastave;

@Repository
public interface TipNastaveRepository extends CrudRepository<TipNastave, Long>{

}
